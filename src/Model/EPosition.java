package Model;

public enum EPosition {
    MANAGER("MANAGER", 1),
    STAFF("STAFF", 2),
    USER("USER", 3);

    private String value;
    private int id;
    private EPosition(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static EPosition toEPosition(int id) {
        for (EPosition e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public static EPosition getEPositionByName(String name) {
        for (EPosition e : values()) {
            String temp = String.valueOf(e);
            if (temp.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
