package assessmentfeedbacksystem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Enrolment implements FileSerializable{
    private static final String ENROLMENT_FILE = "Enrolment.txt";
    
    private String enrolmentId;
    private String studentId;
    private String classId;
    private LocalDate enroledAt;
    
    // Constructor
    public Enrolment() {}
    
    public Enrolment(String enrolmentId, String studentId, String classId, LocalDate enroledAt) {
        this.enrolmentId = enrolmentId;
        this.studentId = studentId;
        this.classId = classId;
        this.enroledAt = enroledAt;
    }
    
    // Getter
    public static String getFileName() {
        return ENROLMENT_FILE;
    }
    
    public String getEnrolmentId() { 
        return enrolmentId; 
    }
    
    public String getStudentId() { 
        return studentId; 
    }
    
    public String getClassId() { 
        return classId; 
    }
    
    public LocalDate getEnroledAt() { 
        return enroledAt; 
    }
    
    // Interface method
    @Override
    public String serialize(){
        return String.join(";",
                getEnrolmentId(),
                getStudentId(),
                getClassId(),
                getEnroledAt().toString()
        );
    }
    
    // Get classes that enrol by a student
    public static List<Enrolment> getStudentEnrolments(String studentId) {
        List<Enrolment> enrolments = new ArrayList<>();
        List<String> lines = FileManager.readFile(ENROLMENT_FILE);
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 4 && parts[1].equals(studentId)) {
                try {
                    enrolments.add(new Enrolment(
                        parts[0], parts[1], parts[2], LocalDate.parse(parts[3])
                    ));
                } catch (Exception e) {
                    System.out.println("Skipping invalid enrolment line: " + line);
                }
            }
        }
        return enrolments;
    }
    
    // Get students in a specific class
    public static List<String> getStudentsByClassId(String classId) {
        List<String> studentIds = new ArrayList<>();
        List<String> lines = FileManager.readFile(ENROLMENT_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 3 && parts[2].equals(classId)) {
                studentIds.add(parts[1]); 
            }
        }
        return studentIds;
    }
    
    // Get class IDs that the student enroled in
    public static List<String> getEnroledClassIds(String studentId) {
        List<String> classIds = new ArrayList<>();
        List<Enrolment> enrolments = getStudentEnrolments(studentId);
        for (Enrolment e : enrolments) {
            classIds.add(e.getClassId());
        }
        return classIds;
    }
    
    // Check whether the student is enroled in the class
    public static boolean isEnroledInClass(String studentId, String classId) {
        return getEnroledClassIds(studentId).contains(classId);
    }
    
    // Save new enrolment in txt file
    public static boolean enrolInClass(String studentId, String classId) {
        if (isEnroledInClass(studentId, classId)) return false;
        
        ClassModel newClass = ClassModel.getClassById(classId);
        if (newClass == null) return false;
        
        List<String> enroledClassIds = getEnroledClassIds(studentId);
        for (String enroledClassId : enroledClassIds) {
            ClassModel enroledClass = ClassModel.getClassById(enroledClassId);
            if (enroledClass != null && ClassModel.hasTimeConflict(newClass, enroledClass)) {
                return false;
            }
        }
        
        String enrollmentId = generateEnrolmentId();
        Enrolment enrol = new Enrolment(enrollmentId, studentId, classId, LocalDate.now());

        
        try {
            FileManager.writeFile(ENROLMENT_FILE, enrol.serialize(), true);
            NotificationService.createEnrollmentSuccess(studentId, classId);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    // Generate new enrolment id
    private static String generateEnrolmentId() {
        List<String> lines = FileManager.readFile(ENROLMENT_FILE);
        int maxId = 0;
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 1) {
                String id = parts[0].replace("E", "");
                try {
                    int numId = Integer.parseInt(id);
                    if (numId > maxId) maxId = numId;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return String.format("E%03d", maxId + 1);
    }
}
