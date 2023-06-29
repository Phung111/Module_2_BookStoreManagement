package model;

public enum ERole {
    ADMIN("admin",1),
    USER("user",2);

    private String value;
    private long id;

    ERole(String value, long id) {
        this.value = value;
        this.id = id;
    }

    public static ERole toERole(long id){
        for (ERole e : values()){
            if(e.getId() == id) {
                return e;
            }
        }
        return null;
    }


    public static ERole getERoleByName(String name) {
        for (ERole e : values()) {
            if (e.getValue().equals(name)) {
                return e;
            }
        }
        return null;
    }

    ERole() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
