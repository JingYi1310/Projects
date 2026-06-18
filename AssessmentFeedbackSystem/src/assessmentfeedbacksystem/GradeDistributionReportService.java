package assessmentfeedbacksystem;

import java.util.*;

public class GradeDistributionReportService {
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
    
    private static List<String> getAssessmentIdsByLecturers(List<String> lecturerIds) {

        List<String> assessmentIds = new ArrayList<>();
        List<String> assessments = FileManager.readFile("Assessment.txt");

        for (String line : assessments) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");

            if (data.length >= 9 &&
                data[6].equals("Results Published") &&
                lecturerIds.contains(data[7])) {

                assessmentIds.add(data[0]);
            }
        }
        return assessmentIds;
    }
    
    public static List<Object[]> getGradeDistributionTableData(AcademicLeader al) {

        List<Object[]> rows = new ArrayList<>();

        List<Grade> grades = Grade.getAllGrades();
        List<String> lecturerIds = getLecturerIdsByLeader(al);
        List<String> assessmentIds = getAssessmentIdsByLecturers(lecturerIds);
        List<String> results = FileManager.readFile("Result.txt");
        int totalStudents = 0;

        for (Grade g : grades) {

            int count = 0;

            for (String line : results) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                if (data.length >= 9 &&
                    assessmentIds.contains(data[2]) &&
                    data[4].equals(g.getGrade())) {
                    
                    count++;
                }
            }

            totalStudents += count;

            rows.add(new Object[]{
                g.getGrade(),
                g.getDescription(),
                g.getGpa(),
                count,
                0.0   
            });
        }
        
        for (Object[] row : rows) {
            int count = (int) row[3];
            double percentage = totalStudents > 0
                ? (count * 100.0 / totalStudents)
                : 0.0;
            row[4] = Math.round(percentage * 100.0) / 100.0;
        }

        return rows;
    }
    
    public static List<Object[]> getGradeDistributionChartData(AcademicLeader al) {

        List<Object[]> rows = new ArrayList<>();

        for (Object[] row : getGradeDistributionTableData(al)) {
            rows.add(new Object[]{
                row[0], 
                row[3]  
            });
        }

        return rows;
    }
}
