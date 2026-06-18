/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Student;

import assessmentfeedbacksystem.GUI.LoginFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author student
 */
public class StudentSideNavBar extends javax.swing.JPanel {
    
    private final StudentMainFrame mainFrame;
    private JButton selectedButton = null;
        
    // TODO: Replace these with actual student-themed icons if you have them
    // For now using placeholder paths - you'll need to add student icons to your resources
    private final Icon DASHBOARD_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/dashboard.png"));
    private final Icon DASHBOARD_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/dashboard_hover_selected.png"));

    private final Icon CLASSES_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/module.png"));
    private final Icon CLASSES_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/module_hover_selected.png"));

    private final Icon RESULTS_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/report.png"));
    private final Icon RESULTS_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/report_hover_selected.png"));
    
    private final Icon FEEDBACK_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/profile.png"));
    private final Icon FEEDBACK_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/profile_hover_selected.png"));
    
    private final Icon NOTIFICATIONS_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/profile.png"));
    private final Icon NOTIFICATIONS_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/profile_hover_selected.png"));
    
    private final Icon TIMETABLE_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/profile.png"));
    private final Icon TIMETABLE_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/profile_hover_selected.png"));
    
    private final Icon PROFILE_ICON_NORMAL = new ImageIcon(getClass().getResource("/icons/profile.png"));
    private final Icon PROFILE_ICON_HOVER_SELECTED = new ImageIcon(getClass().getResource("/icons/profile_hover_selected.png"));
    
    /**
     * Creates new form StudentSideNavBar
     */
    public StudentSideNavBar() {
        this.mainFrame = null;
        initComponents();
    }
    
    public StudentSideNavBar(StudentMainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        
        styleLogoutButton();
        
        initButtonState();
        
        forceFullWidth(dashboardBtn);
        forceFullWidth(classesBtn);
        forceFullWidth(resultsBtn);
        forceFullWidth(feedbackBtn);
        forceFullWidth(notificationsBtn);
        forceFullWidth(timetableBtn);
        forceFullWidth(profileBtn);
        
        applyHoverEffect(dashboardBtn);
        applyHoverEffect(classesBtn);
        applyHoverEffect(resultsBtn);
        applyHoverEffect(feedbackBtn);
        applyHoverEffect(notificationsBtn);
        applyHoverEffect(timetableBtn);
        applyHoverEffect(profileBtn);
        
        makeRounded(dashboardBtn);
        makeRounded(classesBtn);
        makeRounded(resultsBtn);
        makeRounded(feedbackBtn);
        makeRounded(notificationsBtn);
        makeRounded(timetableBtn);
        makeRounded(profileBtn);
        
        selectButton(dashboardBtn);
    }
    
    private void styleLogoutButton() {
        logoutBtn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = 20;
                int borderThickness = 2;

                g2.setColor(logoutBtn.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), arc, arc);

                g2.setStroke(new java.awt.BasicStroke(borderThickness));
                g2.setColor(new Color(221, 164, 158));
                g2.drawRoundRect(borderThickness/2, borderThickness/2, 
                             c.getWidth() - borderThickness, 
                             c.getHeight() - borderThickness, arc, arc);

                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private void initButtonState() {
        JButton[] buttons = { dashboardBtn, classesBtn, resultsBtn, feedbackBtn, 
                              notificationsBtn, timetableBtn, profileBtn };
        for (JButton btn : buttons) {
            styleButton(btn, false, false);
        }
    }
    
    private void forceFullWidth(JButton btn) {
        btn.setMaximumSize(
            new java.awt.Dimension(
                Integer.MAX_VALUE,
                48
            )
        );
    }
    
    private void selectButton(JButton btn) {
        if (selectedButton != null) {
            styleButton(selectedButton, false, false);
        }
        selectedButton = btn;
        styleButton(btn, true, false);
    }
    
    private void applyHoverEffect(JButton btn) {
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != selectedButton) {
                    styleButton(btn, false, true);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != selectedButton) {
                    styleButton(btn, false, false);
                }
            }
        });
    }
    
    private void makeRounded(JButton btn) {
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = 16;
                int indicatorWidth = 4;

                g2.setColor(btn.getBackground());
                g2.fillRoundRect(
                    6, 0,
                    c.getWidth() - 12,
                    c.getHeight(),
                    arc, arc
                );

                if (btn == selectedButton) {
                    g2.setColor(new Color(37, 99, 235)); 
                    g2.fillRoundRect(
                        6,
                        6,
                        indicatorWidth,
                        c.getHeight() - 12,
                        8, 8
                    );
                }

                g2.dispose();
                super.paint(g, c);
            }
        });

        btn.setBorder(BorderFactory.createEmptyBorder(12, 28, 12, 20));
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        navPanel = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        classesBtn = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        resultsBtn = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        feedbackBtn = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        notificationsBtn = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        timetableBtn = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(32767, 18));
        profileBtn = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 35, 10, 10));
        headerPanel.setPreferredSize(new java.awt.Dimension(100, 60));
        headerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(107, 114, 128));
        jLabel1.setText("STUDENT MENU");
        headerPanel.add(jLabel1);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        navPanel.setBackground(new java.awt.Color(255, 255, 255));
        navPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        navPanel.setLayout(new javax.swing.BoxLayout(navPanel, javax.swing.BoxLayout.Y_AXIS));

        dashboardBtn.setForeground(new java.awt.Color(115, 118, 122));
        dashboardBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dashboard.png"))); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setBorder(null);
        dashboardBtn.setBorderPainted(false);
        dashboardBtn.setContentAreaFilled(false);
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setFocusPainted(false);
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(12);
        dashboardBtn.setInheritsPopupMenu(true);
        dashboardBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        dashboardBtn.setName(""); // NOI18N
        dashboardBtn.setOpaque(true);
        dashboardBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });
        navPanel.add(dashboardBtn);
        navPanel.add(filler1);

        classesBtn.setForeground(new java.awt.Color(115, 118, 122));
        classesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/module.png"))); // NOI18N
        classesBtn.setText("Classes");
        classesBtn.setBorder(null);
        classesBtn.setBorderPainted(false);
        classesBtn.setContentAreaFilled(false);
        classesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        classesBtn.setFocusPainted(false);
        classesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        classesBtn.setIconTextGap(12);
        classesBtn.setInheritsPopupMenu(true);
        classesBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        classesBtn.setOpaque(true);
        classesBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        classesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classesBtnActionPerformed(evt);
            }
        });
        navPanel.add(classesBtn);
        navPanel.add(filler2);

        resultsBtn.setForeground(new java.awt.Color(115, 118, 122));
        resultsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/report.png"))); // NOI18N
        resultsBtn.setText("Results");
        resultsBtn.setBorder(null);
        resultsBtn.setBorderPainted(false);
        resultsBtn.setContentAreaFilled(false);
        resultsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resultsBtn.setFocusPainted(false);
        resultsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        resultsBtn.setIconTextGap(12);
        resultsBtn.setInheritsPopupMenu(true);
        resultsBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        resultsBtn.setOpaque(true);
        resultsBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        resultsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultsBtnActionPerformed(evt);
            }
        });
        navPanel.add(resultsBtn);
        navPanel.add(filler3);

        feedbackBtn.setForeground(new java.awt.Color(115, 118, 122));
        feedbackBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profile.png"))); // NOI18N
        feedbackBtn.setText("Feedback");
        feedbackBtn.setBorder(null);
        feedbackBtn.setBorderPainted(false);
        feedbackBtn.setContentAreaFilled(false);
        feedbackBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        feedbackBtn.setFocusPainted(false);
        feedbackBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        feedbackBtn.setIconTextGap(12);
        feedbackBtn.setInheritsPopupMenu(true);
        feedbackBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        feedbackBtn.setOpaque(true);
        feedbackBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        feedbackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedbackBtnActionPerformed(evt);
            }
        });
        navPanel.add(feedbackBtn);
        navPanel.add(filler4);

        notificationsBtn.setForeground(new java.awt.Color(115, 118, 122));
        notificationsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profile.png"))); // NOI18N
        notificationsBtn.setText("Notifications");
        notificationsBtn.setBorder(null);
        notificationsBtn.setBorderPainted(false);
        notificationsBtn.setContentAreaFilled(false);
        notificationsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        notificationsBtn.setFocusPainted(false);
        notificationsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        notificationsBtn.setIconTextGap(12);
        notificationsBtn.setInheritsPopupMenu(true);
        notificationsBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        notificationsBtn.setOpaque(true);
        notificationsBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        notificationsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notificationsBtnActionPerformed(evt);
            }
        });
        navPanel.add(notificationsBtn);
        navPanel.add(filler5);

        timetableBtn.setForeground(new java.awt.Color(115, 118, 122));
        timetableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profile.png"))); // NOI18N
        timetableBtn.setText("Timetable");
        timetableBtn.setBorder(null);
        timetableBtn.setBorderPainted(false);
        timetableBtn.setContentAreaFilled(false);
        timetableBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timetableBtn.setFocusPainted(false);
        timetableBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        timetableBtn.setIconTextGap(12);
        timetableBtn.setInheritsPopupMenu(true);
        timetableBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        timetableBtn.setOpaque(true);
        timetableBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        timetableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timetableBtnActionPerformed(evt);
            }
        });
        navPanel.add(timetableBtn);
        navPanel.add(filler6);

        profileBtn.setForeground(new java.awt.Color(115, 118, 122));
        profileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/profile.png"))); // NOI18N
        profileBtn.setText("Profile");
        profileBtn.setBorder(null);
        profileBtn.setBorderPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.setFocusPainted(false);
        profileBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profileBtn.setIconTextGap(12);
        profileBtn.setInheritsPopupMenu(true);
        profileBtn.setMargin(new java.awt.Insets(12, 20, 12, 20));
        profileBtn.setOpaque(true);
        profileBtn.setPreferredSize(new java.awt.Dimension(180, 44));
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });
        navPanel.add(profileBtn);

        add(navPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 255));
        bottomPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 15, 30, 15));
        bottomPanel.setLayout(new java.awt.BorderLayout());

        logoutBtn.setBackground(new java.awt.Color(255, 237, 237));
        logoutBtn.setForeground(new java.awt.Color(153, 0, 0));
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.setBorderPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setIconTextGap(10);
        logoutBtn.setPreferredSize(new java.awt.Dimension(95, 35));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(logoutBtn, java.awt.BorderLayout.CENTER);

        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("DASHBOARD");
            selectButton(dashboardBtn);
        }
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void classesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classesBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("CLASSES");
            selectButton(classesBtn);
        }
    }//GEN-LAST:event_classesBtnActionPerformed

    private void resultsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultsBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("RESULTS");
            selectButton(resultsBtn);
        }
    }//GEN-LAST:event_resultsBtnActionPerformed

    private void feedbackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedbackBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("FEEDBACK");
            selectButton(feedbackBtn);
        }
    }//GEN-LAST:event_feedbackBtnActionPerformed

    private void notificationsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notificationsBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("NOTIFICATIONS");
            selectButton(notificationsBtn);
        }
    }//GEN-LAST:event_notificationsBtnActionPerformed

    private void timetableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timetableBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("TIMETABLE");
            selectButton(timetableBtn);
        }
    }//GEN-LAST:event_timetableBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        if (mainFrame != null) {
            mainFrame.showPage("PROFILE");
            selectButton(profileBtn);
        }
    }//GEN-LAST:event_profileBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); 
        }
    }//GEN-LAST:event_logoutBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton classesBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JButton feedbackBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel navPanel;
    private javax.swing.JButton notificationsBtn;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton resultsBtn;
    private javax.swing.JButton timetableBtn;
    // End of variables declaration//GEN-END:variables

    private void styleButton(JButton btn, boolean selected, boolean hover) {

        Color bg = Color.WHITE;
        Color text = new Color(115, 118, 122); 
        Icon icon;

        if (hover || selected) {
            bg = new Color(217, 235, 255);
            text = new Color(25, 50, 180);
        }
        
        boolean active = hover || selected;

        if (btn == dashboardBtn) {
            icon = active ? DASHBOARD_ICON_HOVER_SELECTED : DASHBOARD_ICON_NORMAL;
        } else if (btn == classesBtn) {
            icon = active ? CLASSES_ICON_HOVER_SELECTED : CLASSES_ICON_NORMAL;
        } else if (btn == resultsBtn) {
            icon = active ? RESULTS_ICON_HOVER_SELECTED : RESULTS_ICON_NORMAL;
        } else if (btn == feedbackBtn) {
            icon = active ? FEEDBACK_ICON_HOVER_SELECTED : FEEDBACK_ICON_NORMAL;
        } else if (btn == notificationsBtn) {
            icon = active ? NOTIFICATIONS_ICON_HOVER_SELECTED : NOTIFICATIONS_ICON_NORMAL;
        } else if (btn == timetableBtn) {
            icon = active ? TIMETABLE_ICON_HOVER_SELECTED : TIMETABLE_ICON_NORMAL;
        } else {
            icon = active ? PROFILE_ICON_HOVER_SELECTED : PROFILE_ICON_NORMAL;
        }

        btn.setBackground(bg);
        btn.setForeground(text);
        btn.setIcon(icon);

        btn.repaint(); 
    }
}
