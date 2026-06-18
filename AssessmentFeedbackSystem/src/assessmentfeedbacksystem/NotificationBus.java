package assessmentfeedbacksystem;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class NotificationBus {
    public interface Listener {
        void onNotification(String studentId);
    }

    private static final List<Listener> LISTENERS = new CopyOnWriteArrayList<>();

    private NotificationBus() {}

    public static void register(Listener listener) {
        if (listener != null) LISTENERS.add(listener);
    }

    public static void unregister(Listener listener) {
        if (listener != null) LISTENERS.remove(listener);
    }

    public static void notifyStudent(String studentId) {
        for (Listener l : LISTENERS) {
            l.onNotification(studentId);
        }
    }
}
