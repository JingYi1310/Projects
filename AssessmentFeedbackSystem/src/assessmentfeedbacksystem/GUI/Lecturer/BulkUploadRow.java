/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Lecturer;

/**
 *
 * @author Jing Yi
 */
public class BulkUploadRow {
    public String studentId;
    public Integer marks;
    public String feedback;
    public boolean exists;

    public BulkUploadRow(String studentId, Integer marks, String feedback, boolean exists) {
        this.studentId = studentId;
        this.marks = marks;
        this.feedback = feedback;
        this.exists = exists;
    }
    
    public String getStudentId() {
        return studentId;
    }

    public int getMarks() {
        return marks;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isExists() {
        return exists;
    }
}
