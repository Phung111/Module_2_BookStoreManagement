package BookStoreManagerment.model;

import java.time.Instant;

public class Account {
    private long accountID;
    private String userName;
    private String passWord;
    private String fullName;
    private AccountTypes accountTypes;
    private Instant atCreated;
    private Instant atUpdated;

    public Account() {
    }

    public Account(long accountID, String userName, String passWord, String fullName, AccountTypes accountTypes, Instant atCreated, Instant atUpdated) {
        this.accountID = accountID;
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.accountTypes = accountTypes;
        this.atCreated = atCreated;
        this.atUpdated = atUpdated;
    }

    public static Account parseAccount(String raw){
        String[] fields = raw.split(",");
        long accountID = Long.parseLong(fields[0]);
        String userName = fields[1];
        String passWord = fields[2];
        String fullName = fields[3];
        AccountTypes accountTypes = AccountTypes.getAccountTypes(fields[4]);
        Instant atCreated = Instant.parse(fields[5]);
        Instant atUpdated = Instant.parse(fields[6]);
        return new Account(accountID, userName, passWord,fullName,accountTypes,atCreated,atUpdated);
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s\n",
                accountID,userName,passWord,fullName,accountTypes.getValue(),atCreated,atUpdated);
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AccountTypes getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(AccountTypes accountTypes) {
        this.accountTypes = accountTypes;
    }

    public Instant getAtCreated() {
        return atCreated;
    }

    public void setAtCreated(Instant atCreated) {
        this.atCreated = atCreated;
    }

    public Instant getAtUpdated() {
        return atUpdated;
    }

    public void setAtUpdated(Instant atUpdated) {
        this.atUpdated = atUpdated;
    }
}
