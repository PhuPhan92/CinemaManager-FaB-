package Model;

public enum EGender {
    MAlE("MALE", 1),
    FEMAlE("FEMAlE", 2),
    OTHER("OTHER", 3);

    private String value;
    private int id;
    private EGender(String value, int id) {
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

    public static EGender toEGender(int id) {
        for (EGender e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    public static EGender getEGenderByName(String name) {
        for (EGender e : values()) {
            String temp = String.valueOf(e);
            if (temp.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
