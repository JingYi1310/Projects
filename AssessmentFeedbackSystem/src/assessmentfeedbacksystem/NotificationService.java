package assessmentfeedbacksystem;

import java.time.LocalDateTime;
import java.util.List;

public final class NotificationService {
    private NotificationService() {}

    public static void createWelcomeNotification(String studentId) {
        Student student = Student.getStudentById(studentId);
        if (student == null) return;

        Notification n = new Notification(
                null,
                student,
                "WELCOME",
                "Welcome back",
                "Welcome back, " + student.getName() + "!",
                LocalDateTime.now(),
                false,
                "NORMAL"
        );
        Notification.saveNewNotification(n);
    }

    public static void createEnrollmentSuccess(String studentId, String classId) {
        Student student = Student.getStudentById(studentId);
        ClassModel c = ClassModel.getClassById(classId);
        if (student == null || c == null || c.getModule() == null) return;

        String moduleLabel = c.getModule().getModuleCode() + " - " + c.getModule().getModuleName();
        String title = "Enrollment Successful";
        String message = "You enrolled in " + c.getClassName() + " (" + moduleLabel + ").";

        Notification n = new Notification(
                null,
                student,
                "ENROL_SUCCESS",
                title,
                message,
                LocalDateTime.now(),
                false,
                "NORMAL"
        );
        Notification.saveNewNotification(n);
    }
}
