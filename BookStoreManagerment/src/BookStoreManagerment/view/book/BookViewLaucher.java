package BookStoreManagerment.view.book;

import BookStoreManagerment.model.Account;
import BookStoreManagerment.model.AccountTypes;
import BookStoreManagerment.utils.AppUtils;
import BookStoreManagerment.view.Options;

import java.util.Scanner;

public class BookViewLaucher {
    static Scanner scanner = new Scanner(System.in);
    private static final int EXIT = 0;
    private static final int ADMIN_SHOW = 1;
    private static final int ADMIN_ADD = 2;
    private static final int ADMIN_EDIT = 3;
    private static final int ADMIN_REMOVE = 4;
    private static final int ADMIN_SORT = 5;
    private static final int ADMIN_FIND = 6;
//    private static final int SHOW_REMOVE = 7;
//    private static final int RESTORE_REMOVE = 8;
    private static final int ADMIN_RETURN = 9;

    private static final int USER_SHOW = 1;
    private static final int USER_SORT = 2;
    private static final int USER_FIND = 3;
    private static final int USER_RETURN = 4;
    private static final BookView bookView = new BookView();

    private static void adminManagerMenuBook(){
        System.out.println("-------------------------");
        System.out.println("Menu Quản lý sách admin |");
        System.out.println("-------------------------");
        System.out.println("[1]Hiển thị danh sách   |");
        System.out.println("[2]Thêm sách            |");
        System.out.println("[3]Sửa thông tin sách   |");
        System.out.println("[4]Xoá sách             |");
        System.out.println("[5]Sắp xếp sách         |");
        System.out.println("[6]Tìm kiếm sách        |");
        System.out.println("[9]Quay lại             |");
        System.out.println("[0]Thoát                |");
        System.out.println("-------------------------");
    }
    private static void userManagerBook(){
        System.out.println("-------------------------");
        System.out.println("Menu Quản lý sách user  |");
        System.out.println("-------------------------");
        System.out.println("[1]Hiển thị danh sách   |");
        System.out.println("[2]Sắp xếp sách         |");
        System.out.println("[3]Tìm kiếm sách        |");
        System.out.println("[9]Quay lại             |");
        System.out.println("[0]Thoát                |");
        System.out.println("-------------------------");
    }

    public static void bookLauchs(Account account){
        boolean isReturn = true;
        if(account.getAccountTypes() == AccountTypes.ADMIN){
            do {
                adminManagerMenuBook();
                long choice = AppUtils.retryParseLongInput();
                switch ((int) choice){
                    case ADMIN_SHOW:
                        bookView.showBookList();
                        break;
                    case ADMIN_ADD:
                        bookView.addBookView();
                        break;
                    case ADMIN_EDIT:
                        bookView.editBookView();
                        break;
                    case ADMIN_REMOVE:
                        bookView.deleteBookView();
                        break;
                    case ADMIN_SORT:
                        bookView.sortBookView();
                        break;
                    case ADMIN_FIND:
                        bookView.searchBookView();
                        break;
                    case ADMIN_RETURN:
                        isReturn = false;
                        break;
                    case EXIT:
                        System.out.println("--Thoát chương trình--");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("--Chọn sai chức năng. Kiểm tra và Nhập lại--");
                        isReturn = true;
                        break;
                }
            } while (isReturn);
        }
        if (account.getAccountTypes() == AccountTypes.USER) {
            int choice = 0;
            do {
                userManagerBook();
                System.out.println("[1]Hiển thị danh sách   |");
                System.out.println("[2]Sắp xếp sách         |");
                System.out.println("[3]Tìm kiếm sách        |");
                System.out.println("[9]Quay lại             |");
                System.out.println("[0]Thoát                |");
                System.out.println("-------------------------");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice){
                        case USER_SHOW:
                            bookView.showBookList();
                            break;
                        case USER_SORT:
                            bookView.sortBookView();
                            break;
                        case USER_FIND:
                            bookView.searchBookView();
                            break;
                        case USER_RETURN:
                            isReturn = false;
                            break;
                        case EXIT:
                            System.out.println("--Thoát chương trình--");
                            System.exit(1);
                            break;
                        default:
                            System.out.println("--Chọn sai chức năng. Kiểm tra và Nhập lại--");
                            isReturn = true;
                            break;
                    }
                } catch (Exception e){
                    System.out.println(">Nhập sai cú pháp. Kiểm tra lại.");
                    isReturn = AppUtils.isRetry(Options.FIND);
                }
            } while (isReturn);
        }
    }




}
