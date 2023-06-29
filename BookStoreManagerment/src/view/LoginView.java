package view;

import model.Account;
import model.ERole;
import service.AccountService;

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
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("◤━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("┃       Menu Login      ┃");
            System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━◢");
            String accountIn = checkAccount();
            if(accountIn.equals(".")){
                break;
            }
            String passwordIn = checkPassword();
            if(passwordIn.equals(".")){
                break;
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            Account account = accountService.checkAccountPassword(accountIn, passwordIn);
            if(account.getErole() == ERole.ADMIN) {
                menuAdminView(account);
            } else {
                menuUserView(account);
            }
            isContinue = isContinue("Đăng nhập", "(Thoát hoàn toàn)");
        } while (isContinue);
    }

    public void menuAdminView(Account account) {
        GenericView view;
        boolean isContinue = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                    ◤━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                    ┃       Menu Admin      ┃");
            System.out.println("                                                                    ◣━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                    ┃[1] Quản lý hoá đơn    ┃");
            System.out.println("                                                                    ┃[2] Quản lý sách       ┃");
            System.out.println("                                                                    ┃[3] Quản lý account    ┃");
            System.out.println("                                                                    ◣━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if( choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    view = new OrderView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 2:
                    view = new BookView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 3:
                    view = new AccountView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                default:
                    isContinue = isContinue("tiếp tụ Menu Admin", "(quay lại Menu Login)");
            }
        } while(isContinue);
    }

    public void menuUserView(Account account) {
        GenericView view;
        boolean isContinue = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                    ◤━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                    ┃       Menu User       ┃");
            System.out.println("                                                                    ◣━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                    ┃[1] Quản lý hoá đơn    ┃");
            System.out.println("                                                                    ┃[2] Quản lý sách       ┃");
            System.out.println("                                                                    ◣━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if( choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    view = new OrderView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                case 2:
                    view = new BookView();
                    view.setAccount(account);
                    view.launcher();
                    break;
                default:
                    isContinue = isContinue("tiếp tục Menu User", "(quay lại Menu Login)");
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
            password = accountService.checkPassword(passwordIn);
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
