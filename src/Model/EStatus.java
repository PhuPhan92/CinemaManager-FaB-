package Model;

public enum EStatus {
    STOCKING ("STOCKING", 1),
    SOLD_OUT("SOLD_OUT", 2);

    private String value;
    private int id;
    private EStatus(String value, int id) {
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

    public static EStatus toStatus(int id) {
        for (EStatus e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public static EStatus getEStatusByName(String name) {
        for (EStatus e : values()) {
            String temp = String.valueOf(e);
            if (temp.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
