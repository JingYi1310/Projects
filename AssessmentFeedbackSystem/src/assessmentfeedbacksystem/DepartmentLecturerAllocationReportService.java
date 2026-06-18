package assessmentfeedbacksystem;

import java.util.*;

public class DepartmentLecturerAllocationReportService {
    // Get lecturers by academic leader
    private static List<String> getLecturerIdsByLeader(AcademicLeader al) {
        List<String> lecturerIds = new ArrayList<>();
        List<String> users = FileManager.readFile("User.txt");

        for (String line : users) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");

            if (data.length >= 12 &&
                data[7].equals("Lecturer") &&
                data[11].equals(al.getUser_id())) {

                lecturerIds.add(data[0]);
            }
        }
        return lecturerIds;
    }
    
    // Get lecturer name by lecturer id
    private static String getLecturerName(String lecturerId) {
        List<String> users = FileManager.readFile("User.txt");

        for (String line : users) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data[0].equals(lecturerId)) {
                return data[1];
            }
        }
        return "Unknown";
    }
    
    public static List<Object[]> getLecturerAllocationTableData(AcademicLeader al) {

        List<Object[]> rows = new ArrayList<>();
        List<String> lecturerIds = getLecturerIdsByLeader(al);
        List<Module> modules = Module.getAllModules();

        for (String lecturerId : lecturerIds) {

            int total = 0;
            int active = 0;
            int inactive = 0;

            for (Module m : modules) {
                if (m.getLecturer().getUser_id().equals(lecturerId)) {
                    total++;
                    if ("Active".equalsIgnoreCase(m.getStatus())) active++;
                    if ("Inactive".equalsIgnoreCase(m.getStatus())) inactive++;
                }
            }

            rows.add(new Object[]{
                lecturerId,
                getLecturerName(lecturerId),
                total,
                active,
                inactive
            });
        }
        return rows;
    }
    
    public static List<Object[]> getLecturerAllocationChartData(AcademicLeader al) {
        List<Object[]> rows = new ArrayList<>();
        List<String> lecturerIds = getLecturerIdsByLeader(al);
        List<Module> modules = Module.getAllModules();

        for (String lecturerId : lecturerIds) {

            int active = 0;
            int inactive = 0;

            for (Module m : modules) {
                if (m.getLecturer().getUser_id().equals(lecturerId)) {
                    if ("Active".equalsIgnoreCase(m.getStatus())) active++;
                    if ("Inactive".equalsIgnoreCase(m.getStatus())) inactive++;
                }
            }

            rows.add(new Object[]{
                lecturerId + " - " + getLecturerName(lecturerId),
                active,
                inactive
            });
        }
        return rows;
    }
}
