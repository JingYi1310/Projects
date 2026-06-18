/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assessmentfeedbacksystem.GUI.AdminStaff;

import assessmentfeedbacksystem.AcademicLeader;
import assessmentfeedbacksystem.AdminStaff;
import assessmentfeedbacksystem.Lecturer;
import assessmentfeedbacksystem.User;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author samanthawoo
 */
public class DeleteAcademicLeaderFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DeleteAcademicLeaderFrame.class.getName());
    private AdminStaff logged_in_admin;
    private User deleted_user;
    private List<Lecturer> affected_lecturers = new ArrayList<>();
    private List<AcademicLeader> academic_leader_list = new ArrayList<>();
    private String[] column = {"Lecturer ID", "Lecturer Name", "New Leader ID"};
    DefaultTableModel lecturer_model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    List<String> academicLeaderOptions = new ArrayList<>();
    
    private String deleted_leader_id;
    private String new_leader_id;
    private String affected_lecturer_id;
    String deleted_academic_leader_name = null;
    
    
    /**
     * Creates new form DeleteAcademicLeaderFrame
     * @param admin
     * @param deleted_user
     */
    public DeleteAcademicLeaderFrame(AdminStaff admin, User deleted_user) {
        initComponents();
        
        this.logged_in_admin = admin;
        this.deleted_user = deleted_user;
        
        
        // Whole row selection
        lecturerAssignTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lecturerAssignTable.setRowSelectionAllowed(true);
        lecturerAssignTable.setColumnSelectionAllowed(false);
        
        
        List<User> allUsers = logged_in_admin.readUser();
        deleted_leader_id = deleted_user.getUser_id();
        
        // Set column name
        lecturer_model.setColumnIdentifiers(column);
        
        for (User u : allUsers) {
            if (u instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) u;
                if (deleted_leader_id.equals(lecturer.getLeaderId())) {
                    affected_lecturers.add(lecturer);
                }
            } else if (u instanceof AcademicLeader) {
                AcademicLeader al = (AcademicLeader) u;
                if (!deleted_leader_id.equals(al.getUser_id())) {
                    academic_leader_list.add(al);
                    academicLeaderOptions.add(al.getUser_id() + " - " + al.getName());
                } else {
                    deleted_academic_leader_name = al.getName();
                }
            }
        }
        
        String defaultLeader = academicLeaderOptions.isEmpty() ? "" : academicLeaderOptions.get(0);

        for (Lecturer lecturer : affected_lecturers) {
            Object[] row = new Object[]{
                lecturer.getUser_id(),
                lecturer.getName(),
                defaultLeader
            };
            lecturer_model.addRow(row);
        }

        newLeaderComboBox.removeAllItems();
        for (String option : academicLeaderOptions) {
            newLeaderComboBox.addItem(option);
        }

        if (lecturer_model.getRowCount() > 0) {
            lecturerAssignTable.setRowSelectionInterval(0, 0);
            int modelRow = lecturerAssignTable.convertRowIndexToModel(0);
            affected_lecturer_id = lecturer_model.getValueAt(modelRow, 0).toString();
            new_leader_id = lecturer_model.getValueAt(modelRow, 2).toString();
            affectedLecturerIdLabel.setText(affected_lecturer_id);
            newLeaderComboBox.setSelectedItem(new_leader_id);
        }

        lecturerAssignTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                getTableData();
            }
        });

        newLeaderComboBox.addActionListener(e -> {
            int viewRow = lecturerAssignTable.getSelectedRow();
            if (viewRow != -1) {
                int modelRow = lecturerAssignTable.convertRowIndexToModel(viewRow);
                new_leader_id = newLeaderComboBox.getSelectedItem().toString();
                lecturer_model.setValueAt(new_leader_id, modelRow, 2);
            }
        });
        
        currentLeaderIdLabel.setText(deleted_leader_id);
        currentLeaderNameLabel.setText(deleted_academic_leader_name);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lecturerAssignTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        currentLeaderIdLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        affectedLecturerIdLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        currentLeaderNameLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        newLeaderComboBox = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Delete User Panel");
        setResizable(false);

        jLabel4.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel4.setText("Affected Lecturers:");

        lecturerAssignTable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lecturerAssignTable.setModel(lecturer_model);
        lecturerAssignTable.setRowHeight(30);
        jScrollPane1.setViewportView(lecturerAssignTable);
        JTableHeader laHeader = lecturerAssignTable.getTableHeader();
        laHeader.setFont(new Font("Serif", Font.BOLD, 15));
        laHeader.setPreferredSize(new Dimension(laHeader.getPreferredSize().width, 35));
        laHeader.setOpaque(true);

        jButton1.setOpaque(true);
        jButton1.setContentAreaFilled(true);
        jButton1.setBackground(new java.awt.Color(70, 130, 180));
        jButton1.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save & Delete");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jPanel1.setBackground(new java.awt.Color(70, 130, 180));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Assign User – Admin Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(263, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(274, 274, 274))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 158, 78), 2));

        jLabel1.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel1.setText("Assign all affected lecturers to:");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel2.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jLabel2.setText("Current Leader ID:");

        currentLeaderIdLabel.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        currentLeaderIdLabel.setText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(currentLeaderIdLabel)
                .addGap(0, 151, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentLeaderIdLabel)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel6.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jLabel6.setText("Affected Lecturer ID:");

        affectedLecturerIdLabel.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        affectedLecturerIdLabel.setText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(affectedLecturerIdLabel)
                .addGap(0, 150, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(affectedLecturerIdLabel)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jLabel3.setText("Current Leader Name:");

        currentLeaderNameLabel.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        currentLeaderNameLabel.setText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(currentLeaderNameLabel))
                .addGap(0, 244, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentLeaderNameLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jLabel5.setText("New Leader ID:");

        newLeaderComboBox.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        newLeaderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        newLeaderComboBox.setMinimumSize(new java.awt.Dimension(63, 26));
        newLeaderComboBox.setPreferredSize(new java.awt.Dimension(63, 26));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 291, Short.MAX_VALUE))
            .addComponent(newLeaderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newLeaderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setOpaque(true);
        jButton2.setContentAreaFilled(true);
        jButton2.setBackground(new java.awt.Color(70, 130, 180));
        jButton2.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Previous");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int choice = JOptionPane.showConfirmDialog(this, 
                                     "Are you sure you want to delete the user (ID: " + deleted_leader_id + ") and update the new academic lecturer?",
                                     "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if(choice != JOptionPane.YES_OPTION){
            return;
        }
        
        List<String> updated_data = new ArrayList<>();
        int row_count = lecturerAssignTable.getRowCount();
        for (int i = 0; i < row_count; i++) {
            String lecturer_id = lecturer_model.getValueAt(i, 0).toString();
            String leaderCell = lecturer_model.getValueAt(i, 2).toString();
            String leader_id = leaderCell.split(" - ")[0];
            String line = lecturer_id + ";" + leader_id;
            updated_data.add(line);
            
            System.out.println("Updated Data Line: " + line);
        }

        String[] result = logged_in_admin.assignLecturer(updated_data, deleted_user);

        if (Boolean.parseBoolean(result[0])) {
            JOptionPane.showMessageDialog(this, result[1], "Success", JOptionPane.INFORMATION_MESSAGE);
            UserManagementFrame umf = new UserManagementFrame(logged_in_admin);
            umf.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, result[1], "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int option = JOptionPane.showConfirmDialog(this, 
            "Do you want to leave this page? Any unsaved changes will be lost.", 
            "Confirm Leave", 
            JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.YES_OPTION){
            UserManagementFrame umf = new UserManagementFrame(logged_in_admin);
            umf.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public void getTableData(){
        
        int view_row = lecturerAssignTable.getSelectedRow();
        
        if(view_row != -1){
            int model_row = lecturerAssignTable.convertRowIndexToModel(view_row);
            affected_lecturer_id = (String) lecturer_model.getValueAt(model_row, 0);
            new_leader_id = (String) lecturer_model.getValueAt(model_row, 2);
        }
        
        affectedLecturerIdLabel.setText(affected_lecturer_id);
        newLeaderComboBox.setSelectedItem(new_leader_id);
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new DeleteAcademicLeaderFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel affectedLecturerIdLabel;
    private javax.swing.JLabel currentLeaderIdLabel;
    private javax.swing.JLabel currentLeaderNameLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lecturerAssignTable;
    private javax.swing.JComboBox<String> newLeaderComboBox;
    // End of variables declaration//GEN-END:variables
}

