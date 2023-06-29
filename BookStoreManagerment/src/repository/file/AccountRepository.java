package repository.file;

import model.Account;

public class AccountRepository extends FileContext<Account> {
    public AccountRepository(){
        filePath = "./BookStoreManagerment/src/data/account.csv";
        tClass = Account.class;
    }
}
