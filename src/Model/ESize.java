package Model;

public enum ESize {
    M("M", 1),
    L("L", 2),
    OTHER("OTHER", 3);

    private String value;
    private int id;
    private ESize(String value, int id) {
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

    public static ESize toSize(int id) {
        for (ESize e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public static ESize getESizeByName(String name) {
        for (ESize e : values()) {
            String temp = String.valueOf(e);
            if (temp.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
