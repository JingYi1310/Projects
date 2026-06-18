package assessmentfeedbacksystem;

import assessmentfeedbacksystem.GUI.Lecturer.ResultRowData;
import java.time.LocalDate;
import java.util.*;

public class Result implements FileSerializable {
    private String resultId;
    private int score;
    private String grade;
    private String feedback;
    private Lecturer gradedBy;
    private LocalDate releasedAt;
    
    private Student student;
    private Assessment assessment;
    private ClassModel classModel;
    
    private static final String RESULT_FILE = "Result.txt";
    
    public Result() {}
    
    public Result(String resultId, Student student, Assessment assessment,
                 int score, String grade, String feedback, ClassModel classModel,
                 Lecturer gradedBy, LocalDate releasedAt) {
        this.resultId = resultId;
        this.student = student;
        this.assessment = assessment;
        this.score = score;
        this.grade = grade;
        this.feedback = feedback;
        this.classModel = classModel;
        this.gradedBy = gradedBy;
        this.releasedAt = releasedAt;
    }
    
    public Student getStudent(){
        return student;
    }
    
    public Assessment getAssessment(){
        return assessment;
    }
    
    public ClassModel getClassModel(){
        return classModel;
    }
    
    public String getResultId() { return resultId; }
    public int getScore() { return score; }
    public String getGrade() { return grade; }
    public String getFeedback() { return feedback; }
    public Lecturer getGradedBy() { return gradedBy; }
    public LocalDate getGradedAt() { return releasedAt; }

    public int getTotalMarks() {
        return assessment != null ? assessment.getTotalMarks() : 100;
    }
//    
    // Interface Method
    @Override
    public String serialize(){
        return String.join(";", 
                getResultId(),
                getStudent().getStudentId(),
                getAssessment().getAssessmentId(),
                Integer.toString(getScore()),
                getGrade(),
                getFeedback(),
                getClassModel().getClassId(),
                getGradedBy().getUser_id(),
                getGradedAt().toString()
        );
    }
    
    // Get all results for a student
    public static List<Result> getStudentResults(String studentId) {
        List<Result> results = new ArrayList<>();
        List<String> lines = FileManager.readFile(RESULT_FILE);
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 9 && parts[1].equals(studentId)) {
                results.add(new Result(
                    parts[0], Student.getStudentById(parts[1]), Assessment.getAssessmentById(parts[2]),
                    Integer.parseInt(parts[3]), parts[4], parts[5],
                    ClassModel.getClassById(parts[6]), Lecturer.getLecturerById(parts[7]), LocalDate.parse(parts[8])
                ));
            }
        }
        return results;
    }
    
    // Get results by student id and class id
    public static List<Result> getResultsByStudentAndClass(String studentId, String classId) {
        List<Result> results = new ArrayList<>();
        List<String> lines = FileManager.readFile(RESULT_FILE);
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 9 && parts[1].equals(studentId) && parts[6].equals(classId)) {
                results.add(new Result(
                    parts[0], Student.getStudentById(parts[1]), Assessment.getAssessmentById(parts[2]),
                    Integer.parseInt(parts[3]), parts[4], parts[5],
                    ClassModel.getClassById(parts[6]), Lecturer.getLecturerById(parts[7]), LocalDate.parse(parts[8])
                ));
            }
        }
        return results;
    }
    
    // Get class result
    public static Map<String, List<Result>> getResultsGroupedByClass(String studentId) {
    Map<String, List<Result>> grouped = new HashMap<>();
    List<Result> allResults = getStudentResults(studentId);

    for (Result r : allResults) {

        if (r.getClassModel() == null) {
            System.out.println("⚠ Warning: Result has null ClassModel. Skipping...");
            continue; // skip broken data
        }

        String classId = r.getClassModel().getClassId();

        grouped.putIfAbsent(classId, new ArrayList<>());
        grouped.get(classId).add(r);
    }

    return grouped;
}

    
    // Calculate GPA for a student (average of grade points per class)
    public static double calculateCGPA(String studentId) {

        Map<String, List<Result>> grouped = getResultsGroupedByClass(studentId);
        if (grouped.isEmpty()) return 0.0;

        double totalGpaPoints = 0.0;
        int classCount = 0;

        for (List<Result> classResults : grouped.values()) {

            double classGpaTotal = 0.0;
            int resultCount = 0;

            for (Result r : classResults) {
                if (r == null) continue;

                double gpa = 0.0;
                boolean hasGpa = false;

                String letterGrade = r.getGrade();
                if (letterGrade != null && !letterGrade.isBlank()) {
                    gpa = Grade.getGPAFromGrade(letterGrade);
                    hasGpa = true;
                } else if (r.getAssessment() != null && r.getAssessment().getTotalMarks() > 0) {
                    double percentage = (r.getScore() * 100.0) / r.getAssessment().getTotalMarks();
                    Grade gradeObj = Grade.getGradeByPercentage(percentage);
                    if (gradeObj != null) {
                        gpa = gradeObj.getGpa();
                        hasGpa = true;
                    }
                }

                if (hasGpa) {
                    classGpaTotal += gpa;
                    resultCount++;
                }
            }

            if (resultCount == 0) continue;
            totalGpaPoints += (classGpaTotal / resultCount);
            classCount++;
        }

        return classCount == 0 ? 0.0 :
               Double.parseDouble(String.format("%.2f", totalGpaPoints / classCount));
    }

    // Backward-compatible alias used by UI panels
    public static double calculateGPA(String studentId) {
        return calculateCGPA(studentId);
    }
    
    // Get total number of pass and fail results for a student
    public static int[] getPassFailSummary(String studentId) {
        List<Result> results = getStudentResults(studentId);
        int passed = 0;
        int total = results.size();
        
        for (Result r : results) {
            if (Grade.isPassing(r.getGrade())) {
                passed++;
            }
        }
        return new int[]{passed, total};
    }
    
    // Get assessment's average marks
    public static double getAverageMarksByAssessmentId(String assessment_id){
        List<String> lines = FileManager.readFile(RESULT_FILE);
        
        Assessment assessment = Assessment.getAssessmentById(assessment_id);
        int num_of_stu = ClassModel.getNoOfStudentInClass(ClassModel.getClassByModuleId(assessment.getModule().getModuleId()).getClassId());
        double maxMarks = assessment.getTotalMarks();
        
        double total_marks = 0;
        
        for (String line : lines){
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length !=9) continue;
            if (parts[2].equals(assessment_id)){
                total_marks += Integer.parseInt(parts[3]);
            }
        }
        double average = (total_marks / num_of_stu) / maxMarks * 100;
        return Double.parseDouble(String.format("%.2f", average));
    }
    
    // Get student result by assessment
    public static List<Result> getStudentResultByAssessment(String assessment_id){
        List<String> lines = FileManager.readFile(RESULT_FILE);
        List<Result> related_result = new ArrayList<>();
        
        for (String line: lines){
            if (line.trim().isEmpty()) continue;
            String [] parts = line.split(";");
            if (parts.length != 9) continue;
            if (parts[2].equals(assessment_id)){
                Assessment assessment = Assessment.getAssessmentById(assessment_id);
                Student student = Student.getStudentById(parts[1]);
                ClassModel classDetails = ClassModel.getClassById(parts[6]);
                
                related_result.add(new Result(
                        parts[0],
                        student,
                        assessment,
                        Integer.parseInt(parts[3]),
                        parts[4],
                        parts[5],
                        classDetails,
                        Lecturer.getLecturerById(parts[7]),
                        LocalDate.parse(parts[8])
                ));
            }
        }
        return related_result;
    }
    
    // Generate new result id 
    public static String generateNewResultId(List<String> lines) {
        int maxId = 0;

        for (String line : lines) {
            String[] data = line.split(";");
            String id = data[0];  // e.g., R001, R015

            try {
                int num = Integer.parseInt(id.substring(1));
                if (num > maxId) maxId = num;
            } catch (Exception ignored) {}
        }

        int newId = maxId + 1;
        return String.format("R%03d", newId);
    }

    
    // Save or update results
    public static boolean saveOrUpdateResults(String assessmentId, String classId, 
            String lecturerId, List<ResultRowData> rows) {
        try {
            List<String> lines = FileManager.readFile(RESULT_FILE);
            List<String> updatedLines = new ArrayList<>(lines);
            int totalMarks = Assessment.getAssessmentById(assessmentId).getTotalMarks();

            for (ResultRowData row : rows) {
                int rawMarks = row.getMarks();
                double percentage = (rawMarks * 100.0) / totalMarks;

                Grade gradeObj = Grade.getGradeByPercentage(percentage);
                String grade = gradeObj != null ? gradeObj.getGrade() : "F";


                if (row.getResultId() == null || row.getResultId().trim().isEmpty()) {
                    String newResultId = generateNewResultId(updatedLines);

                    Result r = new Result(
                        newResultId,
                        Student.getStudentById(row.getStudentId()),
                        Assessment.getAssessmentById(assessmentId),
                        row.getMarks(),
                        grade,
                        row.getFeedback(),
                        ClassModel.getClassById(classId),
                        Lecturer.getLecturerById(lecturerId),
                        LocalDate.now()
                    );
                    
                    updatedLines.add(r.serialize());

                } else {
                    for (int i = 0; i < updatedLines.size(); i++) {
                        String[] data = updatedLines.get(i).split(";");
                        if (data[0].equals(row.getResultId())) {

                            Result updated = new Result(
                                    row.getResultId(),
                                    Student.getStudentById(row.getStudentId()),
                                    Assessment.getAssessmentById(assessmentId),
                                    row.getMarks(),
                                    grade,
                                    row.getFeedback(),
                                    ClassModel.getClassById(classId),
                                    Lecturer.getLecturerById(lecturerId),
                                    LocalDate.now()
                            );

                            updatedLines.set(i, updated.serialize());
                            break;
                        }
                    }
                }
            }

            FileManager.writeFile(RESULT_FILE, updatedLines, false);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    // Remove all results for a student in a specific class
    public static boolean removeResultsByStudentAndClass(String studentId, String classId) {
        try {
            List<String> lines = FileManager.readFile(RESULT_FILE);
            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");

                if (parts.length != 9) {
                    updatedLines.add(line);
                    continue;
                }

                String resultStudentId = parts[1];
                String resultClassId = parts[6];

                if (resultStudentId.equals(studentId) && resultClassId.equals(classId)) {
                    continue; 
                }

                updatedLines.add(line);
            }

            FileManager.writeFile(RESULT_FILE, updatedLines, false);

            return true;

        } catch (Exception e) {
            System.out.println("Error removing results: " + e.getMessage());
            return false;
        }
    }
}
