package hexlet.code;

public final class Node {

    public enum Status {
        ADDED,
        REMOVED,
        UPDATED,
        UNCHANGED
    }

    private final String key;
    private final Status status;
    private final Object oldValue;
    private final Object newValue;

    private Node(String key, Status status, Object oldValue, Object newValue) {
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public static Node added(String key, Object newValue) {
        return new Node(key, Status.ADDED, null, newValue);
    }

    public static Node removed(String key, Object oldValue) {
        return new Node(key, Status.REMOVED, oldValue, null);
    }

    public static Node updated(String key, Object oldValue, Object newValue) {
        return new Node(key, Status.UPDATED, oldValue, newValue);
    }

    public static Node unchanged(String key, Object value) {
        return new Node(key, Status.UNCHANGED, value, value);
    }

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}

