package zStringFormat;

import java.util.Scanner;

public class isContinue {
    static Scanner scanner = new Scanner(System.in);
    public static boolean isContinue(String string,String string2) {
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("--Bạn có muốn " + string + "?--");
            System.out.println("[1] Đồng ý");
            System.out.println("[2] Huỷ " + string2);
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");

            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("--Chọn sai chức năng--");
                    System.out.println("--Nhập lại--");
                    break;
            }
        } while (true);
    }

    public static boolean isPay() {
        do {
            System.out.println("--Xác nhận thanh toán--");
            System.out.println("[1] Đồng ý");
            System.out.println("[2] Huỷ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("--Chọn sai chức năng--");
                    System.out.println("--Nhập lại--");
                    break;
            }
        } while (true);
    }


}
