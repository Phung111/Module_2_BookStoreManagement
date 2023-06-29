package model;

import repository.IModel;

import java.time.Instant;

public class Account implements IModel<Account> {

    private long id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String address;
    private ERole erole;

    public Account() {
    }

    public Account(long id, String account, String password, String name, String email, String address, ERole erole) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.erole = erole;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ERole getErole() {
        return erole;
    }

    public void setErole(ERole erole) {
        this.erole = erole;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void update(Account objNew) {
        this.id = objNew.getId();
        this.account = objNew.getAccount();
        this.password = objNew.getPassword();
        this.name = objNew.getName();
        this.email = objNew.getEmail();
        this.address = objNew.getEmail();
        this.erole = objNew.getErole();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Account parseData(String line) {
        //1,admin,admin,NGUYỄN VĂN ADMIN,admin@gmail.com,52 Ba Trieu,admin
        String[] items = line.split(",");
        long id = Long.parseLong(items[0]);
        String account = items[1];
        String password = items[2];
        String name = items[3];
        String email = items[4];
        String address = items[5];
        ERole eRole = ERole.getERoleByName(items[6]);
//        ERole eRole = ERole.toERole(Integer.parseInt(items[6]));
        Account newAccount = new Account(id, account, password, name, email, address, eRole);
        return newAccount;
    }
}
