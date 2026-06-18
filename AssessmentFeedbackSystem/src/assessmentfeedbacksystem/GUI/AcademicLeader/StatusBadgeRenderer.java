/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assessmentfeedbacksystem.GUI.AcademicLeader;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author xinyun
 */
public class StatusBadgeRenderer extends DefaultTableCellRenderer {
    
    private static final Color ROW_WHITE = Color.WHITE;
    private static final Color ROW_GRAY  = new Color(242,242,242);
    
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Color rowBg;
        if (isSelected) {
            rowBg = table.getSelectionBackground();
        } else {
            rowBg = (row % 2 == 0) ? ROW_WHITE : ROW_GRAY;
        }
        
        JPanel cellPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        cellPanel.setOpaque(true);
        cellPanel.setBackground(rowBg);

        if (value == null) {
            return cellPanel;
        }

        String status = value.toString();

        Color bg;
        Color text;

        if ("Active".equalsIgnoreCase(status)) {
            bg     = new Color(217, 236, 219); 
            text   = new Color(39, 103, 73);
        } else if ("Inactive".equalsIgnoreCase(status)) {
            bg   = new Color(248, 212, 211); 
            text = new Color(199, 43, 43);
        } else { 
            bg     = new Color(247, 235, 193);   
            text   = new Color(151, 90, 22);
        }

        JLabel badge = new JLabel(status);
        badge.setFont(new Font("Helvetica Neue", Font.BOLD, 11));
        badge.setForeground(text);
        badge.setOpaque(false);
        badge.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        badge.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel badgeWrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };

        badgeWrapper.setOpaque(false);
        badgeWrapper.setLayout(new BorderLayout());
        badgeWrapper.add(badge, BorderLayout.CENTER);

        badgeWrapper.setPreferredSize(
                new Dimension(badge.getPreferredSize().width + 12,22)
        );

        cellPanel.add(badgeWrapper);

        return cellPanel;
    }
}
