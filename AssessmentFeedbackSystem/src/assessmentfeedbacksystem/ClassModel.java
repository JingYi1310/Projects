package assessmentfeedbacksystem;

import static assessmentfeedbacksystem.FileManager.readFile;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class ClassModel implements FileSerializable{
    private static final String CLASS_FILE = "Class.txt";
    private String classId;
    private String className;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String createdBy;
    private LocalDate createdAt;
    private Module module;
    
    // Constructor
    public ClassModel(){}
    
    public ClassModel(String classId, String className, DayOfWeek day, LocalTime startTime, 
            LocalTime endTime, String createdBy, LocalDate createdAt, Module module){
        this.classId = classId;
        this.className = className;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.module = module;
    }
    
    // Getter
    public static String getFileName(){
        return CLASS_FILE;
    }
    
    public String getClassId(){
        return classId;
    }
        
    public String getClassName(){
        return className;
    }
    
    public DayOfWeek getDay(){
        return day;
    }
    
    public String getCapitalizedDay(){
        String day1 = getDay().toString();
        return day1.substring(0,1).toUpperCase() + day1.substring(1).toLowerCase();
    }
    
    public LocalTime getStartTime(){
        return startTime;
    }
    
    public LocalTime getEndTime(){
        return endTime;
    }
    
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    
    public String getCreatedBy(){
        return createdBy;
    }
    
    public Module getModule(){ 
        return module; 
    }
    
    // Setter
    public void setClassId(String classId){
        this.classId = classId;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    
    public void setDay(DayOfWeek day){
        this.day = day;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
    
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    public void setModule(Module module){ 
        this.module = module; 
    }
    
    @Override
    public String serialize(){
        return String.join(";",
                getClassId(),
                getClassName(),
                getDay().toString(),
                getStartTime().toString(),
                getEndTime().toString(),
                getModule().getModuleId(),
                getCreatedBy(),
                getCreatedAt().toString()
        );
    }
    
    // Get number of student in a class
    public static int getNoOfStudentInClass(String classId){
        List<String> lines = readFile(Enrolment.getFileName());
        int count = 0;
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] data = line.split(";");
            if (data[2].equals(classId)) {
                count += 1;
            }
        }
        return count;
    }
    
    // Get class by module id
    public static ClassModel getClassByModuleId(String moduleId) {
        List<ClassModel> classes = readAllClasses();
        for (ClassModel c : classes) {
            if (c.getModule().getModuleId().equals(moduleId)) {
                return c; 
            }
        }
        return null;
    }   
    
    // Get specific module status
    public static String getModuleStatus(String moduleId) {
        ClassModel c = ClassModel.getClassByModuleId(moduleId);
        if (c != null) {
            return "Active"; 
        } else {
            return "Waiting for Class"; 
        }
    }
    
    // Get all classes
    public static List<ClassModel> readAllClasses() {
        List<ClassModel> classList = new ArrayList<>();
        List<Module> allModules = Module.getAllModules();

        List<String> lines = readFile(CLASS_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 8) continue;
            String moduleIdFromFile = data[5];
            Module moduleObj = allModules.stream()
                                         .filter(m -> m.getModuleId().equals(moduleIdFromFile))
                                         .findFirst()
                                         .orElse(null);

            if (moduleObj == null) {
                System.out.println("Module not found for class: " + data[0]);
                continue;
            }
            String dayToken = data[2].trim();
            if (dayToken.isEmpty()) {
                System.out.println("Invalid day value for class: " + data[0]);
                continue;
            }
            DayOfWeek day;
            try {
                day = DayOfWeek.valueOf(dayToken.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid day value for class: " + data[0] + " (" + dayToken + ")");
                continue;
            }
            ClassModel c = new ClassModel(
                data[0],
                data[1],
                day,
                LocalTime.parse(data[3]),
                LocalTime.parse(data[4]),
                data[6],
                LocalDate.parse(data[7]),
                moduleObj
            );

            classList.add(c);
        }
         return classList;
    }
    
    // Get module by class id
    public static Module getModuleByClassId(String class_id){
        Module module = null;
        List<Module> allModules = Module.getAllModules();

        List<String> lines = readFile(CLASS_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 8) continue;
            if (data[0].trim().equals(class_id)){
                String moduleIdFromFile = data[5];
                Module moduleObj = allModules.stream()
                                             .filter(m -> m.getModuleId().equals(moduleIdFromFile))
                                             .findFirst()
                                             .orElse(null);

                if (moduleObj == null) {
                    System.out.println("Module not found for class: " + data[0]);
                    continue;
                }
                module = moduleObj;
                break;
            }
        }
        return module;
    }
    
    // Get class by class id
    public static ClassModel getClassById(String classId) {
        List<ClassModel> classes = readAllClasses();
        for (ClassModel c : classes) {
            if (c.getClassId().equals(classId)) {
                return c;
            }
        }
        return null;
    }

    // Check the class time is same or not
    public static boolean hasTimeConflict(ClassModel class1, ClassModel class2) {
        if (!class1.getDay().equals(class2.getDay())) {
            return false;
        }

        LocalTime start1 = class1.getStartTime();
        LocalTime end1 = class1.getEndTime();
        LocalTime start2 = class2.getStartTime();
        LocalTime end2 = class2.getEndTime();

        return (start1.isBefore(end2) && end1.isAfter(start2));
    }
    
    // Get students by class id
    public List<Student> getAllStudentsByClass() {
        List<Student> students = new ArrayList<>();
        List<String> lines = FileManager.readFile(Enrolment.getFileName());

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");

            String studentId = data[1];
            String classIdFromFile = data[2];

            if (this.classId.equals(classIdFromFile)) {
                Student s = Student.getStudentById(studentId);
                if (s != null) {
                    students.add(s);
                }
            }
        }
        return students;
    }
    
    // Remove student from class
    public static boolean unenrolStudent(String classId, String studentId) {
        List<String> lines = FileManager.readFile(Enrolment.getFileName());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length != 4) {
                continue; 
            }

            String fileClassId = parts[2].trim();
            String fileStudentId = parts[1].trim();
  
            if (fileClassId.equals(classId) && fileStudentId.equals(studentId)) {
                found = true;
                continue;
            }

            updatedLines.add(line);
        }

        if (!found) {
            System.out.println("student not found.");
            return false;
        }

        FileManager.writeFile(Enrolment.getFileName(), updatedLines, false);
        Result.removeResultsByStudentAndClass(studentId, classId);
        return true;
    }

}
