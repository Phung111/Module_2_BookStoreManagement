package BookStoreManagerment.utils;

import BookStoreManagerment.view.Options;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AppUtils {

    static Scanner scanner = new Scanner(System.in);

    public static long retryParseLongInput(){
        //parse kiểm tra thông báo nhập liệu ID kiểu long cho người dùng
        long result;
        do {
            try {
                result = Long.parseLong(scanner.nextLine());
                return result;
            } catch (Exception e){
                System.out.println("Nhập sai dữ liệu.Kiểm tra lại.");
                System.out.print("Nhập lại: ");
            }
        } while (true);
    }

    public static boolean isRetry(Options option){
        do {
            switch (option){
                case SHOW,ADD,EDIT,REMOVE,FIND,SORT,STATISTICAL:
                    System.out.println("[c]-continue    để tiếp tục");
                    System.out.println("[b]-back        để trở lại");
                    System.out.println("[e]-exit        để thoát");
                    break;
                default:
                    throw new IllegalStateException("Giá trị không đúng: " + option);
            }
            System.out.print("--Chọn chức năng--");
            String choice = scanner.nextLine();
            switch (choice){
                case "c":
                    return true;
                case "b":
                    return false;
                case "e":
                    System.out.println("--Thoát chương trình--");
                    System.exit(0);
                    break;
                default:
                    System.out.println("--Chọn sai chức năng. Kiểm tra và nhập lại--");
                    break;
            }
        } while (true);
    }


}
