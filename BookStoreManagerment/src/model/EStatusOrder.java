package model;

public enum EStatusOrder {
    NEW("new", 1),
    PAID("paid", 2),
    CANCELED("canceled", 3),
    BORROW("borrow", 4),
    BORROWPAID("bnp", 5),
    BORROWING("borrowing", 6);

    private String value;
    private long id;

    private EStatusOrder(String value, long id) {
        this.value = value;
        this.id = id;
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

    public static EStatusOrder toEStatusOrder(long id){
        for (EStatusOrder e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static EStatusOrder getEStatusOrderByName(String name) {
        for (EStatusOrder e : values()) {
            if (e.toString().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
