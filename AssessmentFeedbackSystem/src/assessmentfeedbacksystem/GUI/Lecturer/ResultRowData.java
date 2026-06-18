/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Lecturer;

/**
 *
 * @author Jing Yi
 */
public class ResultRowData {

    private String resultId;   
    private String studentId;
    private int marks;
    private String feedback;

    public ResultRowData(String resultId, String studentId, int marks, String feedback) {
        this.resultId = resultId;
        this.studentId = studentId;
        this.marks = marks;
        this.feedback = feedback;
    }

    public String getResultId() { return resultId; }
    public String getStudentId() { return studentId; }
    public int getMarks() { return marks; }
    public String getFeedback() { return feedback; }
}