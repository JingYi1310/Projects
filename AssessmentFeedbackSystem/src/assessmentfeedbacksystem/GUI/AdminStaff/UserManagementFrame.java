/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assessmentfeedbacksystem.GUI.AdminStaff;

import assessmentfeedbacksystem.AcademicLeader;
import assessmentfeedbacksystem.AdminStaff;
import assessmentfeedbacksystem.GUI.AdminStaff.CreateUserFrame;
import assessmentfeedbacksystem.GUI.AdminStaff.DeleteAcademicLeaderFrame;
import assessmentfeedbacksystem.Lecturer;
import assessmentfeedbacksystem.Student;
import assessmentfeedbacksystem.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author samanthawoo
 */
public class UserManagementFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserManagementFrame.class.getName());
    private AdminStaff logged_in_admin;
    private String[] academic_leader_column = {"ID", "Name", "Email", "Gender", "Date of Birth", "Phone Number", "Created By", "Created At", "Department"};
    private String[] lecturer_column = {"ID", "Name", "Email", "Gender", "Date of Birth", "Phone Number", "Created By", "Created At", "Department", "Leader ID"};
    private String[] student_column = {"ID", "Name", "Email", "Gender", "Date of Birth", "Phone Number", "Created By", "Created At"};
    
    // Set table is not editable
    DefaultTableModel academic_leader_model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel lecturer_model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel student_model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    TableRowSorter<DefaultTableModel> alSorter = new TableRowSorter<>(academic_leader_model);
    TableRowSorter<DefaultTableModel> lSorter = new TableRowSorter<>(lecturer_model);
    TableRowSorter<DefaultTableModel> sSorter = new TableRowSorter<>(student_model);
    
    /**
     * Creates new form UserManagementFrame
     */
    public UserManagementFrame(AdminStaff admin) {
        initComponents();
        
        this.setResizable(false);
        
        this.logged_in_admin = admin;
        
        // Set column name
        academic_leader_model.setColumnIdentifiers(academic_leader_column);
        lecturer_model.setColumnIdentifiers(lecturer_column);
        student_model.setColumnIdentifiers(student_column);
        
        // Set whole row selection
        academicLeaderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        academicLeaderTable.setRowSelectionAllowed(true);
        academicLeaderTable.setColumnSelectionAllowed(false);

        lecturerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lecturerTable.setRowSelectionAllowed(true);
        lecturerTable.setColumnSelectionAllowed(false);

        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowSelectionAllowed(true);
        studentTable.setColumnSelectionAllowed(false);
        
        // Extract user data in list(object type)
        List<User> allUsers = logged_in_admin.readUser();

        
        // Display user data into table
        for(User u: allUsers){
            if(u instanceof AcademicLeader){
                AcademicLeader al = (AcademicLeader) u;
                
                Object[] row = new Object[]{
                    al.getUser_id(),
                    al.getName(),
                    al.getEmail(),
                    al.getGender(),
                    al.getDob(),
                    al.getContact_no(),
                    al.getCreated_by(),
                    al.getCreated_at(),
                    al.getDepartment()
                };
                
                System.out.println(Arrays.toString(row));
                academic_leader_model.addRow(row);
                
            }else if(u instanceof Lecturer){
                Lecturer l = (Lecturer) u;
                
                Object[] row = new Object[]{
                    l.getUser_id(),
                    l.getName(),
                    l.getEmail(),
                    l.getGender(),
                    l.getDob(),
                    l.getContact_no(),
                    l.getCreated_by(),
                    l.getCreated_at(),
                    l.getDepartment(),
                    l.getLeaderId()
                };
                
                System.out.println(Arrays.toString(row));
                lecturer_model.addRow(row);
                
            }else if(u instanceof Student){
                Student s = (Student) u;
                
                Object[] row = new Object[]{
                    s.getUser_id(),
                    s.getName(),
                    s.getEmail(),
                    s.getGender(),
                    s.getDob(),
                    s.getContact_no(),
                    s.getCreated_by(),
                    s.getCreated_at()
                };
                
                System.out.println(Arrays.toString(row));
                student_model.addRow(row);
                
            }
        }
        
        // Clear the previous selected row
        setupTableSelectionListeners();
        
        // Search function
        searchComboBox.removeAllItems();
        setupSearchComboBoxListener();
        
        academicLeaderTable.setRowSorter(alSorter);
        lecturerTable.setRowSorter(lSorter);
        studentTable.setRowSorter(sSorter);
        
        // Sorting function
        setupSortingAndRoleBox();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        academicLeaderTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lecturerTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        updateUserButton = new javax.swing.JButton();
        createUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        roleBox = new javax.swing.JComboBox<>();
        sortingBox = new javax.swing.JComboBox<>();
        sortingButton = new javax.swing.JButton();
        asc_desc_box = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        searchBar = new javax.swing.JTextField();
        searchComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Management Panel");
        setBackground(new java.awt.Color(245, 245, 245));
        setMinimumSize(new java.awt.Dimension(900, 702));

        jTabbedPane1.setBackground(new java.awt.Color(230, 230, 230));
        jTabbedPane1.setForeground(new java.awt.Color(85, 80, 80));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Serif", 3, 16)); // NOI18N

        academicLeaderTable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        academicLeaderTable.setModel(academic_leader_model);
        academicLeaderTable.setRowHeight(30);
        jScrollPane2.setViewportView(academicLeaderTable);
        JTableHeader alHeader = academicLeaderTable.getTableHeader();
        alHeader.setFont(new Font("Serif", Font.BOLD, 15));
        alHeader.setPreferredSize(new Dimension(alHeader.getPreferredSize().width, 35));
        alHeader.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Academic Leader", jPanel1);

        lecturerTable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lecturerTable.setModel(lecturer_model);
        lecturerTable.setRowHeight(30);
        jScrollPane1.setViewportView(lecturerTable);
        JTableHeader lHeader = lecturerTable.getTableHeader();
        lHeader.setFont(new Font("Serif", Font.BOLD, 15));
        lHeader.setPreferredSize(new Dimension(lHeader.getPreferredSize().width, 35));
        lHeader.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lecturer", jPanel2);

        studentTable.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        studentTable.setModel(student_model);
        studentTable.setRowHeight(30);
        jScrollPane3.setViewportView(studentTable);
        JTableHeader sHeader = studentTable.getTableHeader();
        sHeader.setFont(new Font("Serif", Font.BOLD, 15));
        sHeader.setPreferredSize(new Dimension(sHeader.getPreferredSize().width, 35));
        sHeader.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Student", jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 100, 0));

        updateUserButton.setOpaque(true);
        updateUserButton.setContentAreaFilled(true);
        updateUserButton.setBackground(new java.awt.Color(70, 130, 180));
        updateUserButton.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        updateUserButton.setForeground(new java.awt.Color(255, 255, 255));
        updateUserButton.setText("Update User");
        updateUserButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        updateUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateUserButton.addActionListener(this::updateUserButtonActionPerformed);
        jPanel4.add(updateUserButton);

        createUserButton.setOpaque(true);
        createUserButton.setContentAreaFilled(true);
        createUserButton.setBackground(new java.awt.Color(70, 130, 180));
        createUserButton.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        createUserButton.setForeground(new java.awt.Color(255, 255, 255));
        createUserButton.setText("Create User");
        createUserButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        createUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserButton.addActionListener(this::createUserButtonActionPerformed);
        jPanel4.add(createUserButton);

        deleteUserButton.setOpaque(true);
        deleteUserButton.setContentAreaFilled(true);
        deleteUserButton.setBackground(new java.awt.Color(70, 130, 180));
        deleteUserButton.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        deleteUserButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteUserButton.setText("Delete User");
        deleteUserButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        deleteUserButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteUserButton.addActionListener(this::deleteUserButtonActionPerformed);
        jPanel4.add(deleteUserButton);

        jPanel5.setBackground(new java.awt.Color(70, 130, 180));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User Control Center");

        jButton1.setBackground(new java.awt.Color(70, 130, 180));
        jButton1.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("🏠   Back to Home");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sort By", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 2, 13), new java.awt.Color(153, 153, 153))); // NOI18N

        roleBox.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        roleBox.setForeground(new java.awt.Color(102, 102, 102));
        roleBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Academic Leader", "Lecturer", "Student" }));
        roleBox.addActionListener(this::roleBoxActionPerformed);

        sortingBox.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        sortingBox.setForeground(new java.awt.Color(102, 102, 102));
        sortingBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortingBox.addActionListener(this::sortingBoxActionPerformed);

        sortingButton.setBackground(new java.awt.Color(240, 240, 240));
        sortingButton.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        sortingButton.setForeground(new java.awt.Color(255, 0, 51));
        sortingButton.setText("Sort");
        sortingButton.setToolTipText("");
        sortingButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 51)));
        sortingButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortingButton.addActionListener(this::sortingButtonActionPerformed);

        asc_desc_box.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        asc_desc_box.setForeground(new java.awt.Color(102, 102, 102));
        asc_desc_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASC", "DESC" }));
        asc_desc_box.addActionListener(this::asc_desc_boxActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(asc_desc_box, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortingBox, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sortingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(sortingBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(asc_desc_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sortingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search By", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 2, 13), new java.awt.Color(153, 153, 153))); // NOI18N

        searchBar.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        searchBar.setForeground(new java.awt.Color(102, 102, 102));
        searchBar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        searchComboBox.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        searchComboBox.setForeground(new java.awt.Color(102, 102, 102));
        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None Result" }));
        searchComboBox.setBorder(null);

        searchButton.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 0, 51));
        searchButton.setText("Search");
        searchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(this::searchButtonActionPerformed);

        jLabel2.setFont(new java.awt.Font("Serif", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Result:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 317, Short.MAX_VALUE)
                            .addComponent(searchBar))
                        .addGap(18, 18, 18)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setupTableSelectionListeners() {
        academicLeaderTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                lecturerTable.clearSelection();
                studentTable.clearSelection();
            }
        });

        lecturerTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                academicLeaderTable.clearSelection();
                studentTable.clearSelection();
            }
        });

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                academicLeaderTable.clearSelection();
                lecturerTable.clearSelection();
            }
        });
    }
    
    // For updating
    private User getSelectedUser() {
        
        int row = -1;
        
        row = academicLeaderTable.getSelectedRow();
        if (row != -1) {
            int modelRow = academicLeaderTable.convertRowIndexToModel(row);
            return new AcademicLeader(
                academic_leader_model.getValueAt(modelRow, 0).toString()
            );
        }
        
        row = lecturerTable.getSelectedRow();
        if (row != -1) {
            int modelRow = lecturerTable.convertRowIndexToModel(row);
            return new Lecturer(
                lecturer_model.getValueAt(modelRow, 0).toString()
            );
        }
        
        
        row = studentTable.getSelectedRow();
        if (row != -1) {
            int modelRow = studentTable.convertRowIndexToModel(row);
            return new Student(
                student_model.getValueAt(modelRow, 0).toString()
            );
        }

        return null;
    }
    
    // Updating
   
    
    // For deleting
    private List<Object> getUserToDelete() {
        int row;

        row = academicLeaderTable.getSelectedRow();
        if (row != -1) {
            int modelRow = academicLeaderTable.convertRowIndexToModel(row);
            User user = new AcademicLeader(
                academic_leader_model.getValueAt(modelRow, 0).toString()
            );
            return Arrays.asList("Academic Leader", user);
        }

        row = lecturerTable.getSelectedRow();
        if (row != -1) {
            int modelRow = lecturerTable.convertRowIndexToModel(row);
            User user = new Lecturer(
                lecturer_model.getValueAt(modelRow, 0).toString()
            );
            return Arrays.asList("Lecturer", user);
        }

        row = studentTable.getSelectedRow();
        if (row != -1) {
            int modelRow = studentTable.convertRowIndexToModel(row);
            User user = new Student(
                student_model.getValueAt(modelRow, 0).toString()
            );
            return Arrays.asList("Student", user);
        }

        return null;
    }


    
    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        
        User selectedrow = getSelectedUser();
        
        if (selectedrow != null) {

//            System.out.println("Role: " + role);
//            System.out.println("User ID: " + user.getUser_id());
            
            this.dispose();
            UpdateUserFrame uuf = new UpdateUserFrame(logged_in_admin, selectedrow);
            uuf.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Please select the role to update");
        }
        
//        if(selected_row != null){
//            
//            UpdateUserFrame uuf = new UpdateUserFrame(logged_in_admin, selected_row);
//            
//        }else{
//            
//        }
        
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void createUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserButtonActionPerformed
        CreateUserFrame cuf = new CreateUserFrame(logged_in_admin);
        cuf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_createUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        
        List<Object> selected_row = getUserToDelete();
        
        if(selected_row == null){
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }
        
        String role = (String) selected_row.get(0);
        User user = (User) selected_row.get(1);
        
        if(role.equals("Academic Leader")){
            Object[] options = {"Reassign Lecturer", "Cancel"};
            int choice = JOptionPane.showOptionDialog(
                this,
                "Warning: Deleting this Academic Leader will affect Lecturers",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,       // default icon
                options,    // custom button text
                options[0]  // default button
            );
            
            if(choice == 0){
                DeleteAcademicLeaderFrame dalm = new DeleteAcademicLeaderFrame(logged_in_admin, user);
                this.dispose();
                dalm.setVisible(true);
                return;
            }else{
                return;
            }
        }
                
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user? (ID: " + user.getUser_id() + ")", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if(confirm != JOptionPane.YES_OPTION){
            return;
        }
        
        // Delete user & display success message
        String[] delete_user_status = logged_in_admin.deleteUser(user);
        boolean status = Boolean.parseBoolean(delete_user_status[0]);
        String status_message = delete_user_status[1];
        
        if (status) {
            JOptionPane.showMessageDialog(this, status_message);
            
            if (academicLeaderTable.getSelectedRow() != -1) {
                removeRowSafely(academicLeaderTable, academic_leader_model, alSorter);
            } else if (lecturerTable.getSelectedRow() != -1) {
                removeRowSafely(lecturerTable, lecturer_model, lSorter);
            } else if (studentTable.getSelectedRow() != -1) {
                removeRowSafely(studentTable, student_model, sSorter);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, status_message, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

                
        // Remove row from table
//        int row = academicLeaderTable.getSelectedRow();
//        if (row != -1) {
//            academic_leader_model.removeRow(academicLeaderTable.convertRowIndexToModel(row));
//            
//        }
//        
//        row = lecturerTable.getSelectedRow();
//        if (row != -1) {
//            lecturer_model.removeRow(lecturerTable.convertRowIndexToModel(row));
//        }
//        
//        row = studentTable.getSelectedRow();
//        if (row != -1) {
//            student_model.removeRow(studentTable.convertRowIndexToModel(row));
//        }
        
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AdminDashboardFrame adf = new AdminDashboardFrame(logged_in_admin);
        this.dispose();
        adf.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
        String keyword = searchBar.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter ID, Name, or Email");
            return;
        }

        // Clear previous results
        searchComboBox.removeAllItems(); 

        // Collect matches across all tables
        int resultsCount = 0;

        // Collect resultsCount
        resultsCount += addSearchResults(academicLeaderTable, 0, keyword, "Academic Leader");
        resultsCount += addSearchResults(lecturerTable, 1, keyword, "Lecturer");
        resultsCount += addSearchResults(studentTable, 2, keyword, "Student");

        if (resultsCount == 0) {
            JOptionPane.showMessageDialog(this, "No matching record found.");
        } else {
            searchComboBox.setSelectedIndex(0); // default selection
            searchBar.setText(""); 
        }
        
    }//GEN-LAST:event_searchButtonActionPerformed

    private void roleBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleBoxActionPerformed

    }//GEN-LAST:event_roleBoxActionPerformed

    private void sortingBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingBoxActionPerformed

    }//GEN-LAST:event_sortingBoxActionPerformed

    private void asc_desc_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asc_desc_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_asc_desc_boxActionPerformed

    private void sortingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sortingButtonActionPerformed

    private void removeRowSafely(JTable table, DefaultTableModel model, TableRowSorter sorter) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Convert to model index while the sorter is still active
            int modelRow = table.convertRowIndexToModel(selectedRow);

            // Stop the table from listening to the sorter temporarily
            table.setRowSorter(null); 
            table.clearSelection();

            // Remove the data
            model.removeRow(modelRow);

            // Re-attach the sorter
            table.setRowSorter(sorter);

            // Clear search results as indices are now invalid
            searchComboBox.removeAllItems();
        }
        
    }

    private int addSearchResults(JTable table, int tabIndex, String keyword, String role) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int matchCount = 0;
        int maxLength = 40; 

        for (int i = 0; i < model.getRowCount(); i++) {
            String id = model.getValueAt(i, 0).toString().toLowerCase();
            String name = model.getValueAt(i, 1).toString().toLowerCase();
            String email = model.getValueAt(i, 2).toString().toLowerCase();

            if (id.contains(keyword) || name.contains(keyword) || email.contains(keyword)) {

                // Full display text
                String display = String.format("%s - %s", model.getValueAt(i, 0), model.getValueAt(i, 1));

                // Truncate if too long
                if (display.length() > maxLength) {
                    display = display.substring(0, maxLength - 3) + "...";
                }

                // Encode table info for focusing
                String encoded = tabIndex + "|" + i + "|" + display;
                searchComboBox.addItem(encoded);
                matchCount++;
            }
        }

        return matchCount;
    }

    private void setupSearchComboBoxListener() {
        searchComboBox.addActionListener(e -> {
            String selected = (String) searchComboBox.getSelectedItem();
            if (selected == null) return;

            // Decode the selection
            String[] parts = selected.split("\\|", 3);
            if (parts.length != 3) return;

            int tabIndex = Integer.parseInt(parts[0]);
            int rowIndex = Integer.parseInt(parts[1]);

            JTable table;
            switch (tabIndex) {
                case 0: table = academicLeaderTable; break;
                case 1: table = lecturerTable; break;
                default: table = studentTable; break;
            }

            // Switch tab first
            jTabbedPane1.setSelectedIndex(tabIndex);

            // Focus table AFTER Swing has updated the tab
            SwingUtilities.invokeLater(() -> {
                int viewRow = table.convertRowIndexToView(rowIndex);
                table.getSelectionModel().setSelectionInterval(viewRow, viewRow);
                table.scrollRectToVisible(table.getCellRect(viewRow, 0, true));
                table.requestFocusInWindow();
            });
        });
    }

    
//    
//    private boolean searchAndHighlight(JTable table, String keyword) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        table.clearSelection(); // remove previous selection
//
//        boolean found = false;
//        int firstMatchRow = -1;
//
//        TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
//
//        for (int i = 0; i < model.getRowCount(); i++) {
//            String id    = model.getValueAt(i, 0).toString().toLowerCase();
//            String name  = model.getValueAt(i, 1).toString().toLowerCase();
//            String email = model.getValueAt(i, 2).toString().toLowerCase();
//
//            if (id.contains(keyword) || name.contains(keyword) || email.contains(keyword)) {
//                int viewRow = table.convertRowIndexToView(i);
//                table.addRowSelectionInterval(viewRow, viewRow);
//                found = true;
//            }
//        }
//        
//        return found;
//        
//    }
//    
//    public void switchTabAndFocus(JTable table, int tabIndex){
//        
//        jTabbedPane1.setSelectedIndex(tabIndex);
//
//        SwingUtilities.invokeLater(() -> {
//            int row = table.getSelectedRow();
//            if (row != -1) {
//                table.scrollRectToVisible(table.getCellRect(row, 0, true));
//                table.requestFocusInWindow();
//            }
//        });
//        
//    }


    private void setupSortingAndRoleBox() {
        roleBox.removeAllItems();
        sortingBox.removeAllItems();
        asc_desc_box.removeAllItems();
        
         
        // Populate roleBox
        roleBox.addItem("Academic Leader");
        roleBox.addItem("Lecturer");
        roleBox.addItem("Student");

        // Populate asc_desc_box
        asc_desc_box.addItem("ASC");
        asc_desc_box.addItem("DESC");
        
        roleBox.addActionListener(e -> updateSortingBox());

        roleBox.setSelectedIndex(0);
        updateSortingBox();
        
        sortingButton.addActionListener(e -> applySorting());
        
    }
    
    public void updateSortingBox(){
        
        sortingBox.removeAllItems();

        // Common columns
        sortingBox.addItem("ID");
        sortingBox.addItem("Name");
        sortingBox.addItem("Email");
        sortingBox.addItem("Date of Birth");
        sortingBox.addItem("Created By");
        sortingBox.addItem("Created At");

        String role = (String) roleBox.getSelectedItem();

        if ("Academic Leader".equals(role)) {
            sortingBox.addItem("Department");
        } 
        else if ("Lecturer".equals(role)) {
            sortingBox.addItem("Department");
            sortingBox.addItem("Leader ID");
        }
        
    }
    
    public void applySorting(){ 
        String role = (String) roleBox.getSelectedItem();
        String column = (String) sortingBox.getSelectedItem();
        String ascDesc = (String) asc_desc_box.getSelectedItem();

        DefaultTableModel model;
        TableRowSorter<DefaultTableModel> sorter;
        int tabIndex;

        switch (role) {
            case "Academic Leader":
                model = academic_leader_model;
                sorter = alSorter;
                tabIndex = 0;
                break;
            case "Lecturer":
                model = lecturer_model;
                sorter = lSorter;
                tabIndex = 1;
                break;
            default:
                model = student_model;
                sorter = sSorter;
                tabIndex = 2;
        }

        int colIndex = model.findColumn(column);
        if (colIndex == -1) return;

        sorter.setSortKeys(List.of(
            new RowSorter.SortKey(
                colIndex,
                "ASC".equals(ascDesc) ? SortOrder.ASCENDING : SortOrder.DESCENDING
            )
        ));

        sorter.sort();
        
        jTabbedPane1.setSelectedIndex(tabIndex);
        
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
//        java.awt.EventQueue.invokeLater(() -> new UserManagementFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable academicLeaderTable;
    private javax.swing.JComboBox<String> asc_desc_box;
    private javax.swing.JButton createUserButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable lecturerTable;
    private javax.swing.JComboBox<String> roleBox;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JComboBox<String> sortingBox;
    private javax.swing.JButton sortingButton;
    private javax.swing.JTable studentTable;
    private javax.swing.JButton updateUserButton;
    // End of variables declaration//GEN-END:variables
}
