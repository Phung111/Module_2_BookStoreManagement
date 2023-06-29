package view;

import model.Account;

import java.util.Scanner;

public abstract class GenericView {
    protected Scanner scanner = new Scanner(System.in);
    protected Account account;

    public abstract void launcher();

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }
}
