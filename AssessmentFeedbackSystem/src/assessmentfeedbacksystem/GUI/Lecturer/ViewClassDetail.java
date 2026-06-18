/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Lecturer;

import assessmentfeedbacksystem.Assessment;
import assessmentfeedbacksystem.AssessmentStatus;
import assessmentfeedbacksystem.ClassModel;
import assessmentfeedbacksystem.Student;
import java.awt.Color;
import java.awt.Font;
//import assessmentfeedbacksystem.Module;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jing Yi
 */
public class ViewClassDetail extends javax.swing.JPanel {
    private final MainFrame mainFrame;
    private String selected_class_id;
    private AssessmentStatus selectedStatus = null;
//    private ClassModel classModel;
    /**
     * Creates new form ViewClassDetail
     * @param selected_class_id
     * @param MainFrame
     */
    public ViewClassDetail(String selected_class_id, MainFrame MainFrame, AssessmentStatus status) {
        this.selected_class_id = selected_class_id;
        this.mainFrame = MainFrame;
        this.selectedStatus = status;
        
        String moduleStatus = ClassModel.getModuleByClassId(selected_class_id).getStatus();
        
        initComponents();
        restoreFilterSelection();
        
        if ("Inactive".equalsIgnoreCase(moduleStatus)){
            CreateNewAssessmentBtn.setVisible(false);
            StudentListTable.setEnabled(false);
            StudentListTable.getColumnModel().getColumn(2).setMinWidth(0);
            StudentListTable.getColumnModel().getColumn(2).setMaxWidth(0);
            StudentListTable.getColumnModel().getColumn(2).setWidth(0);
        }
       
        javax.swing.table.JTableHeader header = StudentListTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        new ButtonColumn(StudentListTable, 2, "Unenrol", e -> {
            int row = (int) e.getSource();
            String studentId = (String) StudentListTable.getValueAt(row, 0);
            String classId = selected_class_id; 

            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to unenrol student " + studentId + "?",
                "Confirm Unenrol",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = ClassModel.unenrolStudent(classId, studentId);
                if (success) {
                    ((DefaultTableModel) StudentListTable.getModel()).removeRow(row);
                    JOptionPane.showMessageDialog(null, "Student unenrolled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    NoOfStudent.setText(Integer.toString(ClassModel.getNoOfStudentInClass(selected_class_id)));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to unenrol student.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        showStudentList(selected_class_id);
        
        AssessmentContainer.removeAll();
        filterAssessment(selectedStatus);

        AssessmentContainer.revalidate();
        AssessmentContainer.repaint();
        
        jTabbedPane1.revalidate();
        jTabbedPane1.repaint();
        
        ClassNameLabel.setText(ClassModel.getClassById(selected_class_id).getClassName());
        Day.setText(ClassModel.getClassById(selected_class_id).getCapitalizedDay());
        ModuleName.setText(ClassModel.getClassById(selected_class_id).getModule().getModuleName());
        NoOfStudent.setText(Integer.toString(ClassModel.getNoOfStudentInClass(selected_class_id)));
        Time.setText(ClassModel.getClassById(selected_class_id).getStartTime() + " - " + ClassModel.getClassById(selected_class_id).getEndTime());
    }
    
    private javax.swing.JPanel createAssessmentPlaceholder(AssessmentCard ref) {
        javax.swing.JPanel p = new javax.swing.JPanel();
        p.setOpaque(false);
        p.setPreferredSize(ref.getPreferredSize());
        p.setMinimumSize(ref.getPreferredSize());
        p.setMaximumSize(ref.getPreferredSize());
        return p;
    }

    private void filterAssessment(AssessmentStatus status) {
        this.selectedStatus = status;  
        AssessmentContainer.removeAll();
        AssessmentContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.JPanel content = new javax.swing.JPanel();
        content.setOpaque(false);
        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));

        List<Assessment> assessments =
            Assessment.getAssessmentsByModule(
                ClassModel.getModuleByClassId(selected_class_id).getModuleId()
            );

        javax.swing.JPanel row = null;
        int col = 0;
        AssessmentCard lastCard = null;
        boolean hasData = false;

        for (Assessment a : assessments) {
            if (status != null && a.getAssessmentStatus() != status) continue;

            if (col == 0) {
                row = new javax.swing.JPanel(new java.awt.GridBagLayout());
                row.setOpaque(false);
                row.setBorder(
                    javax.swing.BorderFactory.createEmptyBorder(0, 0, 30, 0)
                );
            }

            AssessmentCard card = new AssessmentCard(a, mainFrame, selected_class_id);
            lastCard = card;
            hasData = true;

            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.gridx = col;
            gbc.gridy = 0;
            gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gbc.weightx = 0.5;
            gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;

            gbc.insets = (col == 0)
                    ? new java.awt.Insets(0, 0, 0, 30)
                    : new java.awt.Insets(0, 0, 0, 0);

            row.add(card, gbc);
            col++;

            if (col == 2) {
                content.add(row);
                col = 0;
            }
        }

        if (col == 1 && row != null && lastCard != null) {
            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 0.5;
            gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

            row.add(createAssessmentPlaceholder(lastCard), gbc);
            content.add(row);
        }

        if (!hasData) {
            content.add(new EmptyAssessment());
        }

        AssessmentContainer.add(content, java.awt.BorderLayout.NORTH);

        AssessmentContainer.revalidate();
        AssessmentContainer.repaint();
    }
    
    private void restoreFilterSelection() {
        if (selectedStatus == null) {
            allBtn.setSelected(true);
        } else if (selectedStatus == AssessmentStatus.DRAFT) {
            DraftBtn.setSelected(true);
        } else if (selectedStatus == AssessmentStatus.PUBLISHED) {
            PublishBtn.setSelected(true);
        } else if (selectedStatus == AssessmentStatus.RESULTS_PUBLISHED) {
            ResultPublishBtn.setSelected(true);
        }

        filterAssessment(selectedStatus);
    }
    
    public void showStudentList(String selected_class_id){
        DefaultTableModel model = (DefaultTableModel) StudentListTable.getModel();
        model.setRowCount(0);
        
        ClassModel selected_class = ClassModel.getClassById(selected_class_id);
        List<Student> students = selected_class.getAllStudentsByClass();
        
        for (Student s: students){
            model.addRow(new Object[]{s.getStudentId(), s.getName()});
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

        statusChoose = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        BackBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        AssessmentContainer = new javax.swing.JPanel();
        CreateNewAssessmentBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        allBtn = new javax.swing.JToggleButton();
        DraftBtn = new javax.swing.JToggleButton();
        PublishBtn = new javax.swing.JToggleButton();
        ResultPublishBtn = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        StudentListContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StudentListTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        ClassNameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ModuleName = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        Day = new javax.swing.JLabel();
        NoOfStudent = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(237, 241, 246));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 770));

        BackBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BackBtn.setText("<  Back");
        BackBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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

        jTabbedPane1.setBackground(new java.awt.Color(237, 241, 246));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(696, 26));

        jPanel3.setBackground(new java.awt.Color(237, 241, 246));

        jScrollPane2.setBackground(new java.awt.Color(237, 241, 246));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 107, 107)));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(jPanel2);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        AssessmentContainer.setBackground(new java.awt.Color(255, 255, 255));
        AssessmentContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 25));
        AssessmentContainer.setLayout(new java.awt.BorderLayout());

        CreateNewAssessmentBtn.setBackground(new java.awt.Color(30, 58, 138));
        CreateNewAssessmentBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CreateNewAssessmentBtn.setForeground(new java.awt.Color(255, 255, 255));
        CreateNewAssessmentBtn.setText("New Assessment");
        CreateNewAssessmentBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 58, 138), 1, true));
        CreateNewAssessmentBtn.setContentAreaFilled(false);
        CreateNewAssessmentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CreateNewAssessmentBtn.setFocusPainted(false);
        CreateNewAssessmentBtn.setFocusable(false);
        CreateNewAssessmentBtn.setOpaque(true);
        CreateNewAssessmentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CreateNewAssessmentBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CreateNewAssessmentBtnMouseExited(evt);
            }
        });
        CreateNewAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateNewAssessmentBtnActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));

        statusChoose.add(allBtn);
        allBtn.setText("All");
        allBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allBtn.setFocusPainted(false);
        allBtn.setFocusable(false);
        allBtn.setMaximumSize(new java.awt.Dimension(80, 34));
        allBtn.setMinimumSize(new java.awt.Dimension(80, 34));
        allBtn.setPreferredSize(new java.awt.Dimension(80, 34));
        allBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnActionPerformed(evt);
            }
        });
        jPanel5.add(allBtn);

        statusChoose.add(DraftBtn);
        DraftBtn.setText("Draft");
        DraftBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DraftBtn.setFocusPainted(false);
        DraftBtn.setFocusable(false);
        DraftBtn.setMaximumSize(new java.awt.Dimension(80, 34));
        DraftBtn.setMinimumSize(new java.awt.Dimension(80, 34));
        DraftBtn.setPreferredSize(new java.awt.Dimension(80, 34));
        DraftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DraftBtnActionPerformed(evt);
            }
        });
        jPanel5.add(DraftBtn);

        statusChoose.add(PublishBtn);
        PublishBtn.setText("Published");
        PublishBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PublishBtn.setFocusPainted(false);
        PublishBtn.setFocusable(false);
        PublishBtn.setMaximumSize(new java.awt.Dimension(100, 34));
        PublishBtn.setMinimumSize(new java.awt.Dimension(100, 34));
        PublishBtn.setPreferredSize(new java.awt.Dimension(100, 34));
        PublishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublishBtnActionPerformed(evt);
            }
        });
        jPanel5.add(PublishBtn);

        statusChoose.add(ResultPublishBtn);
        ResultPublishBtn.setText("Results Published");
        ResultPublishBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ResultPublishBtn.setFocusPainted(false);
        ResultPublishBtn.setFocusable(false);
        ResultPublishBtn.setMaximumSize(new java.awt.Dimension(135, 34));
        ResultPublishBtn.setMinimumSize(new java.awt.Dimension(135, 34));
        ResultPublishBtn.setPreferredSize(new java.awt.Dimension(135, 34));
        ResultPublishBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultPublishBtnActionPerformed(evt);
            }
        });
        jPanel5.add(ResultPublishBtn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CreateNewAssessmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(AssessmentContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateNewAssessmentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AssessmentContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("     Assessment     ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(237, 241, 246));

        StudentListContainer.setBackground(new java.awt.Color(255, 255, 255));
        StudentListContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(107, 107, 107)));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setViewportView(null);

        StudentListTable.setAutoCreateRowSorter(true);
        StudentListTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        StudentListTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        StudentListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudentListTable.setFillsViewportHeight(true);
        StudentListTable.setGridColor(new java.awt.Color(255, 255, 255));
        StudentListTable.setRowHeight(28);
        StudentListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        StudentListTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(StudentListTable);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Student List");

        javax.swing.GroupLayout StudentListContainerLayout = new javax.swing.GroupLayout(StudentListContainer);
        StudentListContainer.setLayout(StudentListContainerLayout);
        StudentListContainerLayout.setHorizontalGroup(
            StudentListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentListContainerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(StudentListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        StudentListContainerLayout.setVerticalGroup(
            StudentListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentListContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(StudentListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(StudentListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("     Student List     ", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        ClassNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ClassNameLabel.setText("Class Name");

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Module Name: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Time:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Day:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("No. of Student:");

        ModuleName.setText("jLabel5");

        Time.setText("jLabel6");

        Day.setText("jLabel7");

        NoOfStudent.setText("jLabel8");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClassNameLabel)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NoOfStudent)
                            .addComponent(ModuleName)
                            .addComponent(Day)
                            .addComponent(Time))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(ClassNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ModuleName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Time))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Day))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NoOfStudent))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackBtn)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(BackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, 785, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBtnActionPerformed
        mainFrame.showPage("Class");
    }//GEN-LAST:event_BackBtnActionPerformed

    private void CreateNewAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateNewAssessmentBtnActionPerformed
        mainFrame.createNewAssessment(selected_class_id);
    }//GEN-LAST:event_CreateNewAssessmentBtnActionPerformed

    private void DraftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DraftBtnActionPerformed
        filterAssessment(AssessmentStatus.DRAFT);
    }//GEN-LAST:event_DraftBtnActionPerformed

    private void PublishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublishBtnActionPerformed
        filterAssessment(AssessmentStatus.PUBLISHED);
    }//GEN-LAST:event_PublishBtnActionPerformed

    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        filterAssessment(null);
    }//GEN-LAST:event_allBtnActionPerformed

    private void ResultPublishBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultPublishBtnActionPerformed
        filterAssessment(AssessmentStatus.RESULTS_PUBLISHED);
    }//GEN-LAST:event_ResultPublishBtnActionPerformed

    private void BackBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseEntered
//        BackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        BackBtn.setForeground(Color.GRAY);
    }//GEN-LAST:event_BackBtnMouseEntered

    private void BackBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackBtnMouseExited
//        BackBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        BackBtn.setForeground(Color.BLACK);
    }//GEN-LAST:event_BackBtnMouseExited

    private void CreateNewAssessmentBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateNewAssessmentBtnMouseEntered
        CreateNewAssessmentBtn.setForeground(new java.awt.Color(30, 58, 138));
        CreateNewAssessmentBtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_CreateNewAssessmentBtnMouseEntered

    private void CreateNewAssessmentBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateNewAssessmentBtnMouseExited
        CreateNewAssessmentBtn.setForeground(new java.awt.Color(255, 255, 255));
        CreateNewAssessmentBtn.setBackground(new java.awt.Color(30, 58, 138));
    }//GEN-LAST:event_CreateNewAssessmentBtnMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssessmentContainer;
    private javax.swing.JButton BackBtn;
    private javax.swing.JLabel ClassNameLabel;
    private javax.swing.JButton CreateNewAssessmentBtn;
    private javax.swing.JLabel Day;
    private javax.swing.JToggleButton DraftBtn;
    private javax.swing.JLabel ModuleName;
    private javax.swing.JLabel NoOfStudent;
    private javax.swing.JToggleButton PublishBtn;
    private javax.swing.JToggleButton ResultPublishBtn;
    private javax.swing.JPanel StudentListContainer;
    private javax.swing.JTable StudentListTable;
    private javax.swing.JLabel Time;
    private javax.swing.JToggleButton allBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.ButtonGroup statusChoose;
    // End of variables declaration//GEN-END:variables
}
