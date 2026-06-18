/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Lecturer;

import assessmentfeedbacksystem.Assessment;
import assessmentfeedbacksystem.AssessmentStatus;
import assessmentfeedbacksystem.ClassModel;
import assessmentfeedbacksystem.Lecturer;
import assessmentfeedbacksystem.Notification;
import assessmentfeedbacksystem.Result;
import assessmentfeedbacksystem.Student;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jing Yi
 */
public class ViewAssessmentDetails extends javax.swing.JPanel {
    private MainFrame mainFrame;
    private String selected_class_id;
    private String selected_assessment_id; 
    private AssessmentStatus selected_ass_status;
    private String lecturer_id;
    private int maxMarks; 
    private boolean isModuleActive;
    private List<BulkUploadRow> uploadedRows;
    private boolean saveError = false;
    /**
     * Creates new form ViewAssessmentDetails
     * @param mainFrame
     * @param selected_class_id
     * @param selected_assessment
     * @param logged_in_user
     */
    public ViewAssessmentDetails(MainFrame mainFrame, String selected_class_id, Assessment selected_assessment, Lecturer logged_in_user) {
        this.mainFrame = mainFrame;
        initComponents();
        
        DefaultTableModel previewModel = (DefaultTableModel) previewTable.getModel();
        previewModel.setRowCount(0);
        
        javax.swing.table.JTableHeader DialogTableheader = previewTable.getTableHeader();
        DialogTableheader.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        javax.swing.table.JTableHeader header = StudentListTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        String moduleStatus = ClassModel.getModuleByClassId(selected_class_id).getStatus();
        this.isModuleActive = "Active".equalsIgnoreCase(moduleStatus);
        SaveBtn.setEnabled(false);
        
        this.selected_class_id = selected_class_id;
        showStudentList(selected_assessment.getAssessmentId());
        hideResultIdColumn();
        
        String status = selected_assessment.getAssessmentStatus().toString();
        this.selected_assessment_id = selected_assessment.getAssessmentId();
        this.selected_ass_status = selected_assessment.getAssessmentStatus();
        this.lecturer_id = logged_in_user.getUser_id();
        AssessmentName.setText(selected_assessment.getAssessmentName());
        
        AssessmentType.setText(selected_assessment.getAssessmentType().toString());
        PercentageOfFinalExam.setText(Integer.toString(selected_assessment.getWeightPercentage()) + "%");
        TotalMarks.setText(Integer.toString(selected_assessment.getTotalMarks()));
        this.maxMarks = selected_assessment.getTotalMarks();
        
        AssessmentStatusTF.setText(status);
        reloadAverageMarks(selected_assessment.getAssessmentId());
        
        if ("Results Published".equalsIgnoreCase(status)){
            PublishResultBtn.setVisible(false);
            BulkUploadBtn.setVisible(false);
            SaveBtn.setVisible(false);
        }
        
        applyModuleStatusRestriction();
        
        previewTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                java.awt.Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                Object statusObj = table.getValueAt(row, 3);
                String status = statusObj == null ? "" : statusObj.toString();

                if ("Found".equals(status)) {
                    c.setBackground(new Color(220, 255, 220)); // green
                } else {
                    c.setBackground(new Color(255, 220, 220)); // red
                }

                if (isSelected) {
                    c.setBackground(new Color(184, 207, 229));
                }

                return c;
            }
        });
    }
    
    private void hideResultIdColumn() {
        StudentListTable.getColumnModel().getColumn(0).setMinWidth(0);
        StudentListTable.getColumnModel().getColumn(0).setMaxWidth(0);
        StudentListTable.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    private void showStudentList(String assessment_id){
        DefaultTableModel model = (DefaultTableModel) StudentListTable.getModel();
        model.setRowCount(0);
        
        
        ClassModel selected_class = ClassModel.getClassById(selected_class_id);
        List<Student> students = selected_class.getAllStudentsByClass();
        
        List<Result> results = Result.getStudentResultByAssessment(assessment_id);
        
        for (Student s: students){
            boolean found = false;
            for (Result r: results){
                if (s.getStudentId().equals(r.getStudent().getStudentId())){
                    model.addRow(new Object[]{r.getResultId(), s.getStudentId(), s.getName(), r.getScore(), "null".equals(r.getFeedback()) ?  "" : r.getFeedback()});
                    found = true;
                    break;
                }
            }
            if (!found){
                model.addRow(new Object[]{null, s.getStudentId(), s.getName()});
            }
        }
        
        SaveBtn.setEnabled(false);
        
        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                SaveBtn.setEnabled(true);
            }
        });
    }
    
    private void reloadAverageMarks(String ass_id){
        double average_marks = Result.getAverageMarksByAssessmentId(ass_id);
        average_marks = Double.isNaN(average_marks) ? 0 : average_marks; 
        AverageMarks.setText(Double.toString(average_marks) + "%");
    }
    
    private void applyModuleStatusRestriction() {
        if (!isModuleActive) {

            StudentListTable.setEnabled(false);
            SaveBtn.setVisible(false);
            PublishResultBtn.setVisible(false);
            BulkUploadBtn.setVisible(false);
            
            // Optional: visual feedback
            PublishResultBtn.setToolTipText("Module is inactive. Cannot publish results.");
            SaveBtn.setToolTipText("Module is inactive. Cannot edit results.");

            JOptionPane.showMessageDialog(
                null,
                "This module is inactive. Editing and publishing results are disabled.",
                "Inactive Module",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    private List<BulkUploadRow> readCSVFile(java.io.File file) {
        List<BulkUploadRow> rows = new ArrayList<>();

        List<String> existingStudentIds =
            ClassModel.getClassById(selected_class_id)
                      .getAllStudentsByClass()
                      .stream()
                      .map(Student::getStudentId)
                      .toList();

        try (java.io.BufferedReader br = 
                new java.io.BufferedReader(new java.io.FileReader(file))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                String studentId = data[0].trim();
                int marks = Integer.parseInt(data[1].trim());
                String feedback = data.length > 2 ? data[2].trim() : "";

                boolean exists = existingStudentIds.contains(studentId);

                rows.add(new BulkUploadRow(studentId, marks, feedback, exists));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid CSV file");
        }

        return rows;
    }
    
    private void loadPreviewTable(List<BulkUploadRow> rows) {
        DefaultTableModel model = (DefaultTableModel) previewTable.getModel();
        model.setRowCount(0);

        for (BulkUploadRow r : rows) {
            model.addRow(new Object[]{
                r.getStudentId(),
                r.getMarks(),
                r.getFeedback(),
                r.isExists() ? "Found" : "Not Found"
            });
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        BulkUploadPreviewDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        UploadBtn = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        previewTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ImportBtn = new javax.swing.JButton();
        CancelImportBtn = new javax.swing.JButton();
        DownloadCSVTemplateBtn = new javax.swing.JButton();
        BackBtn = new javax.swing.JButton();
        AssessmentName = new javax.swing.JLabel();
        PublishResultBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentListTable = new javax.swing.JTable();
        SaveBtn = new javax.swing.JButton();
        AssessmentType = new javax.swing.JLabel();
        AssessmentStatusTF = new javax.swing.JLabel();
        TotalMarks = new javax.swing.JLabel();
        PercentageOfFinalExam = new javax.swing.JLabel();
        AverageMarks = new javax.swing.JLabel();
        BulkUploadBtn = new javax.swing.JButton();

        BulkUploadPreviewDialog.setBackground(new java.awt.Color(255, 255, 255));
        BulkUploadPreviewDialog.setMinimumSize(new java.awt.Dimension(572, 530));
        BulkUploadPreviewDialog.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Bulk Upload ");

        UploadBtn.setBackground(new java.awt.Color(255, 255, 255));
        UploadBtn.setBorder(BorderFactory.createDashedBorder(Color.GRAY));
        UploadBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UploadBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UploadBtnMousePressed(evt);
            }
        });
        UploadBtn.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 60, 129));
        jLabel8.setText("Click to upload");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        UploadBtn.add(jLabel8, gridBagConstraints);

        jScrollPane2.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        previewTable.setAutoCreateRowSorter(true);
        previewTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        previewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student ID", "Marks", "Feedback", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        previewTable.setRowHeight(28);
        jScrollPane2.setViewportView(previewTable);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Preview Table");

        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Upload a CSV to import student results");

        ImportBtn.setBackground(new java.awt.Color(30, 58, 138));
        ImportBtn.setForeground(new java.awt.Color(255, 255, 255));
        ImportBtn.setSize(72, 23);
        ImportBtn.setText("Import");
        ImportBtn.setToolTipText("Import");
        ImportBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        ImportBtn.setContentAreaFilled(false);
        ImportBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ImportBtn.setFocusPainted(false);
        ImportBtn.setFocusable(false);
        ImportBtn.setMaximumSize(new java.awt.Dimension(29, 100));
        ImportBtn.setMinimumSize(new java.awt.Dimension(29, 100));
        ImportBtn.setOpaque(true);
        ImportBtn.setPreferredSize(new java.awt.Dimension(29, 100));
        ImportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportBtnActionPerformed(evt);
            }
        });

        CancelImportBtn.setBackground(new java.awt.Color(255, 255, 255));
        CancelImportBtn.setForeground(new java.awt.Color(30, 58, 138));
        CancelImportBtn.setSize(72, 23);
        CancelImportBtn.setText("Cancel");
        CancelImportBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        CancelImportBtn.setContentAreaFilled(false);
        CancelImportBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelImportBtn.setFocusPainted(false);
        CancelImportBtn.setFocusable(false);
        CancelImportBtn.setMaximumSize(new java.awt.Dimension(29, 100));
        CancelImportBtn.setMinimumSize(new java.awt.Dimension(29, 100));
        CancelImportBtn.setOpaque(true);
        CancelImportBtn.setPreferredSize(new java.awt.Dimension(29, 100));
        CancelImportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelImportBtnActionPerformed(evt);
            }
        });

        DownloadCSVTemplateBtn.setBackground(new java.awt.Color(255, 255, 255));
        DownloadCSVTemplateBtn.setForeground(new java.awt.Color(30, 58, 138));
        DownloadCSVTemplateBtn.setText("CSV Template");
        DownloadCSVTemplateBtn.setToolTipText("Click to download CSV Template");
        DownloadCSVTemplateBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        DownloadCSVTemplateBtn.setContentAreaFilled(false);
        DownloadCSVTemplateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DownloadCSVTemplateBtn.setOpaque(true);
        DownloadCSVTemplateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadCSVTemplateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(CancelImportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(ImportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(UploadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DownloadCSVTemplateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(DownloadCSVTemplateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelImportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout BulkUploadPreviewDialogLayout = new javax.swing.GroupLayout(BulkUploadPreviewDialog.getContentPane());
        BulkUploadPreviewDialog.getContentPane().setLayout(BulkUploadPreviewDialogLayout);
        BulkUploadPreviewDialogLayout.setHorizontalGroup(
            BulkUploadPreviewDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        BulkUploadPreviewDialogLayout.setVerticalGroup(
            BulkUploadPreviewDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(237, 241, 246));
        setMaximumSize(new java.awt.Dimension(772, 798));
        setMinimumSize(new java.awt.Dimension(772, 798));

        BackBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BackBtn.setText("<  Back");
        BackBtn.setBorder(null);
        BackBtn.setContentAreaFilled(false);
        BackBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BackBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BackBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BackBtnMouseExited(evt);
            }
        });
        BackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBtnActionPerformed(evt);
            }
        });

        AssessmentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AssessmentName.setText("Assessment Name");

        PublishResultBtn.setBackground(new java.awt.Color(30, 58, 138));
        PublishResultBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PublishResultBtn.setForeground(new java.awt.Color(255, 255, 255));
        PublishResultBtn.setText("Publish Result");
        PublishResultBtn.setToolTipText("Publish Student Result");
        PublishResultBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        PublishResultBtn.setContentAreaFilled(false);
        PublishResultBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PublishResultBtn.setOpaque(true);
        PublishResultBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PublishResultBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PublishResultBtnMouseExited(evt);
            }
        });
        PublishResultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublishResultBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Type: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Status: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Total Marks:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Percentage of Final Exam: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Average Marks (in percentage): ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Student List");

        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));

        StudentListTable.setAutoCreateRowSorter(true);
        StudentListTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        StudentListTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        StudentListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Result ID", "Student ID", "Student Name", "Marks", "Feedback"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudentListTable.setFillsViewportHeight(true);
        StudentListTable.setRowHeight(28);
        StudentListTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(StudentListTable);

        SaveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SaveBtn.setBackground(new java.awt.Color(255, 255, 255));
        SaveBtn.setForeground(new java.awt.Color(102, 102, 102));
        SaveBtn.setText("Save");
        SaveBtn.setToolTipText("Save");
        SaveBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        SaveBtn.setContentAreaFilled(false);
        SaveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveBtn.setEnabled(false);
        SaveBtn.setFocusPainted(false);
        SaveBtn.setFocusable(false);
        SaveBtn.setOpaque(true);
        SaveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveBtnMouseExited(evt);
            }
        });
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });
        SaveBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                SaveBtnPropertyChange(evt);
            }
        });

        AssessmentType.setText("jLabel7");

        AssessmentStatusTF.setText("jLabel7");

        TotalMarks.setText("jLabel7");

        PercentageOfFinalExam.setText("jLabel7");

        AverageMarks.setText("jLabel7");

        BulkUploadBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BulkUploadBtn.setForeground(new java.awt.Color(30, 58, 138));
        BulkUploadBtn.setBackground(new java.awt.Color(255, 255, 255));
        BulkUploadBtn.setText("Bulk Upload");
        BulkUploadBtn.setToolTipText("Bulk Upload");
        BulkUploadBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        BulkUploadBtn.setContentAreaFilled(false);
        BulkUploadBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BulkUploadBtn.setFocusPainted(false);
        BulkUploadBtn.setFocusable(false);
        BulkUploadBtn.setOpaque(true);
        BulkUploadBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BulkUploadBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BulkUploadBtnMouseExited(evt);
            }
        });
        BulkUploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BulkUploadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14)
                        .addComponent(AverageMarks)
                        .addGap(535, 535, 535))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BackBtn)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(AssessmentName)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PublishResultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(45, 45, 45)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(AssessmentStatusTF)
                                                .addComponent(AssessmentType)
                                                .addComponent(TotalMarks)
                                                .addComponent(PercentageOfFinalExam))))))
                            .addComponent(BulkUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AssessmentName)
                    .addComponent(PublishResultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(AssessmentType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(AssessmentStatusTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TotalMarks))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PercentageOfFinalExam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(AverageMarks))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(BulkUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        if (!SaveBtn.isEnabled()){
            mainFrame.showClassDetail(selected_class_id, selected_ass_status);
        }else{
            int result = JOptionPane.showConfirmDialog(null, "You have unsaved changes. \nDo you want to save them before leaving?","Unsaved Changes",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION){
                SaveBtnActionPerformed(evt);
            }else if (result == JOptionPane.NO_OPTION){
                mainFrame.showClassDetail(selected_class_id,selected_ass_status);
            }
            if(!saveError){
                mainFrame.showClassDetail(selected_class_id,selected_ass_status);
            }
        }
    }//GEN-LAST:event_BackBtnActionPerformed

    private void SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBtnActionPerformed
        if (StudentListTable.isEditing()) {
            StudentListTable.getCellEditor().stopCellEditing();
        }

        List<ResultRowData> rows = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) StudentListTable.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {

            Object marksObj  = model.getValueAt(row, 3);
            if (marksObj  == null || marksObj.toString().isEmpty()) continue;
            
            int marks;
            try {
                if (marksObj instanceof Integer integer) {
                    marks = integer;
                } else {
                    marks = Integer.parseInt(marksObj.toString().trim());
                }
            } catch (NumberFormatException e) {
                saveError = true;
                JOptionPane.showMessageDialog(null,
                    "Invalid marks at row " + (row + 1),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if (marks < 0 || marks > maxMarks) {
                saveError = true;
                JOptionPane.showMessageDialog(null,
                    "Marks at row " + (row + 1) + " must be between 0 and " + maxMarks,
                    "Invalid Marks",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            rows.add(new ResultRowData(
                (String) model.getValueAt(row, 0),  // resultId
                (String) model.getValueAt(row, 1),  // studentId
                marks,
                (String) model.getValueAt(row, 4)   // feedback
            ));
        }

        boolean success = Result.saveOrUpdateResults(
                selected_assessment_id,
                selected_class_id,
                lecturer_id,
                rows
        );

        JOptionPane.showMessageDialog(null, 
                success ? "Results saved successfully!" : "Failed to save results", 
                success ? "Success": "Error", 
                success? JOptionPane.INFORMATION_MESSAGE: JOptionPane.ERROR_MESSAGE);
        SaveBtn.setEnabled(false);
        
        reloadAverageMarks(selected_assessment_id);
        showStudentList(selected_assessment_id);
    }//GEN-LAST:event_SaveBtnActionPerformed

    private void PublishResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublishResultBtnActionPerformed
        if (SaveBtn.isEnabled()){
            JOptionPane.showMessageDialog(
                null,
                "Please save all changes before publishing the results.",
                "Unsaved Changes",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) StudentListTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            Object marksObj  = model.getValueAt(row, 3);
            Object feedbackObj = model.getValueAt(row, 4);
            
            if (marksObj  == null || marksObj.toString().isEmpty() || feedbackObj == null || feedbackObj.toString().isEmpty()){
                JOptionPane.showMessageDialog(
                    null,
                    "All students must have both marks and feedback before publishing results.",
                    "Incomplete Data",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }
        
        Assessment.changeAssessmentStatus(selected_assessment_id, AssessmentStatus.RESULTS_PUBLISHED.toString());
        for (int row = 0; row < model.getRowCount(); row++) {
            String studentId = model.getValueAt(row, 1).toString();

            Notification notification = new Notification(
                null, 
                Student.getStudentById(studentId),
                "grade",
                "New Results Available",
                "Your assessment results have been published. Check your Results page to view your performance.",
                java.time.LocalDateTime.now(),
                false, 
                "high"
            );

            Notification.saveNewNotification(notification); // save to file
        }
        JOptionPane.showMessageDialog(
            null,
            "Results have been published successfully.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );

        mainFrame.showClassDetail(selected_class_id, AssessmentStatus.RESULTS_PUBLISHED);
    }//GEN-LAST:event_PublishResultBtnActionPerformed

    private void BulkUploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BulkUploadBtnActionPerformed
        BulkUploadPreviewDialog.setVisible(true);
        BulkUploadPreviewDialog.setSize(572, 568);
        BulkUploadPreviewDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_BulkUploadBtnActionPerformed

    private void UploadBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UploadBtnMousePressed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileFilter(
            new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv")
        );
        int result = chooser.showOpenDialog(null);

        if (result != javax.swing.JFileChooser.APPROVE_OPTION) return;

        java.io.File file = chooser.getSelectedFile();

        uploadedRows = readCSVFile(file);
        loadPreviewTable(uploadedRows);
    }//GEN-LAST:event_UploadBtnMousePressed

    private void ImportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportBtnActionPerformed
            if (uploadedRows == null || uploadedRows.isEmpty()) return;
            DefaultTableModel studentModel =
                (DefaultTableModel) StudentListTable.getModel();

            for (BulkUploadRow r : uploadedRows) {

                if (!r.isExists()) continue; // skip invalid student IDs

                for (int i = 0; i < studentModel.getRowCount(); i++) {
                    if (studentModel.getValueAt(i, 1).equals(r.getStudentId())) {
                        studentModel.setValueAt(r.getMarks(), i, 3);
                        studentModel.setValueAt(r.getFeedback(), i, 4);
                        break;
                    }
                }
            }

            SaveBtn.setEnabled(true);
            BulkUploadPreviewDialog.dispose();
    }//GEN-LAST:event_ImportBtnActionPerformed

    private void CancelImportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelImportBtnActionPerformed
        BulkUploadPreviewDialog.dispose();
    }//GEN-LAST:event_CancelImportBtnActionPerformed

    private void DownloadCSVTemplateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownloadCSVTemplateBtnActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setDialogTitle("Save CSV Template");
        chooser.setSelectedFile(new java.io.File("student_results_template.csv"));

        int option = chooser.showSaveDialog(null);
        if (option != javax.swing.JFileChooser.APPROVE_OPTION) return;

        java.io.File file = chooser.getSelectedFile();

        try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {

            writer.write("Student ID,Marks,Feedback");

            JOptionPane.showMessageDialog(
                null,
                "CSV template downloaded successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Failed to create CSV template",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_DownloadCSVTemplateBtnActionPerformed

    private void BackBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseEntered
//        BackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        BackBtn.setForeground(Color.GRAY);
    }//GEN-LAST:event_BackBtnMouseEntered

    private void BackBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseExited
//        BackBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        BackBtn.setForeground(Color.BLACK);
    }//GEN-LAST:event_BackBtnMouseExited

    private void SaveBtnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_SaveBtnPropertyChange
        if (!"enabled".equals(evt.getPropertyName())) {
            return;
        }
        
        if (SaveBtn.isEnabled()) {
            SaveBtn.setForeground(new java.awt.Color(30, 58, 138));
            SaveBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 2, true));
            System.out.println("yes");
        } else {
            SaveBtn.setForeground(new java.awt.Color(102, 102, 102));
            SaveBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
            SaveBtn.setBackground(new java.awt.Color(255, 255, 255));
            System.out.println("no");
        }
    }//GEN-LAST:event_SaveBtnPropertyChange

    private void BulkUploadBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BulkUploadBtnMouseEntered
        BulkUploadBtn.setForeground(new java.awt.Color(255, 255, 255));
        BulkUploadBtn.setBackground(new java.awt.Color(30, 58, 138));
    }//GEN-LAST:event_BulkUploadBtnMouseEntered

    private void BulkUploadBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BulkUploadBtnMouseExited
        BulkUploadBtn.setForeground(new java.awt.Color(30, 58, 138));
        BulkUploadBtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_BulkUploadBtnMouseExited

    private void SaveBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtnMouseEntered
        if (SaveBtn.isEnabled()){
            SaveBtn.setForeground(new java.awt.Color(255, 255, 255));
            SaveBtn.setBackground(new java.awt.Color(30, 58, 138));
            SaveBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 2, true));
        }
    }//GEN-LAST:event_SaveBtnMouseEntered

    private void SaveBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveBtnMouseExited
        if (SaveBtn.isEnabled()){
            SaveBtn.setForeground(new java.awt.Color(30, 58, 138));
            SaveBtn.setBackground(new java.awt.Color(255, 255, 255));
            SaveBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 2, true));
        }
    }//GEN-LAST:event_SaveBtnMouseExited

    private void PublishResultBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PublishResultBtnMouseEntered
        PublishResultBtn.setForeground(new java.awt.Color(30, 58, 138));
        PublishResultBtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_PublishResultBtnMouseEntered

    private void PublishResultBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PublishResultBtnMouseExited
        PublishResultBtn.setForeground(new java.awt.Color(255, 255, 255));
        PublishResultBtn.setBackground(new java.awt.Color(30, 58, 138));
    }//GEN-LAST:event_PublishResultBtnMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AssessmentName;
    private javax.swing.JLabel AssessmentStatusTF;
    private javax.swing.JLabel AssessmentType;
    private javax.swing.JLabel AverageMarks;
    private javax.swing.JButton BackBtn;
    private javax.swing.JButton BulkUploadBtn;
    private javax.swing.JDialog BulkUploadPreviewDialog;
    private javax.swing.JButton CancelImportBtn;
    private javax.swing.JButton DownloadCSVTemplateBtn;
    private javax.swing.JButton ImportBtn;
    private javax.swing.JLabel PercentageOfFinalExam;
    private javax.swing.JButton PublishResultBtn;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JTable StudentListTable;
    private javax.swing.JLabel TotalMarks;
    private javax.swing.JPanel UploadBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable previewTable;
    // End of variables declaration//GEN-END:variables
}
