package BookStoreManagerment.view.login;

import java.util.Scanner;
import BookStoreManagerment.model.Account;
import BookStoreManagerment.model.AccountTypes;
import BookStoreManagerment.service.AccountService;

public class LoginView {
    static Scanner scanner = new Scanner(System.in);
    private static AccountService accountService = new AccountService();

    public static void laucher(){
        System.out.println("-------------------------");
        System.out.println("Đăng nhập tài khoản     |");
        System.out.println("-------------------------");
        System.out.println("[1]Đăng nhập            |");
        System.out.println("[0]Thoát chương trình   |");
        System.out.println("-------------------------");
        System.out.println("--Chọn chức năng--");
        boolean isLaucherContinue = false;
        do {
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    accountLaucher();
                    break;
                case "0":
                    System.out.println("--Thoát chương trình--");
                    System.exit(0);
                    break;
                default:
                    System.out.println("--Chọn sai chức năng. Kiểm tra và nhập lại");
                    isLaucherContinue = true;
                    break;
            }
        } while (isLaucherContinue);
    }

    private static void accountLaucher() {
        boolean isChoice = true;
        Account account = null;
        try {
            account = loginAccount();
        } catch (NullPointerException nullPointerException){
            if (account == null){
                System.out.println("--Thoát chương trình--");
                System.exit(0);
            }
        }
        if(account.getAccountTypes() == AccountTypes.ADMIN){
            do {
                menuAdminAccount();
                String choice = scanner.nextLine();
                switch (choice){
                    case "1":

                }

            } while (isChoice);
        }

    }

    private static Account loginAccount() {
        boolean isReLogin = false;
        System.out.println("--Đăng nhập tài khoản--");
        do {
            String username = loginUsername(false);
            String password = loginPassword(false);
            System.out.println("--Đăng nhập thành công--");
            Account account = accountService.login(username,password);
            return account;
        } while (isReLogin);
    }
    private static String loginUsername(boolean isReLoginUsername){
        do {
            System.out.println("--Username--");
            String username = scanner.nextLine();
            if (!accountService.isLoginUsername(username)) {
                System.out.println("--Tài khoản không tồn tại--");
                System.out.println("--Mời bạn nhập lại--");
                isReLoginUsername = true;
            } else {
                return username;
            }
        } while (isReLoginUsername);
        return null;
    }

    public static String loginPassword(boolean isReLoginPassword){
        do {
            System.out.println("--Password--");
            String password = scanner.nextLine();
            switch (password){
                case "*":
                    String username = loginUsername(false);
                    return username;
                default:
                    if(!accountService.isLoginUPassword(password)){
                        System.out.println("--Mật khẩu không chính xác--");
                        System.out.println("--Mời bạn nhập lại--");
                        isReLoginPassword = false;
                    }
                    break;
            }
        } while (isReLoginPassword);
        return null;
    }


    private static void menuAdminAccount() {
        System.out.println("-------------------------");
        System.out.println("Quản lý tài khoản admin |");
        System.out.println("-------------------------");
        System.out.println("[1]Quản lý tài khoản    |");
        System.out.println("[2]Quản lý hoá đơn      |");
        System.out.println("[3]Quản lý kho          |");
        System.out.println("[4]Thống kê             |");
        System.out.println("[9]Quay lại             |");
        System.out.println("[0]Thoát                |");
        System.out.println("-------------------------");
        System.out.println("--Chọn chức năng--");
    }

    private static void menuUserAccount() {
        System.out.println("-------------------------");
        System.out.println("Quản lý tài khoản user  |");
        System.out.println("-------------------------");
        System.out.println("[2]Quản lý hoá đơn      |");
        System.out.println("[3]Quản lý kho          |");
        System.out.println("[9]Quay lại             |");
        System.out.println("[0]Thoát                |");
        System.out.println("-------------------------");
        System.out.println("--Chọn chức năng--");
    }

}
