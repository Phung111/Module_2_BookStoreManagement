package BookStoreManagerment.model;

public enum AccountTypes {
    USER("user"),ADMIN("admin");
    private String value;

    //// !!!!!!!
    AccountTypes (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
//        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static AccountTypes getAccountTypes(String value){
        for (AccountTypes accountTypes : AccountTypes.values()){
            if (accountTypes.getValue().equals(value)){
                return accountTypes;
            }
        }
        return null;
    }

}
