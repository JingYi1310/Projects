package assessmentfeedbacksystem;

import java.io.File;
import java.util.*;

public class Grade implements FileSerializable{
    private String gradeId;
    private int minMark;
    private int maxMark;
    private String grade;
    private String description;
    private double gpa;
    
    private static final String GRADE_FILE = "Grade.txt";
    
    public Grade() {}
    
    public Grade(String gradeId, int minScore, int maxScore, 
                 String grade, String description, double gpa) {
        this.gradeId = gradeId;
        this.minMark = minScore;
        this.maxMark = maxScore;
        this.grade = grade;
        this.description = description;
        this.gpa = gpa;
    }
    
    // Getters
    public String getGradeId() { 
        return gradeId; 
    }
    
    public int getMinMark() {
        return minMark;
    }

    public int getMaxMark() {
        return maxMark;
    }
    
    public String getGrade() { 
        return grade; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public double getGpa() { 
        return gpa; 
    }
    
    public static String getFileName(){
        return GRADE_FILE;
    }
    
    // Setter
    public void setGradeId(String grade_id){
        this.gradeId = grade_id;
    }
    
    public void setMinMark(int min_mark){
        this.minMark = min_mark;
    }
    
    public void setMaxMark(int max_mark){
        this.maxMark = max_mark;
    }
    
    public void setGrade(String grade){
        this.grade = grade;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setGpa(double gpa){
        this.gpa = gpa;
    }
    
    public boolean containsMark(int mark) {
        return mark >= minMark && mark <= maxMark;
    }
    
    public boolean isValidRange() {
        return minMark < maxMark;
    }

    public boolean overlaps(Grade other) {
        return this.minMark <= other.maxMark && this.maxMark >= other.minMark;
    }

    @Override
    public String serialize(){
        return String.join(";", 
             getGradeId(),
             Integer.toString(getMinMark()),
             Integer.toString(getMaxMark()),
             getGrade(),
             getDescription(),
             Double.toString(getGpa())
        );
    }
    
    // Initialize grade
    public void initializeGrade(){
        File file = new File(GRADE_FILE);
        
        try{
            if(file.exists() && file.length() > 0){
                System.out.println("Grade File already created.");
                return;
            }

            List<String> grade_list = List.of(
                "GR001;80;100;A+;Distinction;4.00",
                "GR002;75;79;A;Distinction;3.70",
                "GR003;70;74;B+;Credit;3.30",
                "GR004;65;69;B;Credit;3.00",
                "GR005;60;64;C+;Pass;2.70",
                "GR006;55;59;C;Pass;2.30",
                "GR007;50;54;C-;Pass;2.00",
                "GR008;40;49;D;Fail (Marginal);1.70",
                "GR009;30;39;F+;Fail;1.30",
                "GR010;20;29;F;Fail;1.00",
                "GR011;0;19;F-;Fail;0.00"
            );

            FileManager.writeFile(GRADE_FILE, grade_list, false);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    // Get all grades
    public static List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        List<String> lines = FileManager.readFile(GRADE_FILE);
        
        if(lines == null){
            System.out.println("File data not exists");
            return null;
        }
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 6) {
                grades.add(new Grade(
                    parts[0],
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    parts[3],
                    parts[4],
                    Double.parseDouble(parts[5])
                ));
            }
        }
        return grades;
    }

    // Get grade based on percentage score
   public static Grade getGradeByPercentage(double percentage) {
       List<Grade> grades = getAllGrades();

       // Sort by minScore descending to check highest grade first
       grades.sort((g1, g2) -> Integer.compare(g2.getMinMark(), g1.getMinMark()));

       for (Grade g : grades) {
           // Use >= for minScore only (since percentage can be decimal like 69.3)
           if (percentage >= g.getMinMark()) {
               return g;
           }
       }

       // Default to lowest grade if not found
       if (!grades.isEmpty()) {
           return grades.get(grades.size() - 1);
       }
       return null;
   }
    
    // Get GPA from letter grade
    public static double getGPAFromGrade(String letterGrade) {
        List<Grade> grades = getAllGrades();
        
        for (Grade g : grades) {
            if (g.getGrade().equals(letterGrade)) {
                return g.getGpa();
            }
        }
        return 0.0;
    }
    
    // Check if grade is passing
    public static boolean isPassing(String letterGrade) {
        Grade grade = getGradeByLetterGrade(letterGrade);
        if (grade != null) {
            return !grade.getDescription().toLowerCase().contains("fail");
        }
        return false;
    }
    
    // Get grade by letter
    public static Grade getGradeByLetterGrade(String letterGrade) {
        List<Grade> grades = getAllGrades();
        
        for (Grade g : grades) {
            if (g.getGrade().equals(letterGrade)) {
                return g;
            }
        }
        return null;
    }
    
    // Get respective grade using marks
    public static Grade getGradeByMarks(int marks) {
        List<String> lines = FileManager.readFile(GRADE_FILE);

        for (String line : lines) {
            if (line == null || line.isBlank()) continue;

            String[] data = line.split(";");

            if (data.length < 6) {
                System.out.println("Invalid grading line skipped: " + line);
                continue;
            }

            int min = Integer.parseInt(data[1]);
            int max = Integer.parseInt(data[2]);

            if (marks >= min && marks <= max) {
                return new Grade(
                    data[0],
                    min,
                    max,
                    data[3],
                    data[4],
                    Double.parseDouble(data[5])
                );
            }
        }
        return null; 
    }
    
    // Validate grading system
    public static String[] validateGradingSystem(List<Grade> gradeList) {
        if (gradeList == null || gradeList.isEmpty()) {
            return new String[]{"false", "Grade list is empty."};
        }

        gradeList.sort((g1, g2) -> Integer.compare(g2.getMinMark(), g1.getMinMark()));

        for (int i = 0; i < gradeList.size(); i++) {
            Grade current = gradeList.get(i);

            if (!current.isValidRange()) {
                return new String[]{"false", "Invalid range for grade: " + current.getGradeId()};
            }

            if (i < gradeList.size() - 1) {
                Grade next = gradeList.get(i + 1);

                if (current.overlaps(next)) {
                    return new String[]{"false", "Overlap detected between " + current.getGradeId() + " and " + next.getGradeId()};
                }

                if (next.getMaxMark() + 1 != current.getMinMark()) {
                    System.out.println();
                    return new String[]{"false", "Gap detected between " + current.getGradeId() + " and " + next.getGradeId()};
                }
            }
        }
        return new String[]{"true", "Grade validation successful"};
    }
}
