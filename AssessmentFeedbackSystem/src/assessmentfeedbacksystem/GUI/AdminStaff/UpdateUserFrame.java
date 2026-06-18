/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assessmentfeedbacksystem.GUI.AdminStaff;

import assessmentfeedbacksystem.AcademicLeader;
import assessmentfeedbacksystem.AdminStaff;
import assessmentfeedbacksystem.Lecturer;
import assessmentfeedbacksystem.Student;
import assessmentfeedbacksystem.User;
import java.util.*;
import javax.swing.*;
import java.time.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author samanthawoo
 */
public class UpdateUserFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UpdateUserFrame.class.getName());
    private AdminStaff logged_in_admin ;
    private User selected_user = null;
    private String[] departments = {
        "Computing",
        "Psychology",
        "Business"
    };
    DefaultComboBoxModel<String> leaderModel = new DefaultComboBoxModel<>();
    private Student student;
    private AcademicLeader academic_leader;
    private Lecturer lecturer;
    
    /**
     * Creates new form UpdateUserFrame
     */
    public UpdateUserFrame(AdminStaff admin, User selected_user) {
        initComponents();

        JTextField dobTextField = ((JTextField) dobChooser.getDateEditor().getUiComponent());
        dobTextField.setEditable(false);

        this.logged_in_admin = admin;
        
        // Reset field - see which field is not abled to modified
        id_field.setEditable(false);
        password_field.setEditable(false);
        roleComboBox.setEnabled(false);
        
        departmentPanel.setVisible(false);
        leaderPanel.setVisible(false);
        
        // Display data
        List<User> allUsers = logged_in_admin.readUser();
        for(User u: allUsers){
            
            String user_id = u.getUser_id();
            String selected_user_id = selected_user.getUser_id();
            
            if(selected_user_id.equalsIgnoreCase(user_id)){
                this.selected_user = u;
                break;
            }
            
        }
        
        // Put leader Id to combo box
        for(User u: allUsers){
            if(u instanceof AcademicLeader){
                leaderModel.addElement(u.getUser_id());
            }
        }
        
        if(this.selected_user == null){
            JOptionPane.showMessageDialog(this, "User record not found. Please select a valid user.", "User not found", JOptionPane.ERROR_MESSAGE);
//            disableForm();
        }else{
            
            String genderCode = this.selected_user.getGender();
            String genderText;
            
            if ("F".equalsIgnoreCase(genderCode)) {
                genderText = "Female";
            } else if ("M".equalsIgnoreCase(genderCode)) {
                genderText = "Male";
            } else {
                genderText = "Other"; // optional fallback
            }
            
            System.out.println("Role:" + this.selected_user.getRole());
            System.out.println("Hello");
            // Populate fields
            id_field.setText(this.selected_user.getUser_id());
            name_field.setText(this.selected_user.getName());
            email_field.setText(this.selected_user.getEmail());
            password_field.setText(this.selected_user.getPassword());
            genderComboBox.setSelectedItem(genderText);
            contact_no_field.setText(this.selected_user.getContact_no());
            roleComboBox.setSelectedItem(selected_user.getRole());
//           
            
            LocalDate dob = this.selected_user.getDob();
            if (dob != null) {
                Date dobDate = Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
                dobChooser.setDate(dobDate);
            } else {
                dobChooser.setDate(null);
            }
            
            if (this.selected_user instanceof Lecturer) {
                
                // Display panel
                departmentPanel.setVisible(true);
                leaderPanel.setVisible(true);
                
                lecturer = (Lecturer) this.selected_user;
                departmentComboBox.setSelectedItem(lecturer.getDepartment());
                leaderComboBox.setSelectedItem(lecturer.getLeaderId());
                

            }else if(this.selected_user instanceof AcademicLeader){
                
                // Display panel
                departmentPanel.setVisible(true);
                leaderPanel.setVisible(false);
                
                academic_leader = (AcademicLeader) this.selected_user;
                departmentComboBox.setSelectedItem(academic_leader.getDepartment());
                
            }else if(this.selected_user instanceof Student){
                
                // Display panel
                departmentPanel.setVisible(false);
                leaderPanel.setVisible(false);
                
                student = (Student) this.selected_user;
                
            }
        }
        
        // Capitalize letter
        ((AbstractDocument) name_field.getDocument()).setDocumentFilter(new UpdateUserFrame.CapitalizeFilter());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        email_field = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        dobChooser = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        contact_no_field = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        password_field = new javax.swing.JPasswordField();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        roleComboBox = new javax.swing.JComboBox<>();
        departmentPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox<>();
        leaderPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        leaderComboBox = new javax.swing.JComboBox<>();
        id_field1 = new javax.swing.JTextField();
        name_field1 = new javax.swing.JTextField();
        email_field1 = new javax.swing.JTextField();
        roleComboBox1 = new javax.swing.JComboBox<>();
        genderComboBox1 = new javax.swing.JComboBox<>();
        contact_no_field1 = new javax.swing.JTextField();
        password_field1 = new javax.swing.JPasswordField();
        previousButton1 = new javax.swing.JButton();
        dobChooser1 = new com.toedter.calendar.JDateChooser();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update User Panel");
        setResizable(false);

        previousButton.setOpaque(true);
        previousButton.setContentAreaFilled(true);
        previousButton.setBackground(new java.awt.Color(70, 130, 180));
        previousButton.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        previousButton.setForeground(new java.awt.Color(255, 255, 255));
        previousButton.setText("Previous");
        previousButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        previousButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        previousButton.addActionListener(this::previousButtonActionPerformed);

        jPanel1.setBackground(new java.awt.Color(70, 130, 180));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Update User – Admin Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 158, 78), 2));

        jLabel1.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        jLabel1.setText("Update User");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(341, 26));
        jPanel2.setSize(new java.awt.Dimension(341, 26));

        jLabel2.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel2.setText("ID:");

        id_field.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        id_field.setForeground(new java.awt.Color(51, 51, 51));
        id_field.addActionListener(this::id_fieldActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addComponent(id_field)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel3.setText("Name:");

        name_field.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        name_field.addActionListener(this::name_fieldActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3))
            .addComponent(name_field)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel4.setText("Email:");

        email_field.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
            .addComponent(email_field)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel7.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel7.setText("Gender:");

        genderComboBox.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7))
            .addComponent(genderComboBox)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel8.setText("Date of Birth:");

        dobChooser.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dobChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel8))
            .addComponent(dobChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel9.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel9.setText("Phone Number:");

        contact_no_field.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        contact_no_field.addActionListener(this::contact_no_fieldActionPerformed);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(contact_no_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contact_no_field)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel5.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel5.setText("Password:");

        password_field.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel6.setText("Role:");

        roleComboBox.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Role", "Academic Leader", "Lecturer", "Student" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6))
        );

        departmentPanel.setBackground(new java.awt.Color(255, 255, 255));
        departmentPanel.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel10.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel10.setText("Department:");

        departmentComboBox.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        departmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(departments));

        javax.swing.GroupLayout departmentPanelLayout = new javax.swing.GroupLayout(departmentPanel);
        departmentPanel.setLayout(departmentPanelLayout);
        departmentPanelLayout.setHorizontalGroup(
            departmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, departmentPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        departmentPanelLayout.setVerticalGroup(
            departmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel10)
        );

        leaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        leaderPanel.setPreferredSize(new java.awt.Dimension(341, 26));

        jLabel11.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        jLabel11.setText("Leader ID:");

        leaderComboBox.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        leaderComboBox.setModel(leaderModel);

        javax.swing.GroupLayout leaderPanelLayout = new javax.swing.GroupLayout(leaderPanel);
        leaderPanel.setLayout(leaderPanelLayout);
        leaderPanelLayout.setHorizontalGroup(
            leaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaderPanelLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(leaderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        leaderPanelLayout.setVerticalGroup(
            leaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(leaderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel11)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(departmentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(leaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(leaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        id_field1.addActionListener(this::id_fieldActionPerformed);

        name_field1.addActionListener(this::name_fieldActionPerformed);

        email_field1.addActionListener(this::email_field1ActionPerformed);

        roleComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Role", "Academic Leader", "Lecturer", "Student" }));

        genderComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        previousButton1.setText("Previous");
        previousButton1.addActionListener(this::previousButtonActionPerformed);

        saveButton.setOpaque(true);
        saveButton.setContentAreaFilled(true);
        saveButton.setFont(new java.awt.Font("Serif", 3, 18)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 0, 0));
        saveButton.setText("Save");
        saveButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.addActionListener(this::saveButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void id_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_fieldActionPerformed

    private void name_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_fieldActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        UserManagementFrame umf = new UserManagementFrame(logged_in_admin);
        this.dispose();
        umf.setVisible(true);
    }//GEN-LAST:event_previousButtonActionPerformed

    private void contact_no_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contact_no_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contact_no_fieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // ID, role cannot modified
        // Password can reset
        
        // Get data
//        String id = id_field.getText().trim();

        // Name validation
        String name = name_field.getText().trim();
        if (!name.matches("[a-zA-Z./]+( [a-zA-Z./]+)*")) {
            JOptionPane.showMessageDialog(this, 
                "Name can only contain letters, spaces, periods, and slashes.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (name.length() < 2 || name.length() > 50) {
            JOptionPane.showMessageDialog(this, "Name must be between 2 and 50 characters", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Email Validation
        String email = email_field.getText().trim();
        String email_pattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(email_pattern)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Check if email unique
        boolean emailExists = false;
        List<User> allUsers = logged_in_admin.readUser();
        for(User u: allUsers){
            if (u.getUser_id().equals(selected_user.getUser_id())) {
                continue;
            }
            
            String email_file = u.getEmail();
            
            if (email_file.equalsIgnoreCase(email)) {
                emailExists = true;
                break;
            }

        }
        
        if (emailExists) {
            JOptionPane.showMessageDialog(this, "Email already exists. Please use another email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        char[] passwordChars = password_field.getPassword();
        String password = new String(passwordChars).trim();
        

        String gender = "";
        String genderStr = (String) genderComboBox.getSelectedItem();
        if(genderStr.equals("Female")){
            gender = "F";
        }else if(genderStr.equals("Male")){
            gender = "M";
        }
        
        // DOB validation
        String role = (String) roleComboBox.getSelectedItem();
        Date selected_date = dobChooser.getDate();
        if(selected_date == null){
            JOptionPane.showMessageDialog(this, "Please select a date of birth.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // convert to LocalDate
        LocalDate dob = selected_date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(); // output will be yyyy:mm:dd
        
        int min_age;
        int max_age = 100; 
        switch(role){
            case "Academic Leader":
                min_age = 30;
                break;
            case "Lecturer":
                min_age = 25;
                break;
            case "Student":
                min_age = 18;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please select a valid role before entering date of birth", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
        }
        
        // calculate difference
        LocalDate today = LocalDate.now();
        LocalDate earliestAllowedDate = today.minusYears(max_age);
        LocalDate latestAllowedDate = today.minusYears(min_age);
        
        if(dob.isAfter(latestAllowedDate)){
            JOptionPane.showMessageDialog(this, 
                    String.format("Selected date of birth does not meet the minimum age requirement of %d for %s", min_age, role),
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(dob.isBefore(earliestAllowedDate)){
            JOptionPane.showMessageDialog(this, 
                String.format("Selected date of birth exceeds the maximum age limit of %d for %s", max_age, role),
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Phone number validation
        String contact_no = contact_no_field.getText().trim();

        if (!contact_no.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, 
                "Phone number must be exactly 10 digits (e.g., 0123456789)", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean phoneExists = false;
        for(User u: allUsers){
            if (u.getUser_id().equals(selected_user.getUser_id())) {
                continue;
            }
            
            String contact_no_file = u.getContact_no();
            
            if (contact_no_file.equals(contact_no)) {
                phoneExists = true;
                break;
            }

        }
        
        if (phoneExists) {
            JOptionPane.showMessageDialog(this, "Phone number already exists. Please use another phone number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Use setter for update
        selected_user.setName(name);
        selected_user.setEmail(email);
        selected_user.setPassword(password);
        selected_user.setGender(gender);
        selected_user.setDob(dob);
        selected_user.setContact_no(contact_no);
        
        if (academic_leader != null) {
            academic_leader.setDepartment((String) departmentComboBox.getSelectedItem());
        }
        
        if (lecturer != null) {
            lecturer.setDepartment((String) departmentComboBox.getSelectedItem());
            lecturer.setLeaderId((String) leaderComboBox.getSelectedItem());
        }

        String[] update_user_status = logged_in_admin.updateUser(selected_user);
        boolean status = Boolean.parseBoolean(update_user_status[0]);
        String status_message = update_user_status[1];
        
        if (status) {
            JOptionPane.showMessageDialog(this, status_message);
            this.dispose();
            
            UserManagementFrame umf = new UserManagementFrame(logged_in_admin);
            umf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, status_message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void email_field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_field1ActionPerformed

    // UI improvement capitalize letter
    private class CapitalizeFilter extends DocumentFilter {

        private String capitalizeWords(String text) {
            StringBuilder sb = new StringBuilder();
            boolean capitalizeNext = true;

            for (char c : text.toCharArray()) {
                if (Character.isWhitespace(c)) {
                    sb.append(c);
                    capitalizeNext = true;   // next letter after space is capitalized
                } else {
                    if (capitalizeNext && Character.isLetter(c)) {
                        sb.append(Character.toUpperCase(c));
                        capitalizeNext = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                        capitalizeNext = false;
                    }
                }
            }
            return sb.toString();
        }


        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {

            String current = fb.getDocument().getText(0, fb.getDocument().getLength());
            String result = capitalizeWords(
                    current.substring(0, offset) + string + current.substring(offset)
            );
            fb.replace(0, fb.getDocument().getLength(), result, attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {

            String current = fb.getDocument().getText(0, fb.getDocument().getLength());
            String result = capitalizeWords(
                    current.substring(0, offset) + text + current.substring(offset + length)
            );
            fb.replace(0, fb.getDocument().getLength(), result, attrs);
        }
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
//        java.awt.EventQueue.invokeLater(() -> new UpdateUserFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contact_no_field;
    private javax.swing.JTextField contact_no_field1;
    private javax.swing.JComboBox<String> departmentComboBox;
    private javax.swing.JPanel departmentPanel;
    private com.toedter.calendar.JDateChooser dobChooser;
    private com.toedter.calendar.JDateChooser dobChooser1;
    private javax.swing.JTextField email_field;
    private javax.swing.JTextField email_field1;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JComboBox<String> genderComboBox1;
    private javax.swing.JTextField id_field;
    private javax.swing.JTextField id_field1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JComboBox<String> leaderComboBox;
    private javax.swing.JPanel leaderPanel;
    private javax.swing.JTextField name_field;
    private javax.swing.JTextField name_field1;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPasswordField password_field1;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton previousButton1;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JComboBox<String> roleComboBox1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    private void disableForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
