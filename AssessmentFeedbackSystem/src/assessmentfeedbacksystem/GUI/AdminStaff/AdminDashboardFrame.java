/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assessmentfeedbacksystem.GUI.AdminStaff;

import assessmentfeedbacksystem.AdminStaff;
import assessmentfeedbacksystem.GUI.LoginFrame;
import assessmentfeedbacksystem.User;
import javax.swing.*;
import java.awt.Color;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import java.util.*;

/**
 *
 * @author samanthawoo
 */
public class AdminDashboardFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName());
    private AdminStaff logged_in_admin;
    private String[] column = {"ID", "Name", "Email", "Gender", "Age", "Phone Number", "Role", "Status", "Created By", "Department", "Leader ID"};
    
    private int adminCount = 0;
    private int leaderCount = 0;
    private int lecturerCount = 0;
    private int studentCount = 0;
    private Object Component;
    
    /**
     * Creates new form AdminDashboardFrame
     * @param admin
     */
    public AdminDashboardFrame(AdminStaff admin) {
        this.logged_in_admin = admin;
        initComponents();
        
        List<User> allUsers = admin.readUser(); 
        
        for(User u : allUsers) {
            String role = u.getRole();
            switch(role) {
                case "Admin Staff":
                    adminCount++;
                    break;
                case "Academic Leader":
                    leaderCount++;
                    break;
                case "Lecturer":
                    lecturerCount++;
                    break;
                case "Student":
                    studentCount++;
                    break;
            }
        }
        
        jLabel12.setText("Academic Leader: " + leaderCount);
        jLabel13.setText("Lecturer: " + lecturerCount);
        jLabel14.setText("Student: " + studentCount);
        
        jLabel19.setText(String.valueOf(admin.getTotalClasses()));
        jLabel21.setText(String.valueOf(admin.getAvailableModuleNum()));
        
        // Add hover effect
        userTab.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 70, 140)));
                userTab.setBackground(new Color(245, 245, 245));
                jLabel2.setForeground(new java.awt.Color(0, 70, 140));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 120, 215)));
                userTab.setBackground(new Color(255, 255, 255));
                jLabel2.setForeground(new java.awt.Color(0, 120, 215));
            }
        });
        
        // Add hover effect
        classTab.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                classTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 70, 140)));
                classTab.setBackground(new Color(245, 245, 245));
                jLabel6.setForeground(new java.awt.Color(0, 70, 140));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                classTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 120, 215)));
                classTab.setBackground(new Color(255, 255, 255));
                jLabel6.setForeground(new java.awt.Color(0, 120, 215));
            }
        });
        
        // Add hover effect
        gradeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                gradeTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 70, 140)));
                gradeTab.setBackground(new Color(245, 245, 245));
                jLabel4.setForeground(new java.awt.Color(0, 70, 140));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gradeTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(0, 120, 215)));
                gradeTab.setBackground(new Color(255, 255, 255));
                jLabel4.setForeground(new java.awt.Color(0, 120, 215));
            }
        });
        
        // Add hover effect
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setForeground(new java.awt.Color(237, 112, 107));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setForeground(Color.RED);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        sidebarPanel = new javax.swing.JPanel();
        userTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        classTab = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        gradeTab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(85, 128, 176));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("System Administration Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        sidebarPanel.setBackground(new java.awt.Color(255, 255, 255));

        userTab.setBackground(new java.awt.Color(255, 255, 255));
        Color buttonBlue = new Color(0, 120, 215);
        userTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, buttonBlue));
        userTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userTabMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                userTabMouseReleased(evt);
            }
        });
        userTab.setLayout(new java.awt.GridBagLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 120, 215));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("👤   User");
        userTab.add(jLabel2, new java.awt.GridBagConstraints());

        classTab.setBackground(new java.awt.Color(255, 255, 255));
        classTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, buttonBlue));
        classTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                classTabMouseReleased(evt);
            }
        });
        classTab.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 120, 215));
        jLabel6.setText("📘   Class");
        classTab.add(jLabel6, new java.awt.GridBagConstraints());

        gradeTab.setBackground(new java.awt.Color(255, 255, 255));
        gradeTab.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, buttonBlue));
        gradeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gradeTabMousePressed(evt);
            }
        });
        gradeTab.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 120, 215));
        jLabel4.setText("📝   Grade");
        gradeTab.add(jLabel4, new java.awt.GridBagConstraints());

        logoutButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 0, 0));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/LogoutIcon.png"))); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setBorder(null);
        logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/LogoutSelected.png"))); // NOI18N
        logoutButton.addActionListener(this::logoutButtonActionPerformed);

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(classTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gradeTab, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(userTab, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(classTab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gradeTab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(sidebarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 158, 78), 2));
        jPanel2.setMaximumSize(new java.awt.Dimension(732, 151));
        jPanel2.setMinimumSize(new java.awt.Dimension(732, 151));

        LocalDate today = LocalDate.now();
        String day = today.format(DateTimeFormatter.ofPattern("EEEE"));
        String date = today.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        jLabel1.setBackground(new java.awt.Color(200, 200, 200));
        jLabel1.setFont(new java.awt.Font("Serif", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText(day + ", " + date);

        welcomeLabel.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        welcomeLabel.setText("Welcome Back, " + logged_in_admin.getName());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(3, 2, 0, 10));

        jLabel7.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel7.setText("ID: " + logged_in_admin.getUser_id());
        jPanel3.add(jLabel7);

        jLabel9.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel9.setText("DOB: " + logged_in_admin.getDob());
        jPanel3.add(jLabel9);

        jLabel3.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel3.setText("Email: " + logged_in_admin.getEmail());
        jPanel3.add(jLabel3);

        jLabel10.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel10.setText("Contact Number: " + logged_in_admin.getContact_no());
        jPanel3.add(jLabel10);

        String genderValue = logged_in_admin.getGender();
        String gender = "";
        if(genderValue.equals("F")){
            gender = "Female";
        }else if(genderValue.equals("M")){
            gender = "Male";
        }
        jLabel8.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel8.setText("Gender: " + gender);
        jPanel3.add(jLabel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeLabel)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 91, 730, 150));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        Color myGreen = new Color(80, 158, 78);
        jPanel4.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, myGreen));

        jLabel11.setBackground(new java.awt.Color(80, 158, 48));
        jLabel11.setFont(new java.awt.Font("Serif", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(80, 158, 48));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Overview");

        jLabel14.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(80, 158, 48));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("");

        jLabel12.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(80, 158, 48));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("");

        jLabel13.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(80, 158, 48));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 260, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        Color myBlue = new Color(0, 120, 215);
        jPanel5.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, myBlue));

        jLabel15.setBackground(new java.awt.Color(0, 120, 215));
        jLabel15.setFont(new java.awt.Font("Serif", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 120, 215));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Total Class");

        jLabel19.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 120, 215));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, 155));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        Color myOrange = new Color(255, 165, 0);
        jPanel6.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, myOrange));

        jLabel16.setBackground(new java.awt.Color(255, 165, 0));
        jLabel16.setFont(new java.awt.Font("Serif", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 165, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Available Module");

        jLabel21.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 165, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, 160));

        setSize(new java.awt.Dimension(940, 651));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userTabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTabMouseReleased
        UserManagementFrame umf = new UserManagementFrame(logged_in_admin);
        umf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_userTabMouseReleased

    private void gradeTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gradeTabMousePressed
        UpdateGradeFrame ugf = new UpdateGradeFrame(logged_in_admin);
        ugf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_gradeTabMousePressed

    private void classTabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classTabMouseReleased
        CreateClassFrame ccf = new CreateClassFrame(logged_in_admin);
        ccf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_classTabMouseReleased

    private void userTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_userTabMouseEntered

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

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
//        java.awt.EventQueue.invokeLater(() -> new AdminDashboardFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel classTab;
    private javax.swing.JPanel gradeTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel userTab;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
