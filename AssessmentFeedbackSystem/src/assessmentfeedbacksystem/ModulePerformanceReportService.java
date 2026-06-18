/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assessmentfeedbacksystem;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author xinyun
 */
public class ModulePerformanceReportService {
    public static List<Object[]> getModulePerformanceData(AcademicLeader loggedInAL) {
        List<Object[]> rows = new ArrayList<>();

        // get all modules
        List<Module> modules = Module.getAllModules();

        for (Module module : modules) {
            
            if (!module.getCreatedBy().equals(loggedInAL.getUser_id())) {
                continue;
            }

            String moduleId = module.getModuleId();
            String moduleCode = module.getModuleCode();
            String moduleName = module.getModuleName();
            String lecturerId = module.getLecturer().getUser_id();

            // get class for this module
            ClassModel classModel = ClassModel.getClassByModuleId(moduleId);
            if (classModel == null) {
                rows.add(new Object[]{
                    moduleId, moduleCode, moduleName, lecturerId, "-", "-", "-"
                });
                continue;
            }

            String classId = classModel.getClassId();

            // get students enrolled in this class
            List<String> studentIds = Enrolment.getStudentsByClassId(classId);
            if (studentIds.isEmpty()) {
                rows.add(new Object[]{
                    moduleId, moduleCode, moduleName, lecturerId, "-", "-", "-"
                });
                continue;
            }

            double totalPercentage = 0;
            int passCount = 0;
            int failCount = 0;

            // calculate percentage for each student
            for (String studentId : studentIds) {

                List<Result> results = Result.getResultsByStudentAndClass(studentId, classId);

                if (results.isEmpty()) {
                    continue;
                }

                double weightedPercentage = 0;
                double totalWeight = 0;

                for (Result r : results) {

                    Assessment assessment = Assessment.getAssessmentById(r.getAssessment().getAssessmentId());
                    if (assessment == null) continue;

                    int totalMarks = assessment.getTotalMarks();
                    int weight = assessment.getWeightPercentage();

                    if (totalMarks <= 0 || weight <= 0) continue;

                    double contribution =
                        ((double) r.getScore() / totalMarks) * weight;

                    weightedPercentage += contribution;
                    totalWeight += weight;
                }

                if (totalWeight == 0) {
                    continue;
                }

                double finalPercentage = weightedPercentage;

                totalPercentage += finalPercentage;

                // Pass / Fail decision
                Grade grade = Grade.getGradeByPercentage(finalPercentage);
                if (grade != null && Grade.isPassing(grade.getGrade())) {
                    passCount++;
                } else {
                    failCount++;
                }
            }

            int studentCount = passCount + failCount;
            
            String avgMarksStr;
            String passRateStr;
            String failRateStr;

            if (studentCount == 0) {
                avgMarksStr = "-";
                passRateStr = "-";
                failRateStr = "-";
            } else {
                double avgMarks = totalPercentage / studentCount;
                double passRate = (passCount * 100.0) / studentCount;
                double failRate = 100.0 - passRate;

                avgMarksStr = String.valueOf(round(avgMarks));
                passRateStr = String.valueOf(round(passRate));
                failRateStr = String.valueOf(round(failRate));
            }

            // add row to report
            rows.add(new Object[]{
                moduleId,
                moduleCode,
                moduleName,
                lecturerId,
                avgMarksStr,
                passRateStr,
                failRateStr
            });
        }

        return rows;
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
