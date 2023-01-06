package zStringFormat;

import java.util.Scanner;

public class isContinue {
    static Scanner scanner = new Scanner(System.in);
    public static boolean isContinue(String string,String string2) {
        do {
            System.out.println("--Bạn có muốn tiếp tục " + string + "?--");
            System.out.println("[y] Đồng ý");
            System.out.println("[n] Thoát (" + string2 + ")");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("--Chọn sai chức năng--");
                    System.out.println("--Nhập lại--");
                    break;
            }
        } while (true);
    }
}
