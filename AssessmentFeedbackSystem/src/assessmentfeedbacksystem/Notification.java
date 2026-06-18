package assessmentfeedbacksystem;

import java.time.LocalDateTime;
import java.util.*;

public class Notification implements FileSerializable {
    private String notificationId;
    private Student student;
    private String type;
    private String title;
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead;
    private String priority;
    
    private static final String NOTIFICATIONS_FILE = "Notifications.txt";
    
    public Notification() {}
    
    public Notification(String notificationId, Student student, String type,
                       String title, String message, LocalDateTime timestamp,
                       boolean isRead, String priority) {
        this.notificationId = notificationId;
        this.student = student;
        this.type = type;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
        this.priority = priority;
    }
    
    // Getters
    public String getNotificationId() { 
        return notificationId; 
    }
    
    public Student getStudent() { 
        return student; 
    }
    
    public String getType() { 
        return type; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public String getMessage() { 
        return message; 
    }
    
    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }
    
    public boolean isRead() { 
        return isRead; 
    }
    
    public String getPriority() { 
        return priority; 
    }
    
    @Override
    public String serialize() {
        return String.join(";",
                getNotificationId(),
                getStudent().getStudentId(),
                getType(),
                getTitle(),
                getMessage(),
                getTimestamp().toString(),
                String.valueOf(isRead()),
                getPriority()
        );
    }
    
    // Save new notification
    public static void saveNewNotification(Notification n) {
        if (n.getNotificationId() == null || n.getNotificationId().isEmpty()) {
            n.notificationId = generateNewNotificationId();
        }
        List<String> lines = FileManager.readFile(NOTIFICATIONS_FILE);
        lines.add(n.serialize());
        FileManager.writeFile(NOTIFICATIONS_FILE, lines, false);
        NotificationBus.notifyStudent(n.getStudent().getStudentId());
    }

    // Generate new unique notification ID
    public static String generateNewNotificationId() {
        List<String> lines = FileManager.readFile(NOTIFICATIONS_FILE);
        int maxId = 0;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length < 1) continue;
            String id = parts[0]; // first column is notificationId
            try {
                int num = Integer.parseInt(id.substring(1)); // assuming format N001
                if (num > maxId) maxId = num;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        int newId = maxId + 1;
        return String.format("N%03d", newId);
    }
    
    // Get all notifications for a student
    public static List<Notification> getStudentNotifications(String studentId) {
        List<Notification> notifications = new ArrayList<>();
        List<String> lines = FileManager.readFile(NOTIFICATIONS_FILE);
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 8 && parts[1].equals(studentId)) {
                notifications.add(new Notification(
                    parts[0],
                    Student.getStudentById(parts[1]),
                    parts[2],
                    parts[3],
                    parts[4],
                    LocalDateTime.parse(parts[5]),
                    Boolean.parseBoolean(parts[6]),
                    parts[7]
                ));
            }
        }
        
        return notifications;
    }
}
