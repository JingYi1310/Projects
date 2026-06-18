package assessmentfeedbacksystem;

import java.util.*;

public class ClassEnrolmentReportService {
    // Get related modules managed by a specific academic leader
    private static List<Module> getModulesByLeader(AcademicLeader al) {
        List<Module> result = new ArrayList<>();
        for (Module m : Module.getAllModules()) {
            if (m.getCreatedBy().equals(al.getUser_id())) {
                result.add(m);
            }
        }
        return result;
    }
    
    // Get enrolment table data for classes managed by a specific academic leader
    public static List<Object[]> getClassEnrolmentTableData(AcademicLeader al) {
        List<Object[]> rows = new ArrayList<>();
        List<Module> leaderModules = getModulesByLeader(al);
        List<ClassModel> classes = ClassModel.readAllClasses();

        for (ClassModel c : classes) {
            Module m = c.getModule();
            if (m == null) continue;

            boolean belongsToLeader = false;
            for (Module lm : leaderModules) {
                if (lm.getModuleId().equals(m.getModuleId())) {
                    belongsToLeader = true;
                    break;
                }
            }
            if (!belongsToLeader) continue;

            int enrolmentCount = ClassModel.getNoOfStudentInClass(c.getClassId());

            rows.add(new Object[]{
                c.getClassName(),              
                m.getModuleCode(),             
                m.getModuleName(),            
                enrolmentCount,              
                c.getCreatedAt()            
            });
        }
        return rows;
    }

    // Returns enrolment chart data for classes managed by a specific academic leader
    public static List<Object[]> getClassEnrolmentChartData(AcademicLeader al) {
        List<Object[]> rows = new ArrayList<>();
        List<Module> leaderModules = getModulesByLeader(al);
        List<ClassModel> classes = ClassModel.readAllClasses();

        for (ClassModel c : classes) {
            Module m = c.getModule();
            if (m == null) continue;

            boolean belongsToLeader = false;
            for (Module lm : leaderModules) {
                if (lm.getModuleId().equals(m.getModuleId())) {
                    belongsToLeader = true;
                    break;
                }
            }
            if (!belongsToLeader) continue;

            int enrolmentCount = ClassModel.getNoOfStudentInClass(c.getClassId());

            rows.add(new Object[]{
                c.getClassName(),   
                enrolmentCount     
            });
        }
        return rows;
    }
}
