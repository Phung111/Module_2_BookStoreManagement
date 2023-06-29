package service;

import model.Account;
import repository.file.AccountRepository;

import java.util.List;

public class AccountService {
    private AccountRepository accountRepository;
    public AccountService(){
        accountRepository = new AccountRepository();
    }

    public Account checkAccountPassword(String account, String password) {
        List<Account> list = accountRepository.getAll();
        for (int i = 0; i < list.size(); i++){
            boolean check = list.get(i).getAccount().equals(account) && list.get(i).getPassword().equals(password);
            if(check) {
                return list.get(i);
            }
        }
        return null;
    }

    public Account checkAccount(String account) {
        List<Account> list = accountRepository.getAll();
        for (int i = 0; i < list.size(); i++){
            boolean check = list.get(i).getAccount().equals(account);
            if(check) {
                return list.get(i);
            }
        }
        return null;
    }

    public Account checkPassword(String password) {
        List<Account> list = accountRepository.getAll();
        for (int i = 0; i < list.size(); i++){
            boolean check = list.get(i).getPassword().equals(password);
            if(check) {
                return list.get(i);
            }
        }
        return null;
    }

}
