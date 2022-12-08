package entity;

public enum Status {
    NEW(1, "Waiting"), APPROVED(2, "Approved"),
    COOKING(3, "Cooking"), DELIVERING(4, "Delivering"),
    RECEIVED(5, "Received");

    private final int id;
    private final String value;

    Status(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static Status getStatus(long id) {
        for (Status s : values()) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
