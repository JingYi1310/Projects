package assessmentfeedbacksystem;

import java.util.*;

public class LecturerPerformanceReportService {
    private static List<String> getLecturerIdsByLeader(AcademicLeader al) {

        List<String> lecturerIds = new ArrayList<>();
        List<String> users = FileManager.readFile("User.txt");

        for (String line : users) {

            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(";");

            if (data.length >= 12) {
                String role = data[7];
                String leaderId = data[11];

                if (role.equals("Lecturer")
                        && leaderId.equals(al.getUser_id())) {

                    lecturerIds.add(data[0]); // lecturer user_id
                }
            }
        }
        return lecturerIds;
    }
    
    private static String getLecturerDisplayName(String lecturerId) {

        List<String> users = FileManager.readFile("User.txt");

        for (String line : users) {
            if (line == null || line.trim().isEmpty()) continue;

            String[] data = line.split(";");

            if (data.length >= 2 && data[0].equals(lecturerId)) {
                return lecturerId + " - " + data[1];
            }
        }
        return lecturerId;
    }
    
    public static List<Object[]> getLecturerAverageRatings(AcademicLeader al) {

        List<Object[]> results = new ArrayList<>();

        List<String> lecturerIds = getLecturerIdsByLeader(al);
        List<String> feedbacks = FileManager.readFile("Feedback.txt");

        for (String lecturerId : lecturerIds) {

            int totalRating = 0;
            int count = 0;

            for (String line : feedbacks) {

                if (line == null || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(";");

                // Feedback.txt
                // [1] lecturer_id
                // [2] rating
                if (data.length >= 3 && data[1].equals(lecturerId)) {

                    try {
                        int rating = Integer.parseInt(data[2]);
                        totalRating += rating;
                        count++;
                    } catch (NumberFormatException e) {
                        // ignore invalid rating
                    }
                }
            }

            if (count > 0) {
                double average = (double) totalRating / count;
                String displayName = getLecturerDisplayName(lecturerId);
                results.add(new Object[]{displayName, average});
            }
        }

        return results;
    }
    
    public static List<Object[]> getLecturerFeedbackTableData(AcademicLeader al) {

        List<Object[]> rows = new ArrayList<>();

        List<String> lecturerIds = getLecturerIdsByLeader(al);
        List<String> feedbacks = FileManager.readFile("Feedback.txt");

        for (String line : feedbacks) {

            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] data = line.split(";");
            
            if (data.length >= 7) {

                String lecturerId = data[1];

                if (!lecturerIds.contains(lecturerId)) {
                    continue;
                }

                String rating = data[2];
                String comment = data[3];
                String classId = data[6];

                ClassModel classModel = ClassModel.getClassById(classId);
                String className =
                        (classModel != null)
                                ? classModel.getClassName()
                                : "Unknown";

                rows.add(new Object[]{
                    lecturerId,
                    className,
                    rating,
                    comment
                });
            }
        }

        return rows;
    }
}
