package BookStoreManagerment.repository.file;

import BookStoreManagerment.model.Account;

public class AccountRepository extends FileContext<Account> {
    public AccountRepository(){
        filePath = "./BookStoreManagerment/src/BookStoreManagerment/data/account.csv";
        tClass = Account.class;
    }
}
