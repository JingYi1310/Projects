package assessmentfeedbacksystem;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    public List<ClassModel> getEnrolledClasses(String studentId) {
        List<String> classIds = Enrolment.getEnroledClassIds(studentId);
        List<ClassModel> classes = new ArrayList<>();
        for (String classId : classIds) {
            ClassModel c = ClassModel.getClassById(classId);
            if (c != null) classes.add(c);
        }
        return classes;
    }

    public List<String> getEnrolledClassIds(String studentId) {
        return Enrolment.getEnroledClassIds(studentId);
    }

    public List<ClassModel> getAllClasses() {
        return ClassModel.readAllClasses();
    }

    public ClassModel getClassById(String classId) {
        return ClassModel.getClassById(classId);
    }

    public boolean enrollInClass(String studentId, String classId) {
        return Enrolment.enrolInClass(studentId, classId);
    }

    public List<Notification> getNotifications(String studentId) {
        return Notification.getStudentNotifications(studentId);
    }

    public List<Result> getResults(String studentId) {
        return Result.getStudentResults(studentId);
    }

    public double calculateGpa(String studentId) {
        return Result.calculateGPA(studentId);
    }

    public double calculateCgpa(String studentId) {
        return Result.calculateCGPA(studentId);
    }

    public String[] loadStudentData(String studentId) {
        return User.loadStudentData(studentId);
    }

    public List<Feedback> getFeedbacks(String studentId) {
        return Feedback.getStudentFeedbacks(studentId);
    }

    public boolean hasFeedbackForClass(String studentId, String classId) {
        return Feedback.hasFeedbackForClass(studentId, classId);
    }

    public boolean submitFeedback(String studentId, String lecturerId, String classId,
                                  int rating, String category, String comment) {
        return Feedback.submitFeedback(studentId, lecturerId, classId, rating, category, comment);
    }

    public String[] getFeedbackCategories() {
        return Feedback.getFeedbackCategories();
    }
}
