package assessmentfeedbacksystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public final class ToastUtil {
    private ToastUtil() {}

    public static void showToast(JFrame anchor, String title, String message, Color color) {
        if (anchor == null || !anchor.isShowing()) return;

        JWindow toast = new JWindow(anchor);
        JPanel panel = new JPanel(new BorderLayout(6, 2));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        panel.setBackground(Color.WHITE);

        JLabel ttl = new JLabel(title);
        ttl.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        ttl.setForeground(color);
        JLabel msg = new JLabel(message);
        msg.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));

        panel.add(ttl, BorderLayout.NORTH);
        panel.add(msg, BorderLayout.CENTER);
        toast.add(panel);
        toast.pack();

        int x = anchor.getLocationOnScreen().x + anchor.getWidth() - toast.getWidth() - 30;
        int y = anchor.getLocationOnScreen().y + 20;
        toast.setLocation(x, y);
        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        new javax.swing.Timer(3000, e -> toast.dispose()).start();
    }

    public static Color colorForType(String type) {
        if (type == null) return new Color(100, 116, 139);
        switch (type.toLowerCase()) {
            case "welcome": return new Color(59, 130, 246);
            case "enrol_success": return new Color(22, 163, 74);
            case "result": return new Color(234, 179, 8);
            case "new_class": return new Color(147, 51, 234);
            default: return new Color(100, 116, 139);
        }
    }
}
