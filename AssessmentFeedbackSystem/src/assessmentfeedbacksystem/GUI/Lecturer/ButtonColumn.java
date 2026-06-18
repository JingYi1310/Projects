/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assessmentfeedbacksystem.GUI.Lecturer;

/**
 *
 * @author Jing Yi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private JTable table;
    private ActionListener action;
    private int row;
    private int column;
    private static final int CELL_PADDING = 4;
    private int hoverRow = -1;
    
    // Renderer
    private JPanel renderPanel;
    private JButton renderButton;

    // Editor
    private JPanel editPanel;
    private JButton editButton;

    public ButtonColumn(JTable table, int column, String text, ActionListener action) {
        this.table = table;
        this.action = action;
        this.column = column;

        // ===== Renderer =====
        renderButton = createButton(text);
        renderPanel = createPanel(renderButton);

        // ===== Editor =====
        editButton = createButton(text);
        editButton.addActionListener(this);
        editPanel = createPanel(editButton);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
        columnModel.setColumnSelectionAllowed(false);
        
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if (col == ButtonColumn.this.column && row >= 0) {
                    Rectangle cellRect = table.getCellRect(row, col, false);
                    Dimension btnSize = renderButton.getPreferredSize();

                    int btnX = cellRect.x + 4;
                    int btnY = cellRect.y + (cellRect.height - btnSize.height) / 2;

                    Rectangle btnRect = new Rectangle(btnX, btnY, btnSize.width, btnSize.height);

                    if (btnRect.contains(e.getPoint())) {
                        hoverRow = row;
                        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        table.repaint(cellRect);
                        return;
                    }
                }

                hoverRow = -1;
                table.setCursor(Cursor.getDefaultCursor());
                table.repaint();
            }
        });
    }

    // Create padded panel (cell background)
    private JPanel createPanel(JButton button) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); // padding
        panel.add(button, java.awt.BorderLayout.WEST);
        return panel;
    }

    // Create styled button
    private JButton createButton(String text) {
        Color normal = new Color(0x1E3A8A);
        Color hover = new Color(0x2747A3);

        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();

                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // no border
            }
        };

        button.setPreferredSize(new Dimension(70, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(normal);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
        });

        return button;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Color normal = new Color(0x1E3A8A);
        Color hover  = new Color(0x2747A3);

        if (row == hoverRow) {
            renderButton.setBackground(hover);
        } else {
            renderButton.setBackground(normal);
        }

        Color bg = (row % 2 != 0)
                ? new Color(242, 242, 242)
                : Color.WHITE;

        renderPanel.setBackground(bg);
        renderPanel.setOpaque(true);

        return renderPanel;
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected,
            int row, int column) {

        this.row = row;

        Color bg;
        
        bg = (row % 2 != 0)
                ? new Color(242,242,242)
                : new Color(255,255,255);
        
        editPanel.setBackground(bg);
        editPanel.setOpaque(true);

        return editPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        action.actionPerformed(new ActionEvent(row, ActionEvent.ACTION_PERFORMED, null));
    }
}
