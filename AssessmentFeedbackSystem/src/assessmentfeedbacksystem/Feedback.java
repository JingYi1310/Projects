package assessmentfeedbacksystem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Feedback implements FileSerializable{
    private String feedbackId;
    private int rating;
    private String comment;
    private String category;
    private LocalDate submittedAt;
    private Lecturer lecturer;
    private Student student;
    private ClassModel classModel;
    
    private static final String FEEDBACK_FILE = "Feedback.txt";
    
    public Feedback() {}
    
    public Feedback(String feedbackId, Lecturer lecturer, int rating,
                   String comment, String category, LocalDate submittedAt,
                   ClassModel classModel, Student student) {
        this.feedbackId = feedbackId;
        this.lecturer = lecturer;
        this.rating = rating;
        this.comment = comment;
        this.category = category;
        this.submittedAt = submittedAt;
        this.classModel = classModel;
        this.student = student;
    }
    
    public String getFeedbackId() { 
        return feedbackId; 
    }
    
    public int getRating() { 
        return rating; 
    }
    
    public String getComment() { 
        return comment; 
    }
    
    public String getCategory() { 
        return category; 
    }
    
    public LocalDate getSubmittedAt() { 
        return submittedAt; 
    }
    
    public Lecturer getLecturer(){
        return lecturer;
    }
    
    public ClassModel getClassModel(){
        return classModel;
    }
    
    public Student getStudent(){
        return student;
    }
    
    @Override
    public String serialize() {
        return String.join(";",
            getFeedbackId(),
            getLecturer().getUser_id(),
            String.valueOf(getRating()),
            getComment(),
            getCategory(),
            getSubmittedAt().toString(),
            getClassModel().getClassId(),
            getStudent().getStudentId()
        );
    }
    
    // Get student feedbacks
    public static List<Feedback> getStudentFeedbacks(String studentId) {
        List<Feedback> feedbacks = new ArrayList<>();
        List<String> lines = FileManager.readFile(FEEDBACK_FILE);
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 8 && parts[7].equals(studentId)) {
                feedbacks.add(new Feedback(
                    parts[0], Lecturer.getLecturerById(parts[1]), Integer.parseInt(parts[2]),
                    parts[3], parts[4], LocalDate.parse(parts[5]),
                    ClassModel.getClassById(parts[6]), Student.getStudentById(parts[7])
                ));
            }
        }
        return feedbacks;
    }
    
    // Save new feedback
    public static boolean submitFeedback(String studentId, String lecturerId,
                                        String classId, int rating,
                                        String category, String comment) {
        if (hasFeedbackForClass(studentId, classId)) {
            return false;
        }
        String feedbackId = generateFeedbackId();
        LocalDate submittedAt = LocalDate.now();
        
        String line = String.join(";",
            feedbackId, lecturerId, String.valueOf(rating),
            comment, category, submittedAt.toString(),
            classId, studentId
        );
        
        try {
            FileManager.writeFile(FEEDBACK_FILE, line, true);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean hasFeedbackForClass(String studentId, String classId) {
        List<String> lines = FileManager.readFile(FEEDBACK_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 8 && parts[7].equals(studentId) && parts[6].equals(classId)) {
                return true;
            }
        }
        return false;
    }
    
    // Set feedback categories
    public static String[] getFeedbackCategories() {
        return new String[]{
            "Teaching Quality",
            "Course Content",
            "Communication",
            "Availability",
            "Overall Experience"
        };
    }
    
    // Generate new feedback id
    private static String generateFeedbackId() {
        List<String> lines = FileManager.readFile(FEEDBACK_FILE);
        int max = 0;

        for (String line : lines) {
            if (line == null || line.isBlank()) continue;
            String[] data = line.split(";");
            if (data.length == 0) continue;

            String id = data[0].trim();
            if (id.isEmpty()) continue;

            // Extract digits from any prefix (e.g. RB001, B001)
            String digits = id.replaceAll("\\D+", "");
            if (digits.isEmpty()) continue;
            try {
                int num = Integer.parseInt(digits);
                max = Math.max(max, num);
            } catch (NumberFormatException ignored) {
            }
        }
        return String.format("FB%03d", max + 1);
    }
    
    // Get feedback by lecturer id
    public static List<Feedback> getFeedbackByLecturerId(String lecturer_id){
        List<String> lines = FileManager.readFile(FEEDBACK_FILE);
        List<Feedback> relatedFeedback = new ArrayList<>();
        
        for (String line: lines){
            String [] data = line.split(";");
            
            if (lecturer_id.equals(data[1])){
                relatedFeedback.add(new Feedback(
                        data[0],
                        Lecturer.getLecturerById(lecturer_id),
                        Integer.parseInt(data[2]),
                        data[3],
                        data[4],
                        LocalDate.parse(data[5]),
                        ClassModel.getClassById(data[6]),
                        Student.getStudentById(data[7])
                ));
            }
        }
        return relatedFeedback;
    }
    
    // Get overall rating given by students to the lecturer
    public static double getOverallRatingByLecturerId(String lecturer_id) {
        List<Feedback> feedbacks = getFeedbackByLecturerId(lecturer_id);

        if (feedbacks.isEmpty()) return 0.0;

        int totalRating = 0;
        for (Feedback f : feedbacks) {
            totalRating += f.getRating();
        }

        return (double) totalRating / feedbacks.size();
    }
}
