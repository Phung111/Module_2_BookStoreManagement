package BookStoreManagerment.view;

import BookStoreManagerment.model.Account;
import BookStoreManagerment.model.ERole;
import BookStoreManagerment.service.AccountService;

import java.sql.SQLOutput;

import static zStringFormat.formatLongInput.inputLong;
import static zStringFormat.isContinue.isContinue;

public class LoginView extends GenericView {
    private AccountService accountService;

    public LoginView(){
        accountService = new AccountService();
    }

    @Override
    public void launcher() {


        boolean isContinue = true;
        do {
            System.out.println("-----------------");
            System.out.println("Menu Đăng nhập  |");
            System.out.println("-----------------");
            String accountIn = checkAccount();
            String passwordIn = checkPassword();
            Account account = accountService.checkAccountPassword(accountIn, passwordIn);
            if(account.getErole() == ERole.ADMIN) {
                menuAdminView(account);
            } else {
                menuUserView(account);
            }
            isContinue = isContinue("Đăng nhập", "Hoàn toàn ");
        } while (isContinue);
    }

    public void menuAdminView(Account account) {
        GenericView view;
        boolean isContinue = true;
        do {
            System.out.println("-------------------------");
            System.out.println("Menu Admin");
            System.out.println("-------------------------");
            System.out.println("[1] Quản lý sách        |");
            System.out.println("[2] Quản lý hoá đơn     |");
            System.out.println("[3] Quản lý account     |");
            System.out.println("-------------------------");
            String choiceStr = inputLong();
            if( choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    view = new BookView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 2:
                    view = new OrderView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 3:
                    view = new AccountView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                default:
                    isContinue = isContinue("Menu Admin", "Quay lại đăng nhập");
            }
        } while(isContinue);
    }

    public void menuUserView(Account account) {
        GenericView view;
        boolean isContinue = true;
        do {
            System.out.println("--Menu User--");
            System.out.println("[1] Quản lý sách");
            System.out.println("[2] Quản lý hoá đơn");
            String choiceStr = inputLong();
            if( choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    view = new BookView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 2:
                    view = new OrderView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                default:
                    isContinue = isContinue("Menu account", "Quay lại đăng nhập");
            }
        } while (isContinue);
    }

    private String checkAccount(){
        boolean checkAccount = true;
        String accountIn = "";
        do {
            Account account = new Account();
            System.out.println("--Nhập account--");
            accountIn = scanner.nextLine();
            if(accountIn.equals(".")){
                break;
            }
            account = accountService.checkAccount(accountIn);
            if(account == null){
                checkAccount= false;
                System.out.println("--Tài khoản không tồn tại, mời nhập lại--");
            } else {
                checkAccount = true;
            }
        } while (checkAccount == false);
        return accountIn;
    }

    private String checkPassword(){
        boolean checkPassword = true;
        String passwordIn = "";
        do {
            Account password = new Account();
            System.out.println("--Nhập password--");
            passwordIn = scanner.nextLine();
            if(passwordIn.equals(".")){
                break;
            }
            password = accountService.checktPassword(passwordIn);
            if(password == null){
                checkPassword = false;
                System.out.println("--Mật khẩu sai, vui lòng nhập lại--");
            } else {
                checkPassword = true;
            }
        } while (checkPassword == false);
        return passwordIn;
    }
}
