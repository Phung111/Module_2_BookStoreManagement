package BookStoreManagerment.service;

import BookStoreManagerment.model.Account;
import BookStoreManagerment.repository.file.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts;
    private AccountRepository accountRepository;
    public AccountService(){
        accountRepository = new AccountRepository();
    }

    public List<Account> getAllAccounts(){
        return accountRepository.getAll();
    }

    public Account login(String username, String password){
        List<Account> accountList = getAllAccounts();
        try {
            for (Account account : accountList){
                if (account.getUserName().equals(username) &&
                        account.getPassWord().equals(password)){
                    return account;
                }
            }
        } catch (NullPointerException nullPointerException){
            return null;
        }
        return null;
    }

    public boolean isLoginUsername(String username){
        List<Account> accountList = getAllAccounts();
        for (Account account : accountList){
            if (account.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }
    public boolean isLoginUPassword(String password){
        List<Account> accountList = getAllAccounts();
        for (Account account : accountList){
            if (account.getPassWord().equals(password)){
                return true;
            }
        }
        return false;
    }

}
