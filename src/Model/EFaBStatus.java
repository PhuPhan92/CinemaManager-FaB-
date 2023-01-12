package Model;

public enum EFaBStatus {
    PENDING("PENDING", 1),OK("OK",2),CANCELED("CANCELED",3);
    private int id;
    private String value;

    EFaBStatus(String value, int id) {
        this.id = id;
        this.value = value;
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
    public static EFaBStatus getEGenderByName (String name){
        for (EFaBStatus i : values()){
            String temp = String.valueOf(i);
            if (temp.equals(name)){
                return i;
            }
        }
        return null;

    }
    public static EFaBStatus toEFaBStatus (int id) {
        for (EFaBStatus e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
