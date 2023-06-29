package zStringFormat;

import BookStoreManagerment.utils.ValidateUtils;

import java.util.Scanner;

public class formatLongInput {
    static Scanner scanner = new Scanner(System.in);

    public static String inputLong() {
        boolean isLong = true;
        String longNumber;
        do {
            longNumber = scanner.nextLine();
            if (longNumber.equals(".")){
                longNumber = ".";
                break;
            } else {
                isLong = ValidateUtils.isLongNumber(String.valueOf(longNumber));
                if (isLong == false){
                    System.out.println("--Dữ liệu nhập cần là 1 số, vd: 123456--");
                    System.out.println("--Nhập lại--");
                }
            }
        } while (isLong == false);
        return longNumber;
    }
    public static String inputDate(){
        boolean isDate = true;
        String date;
        do{
            date = scanner.nextLine();
            if(date.equals(".")){
                date = ".";
                break;
            } else {
                isDate = ValidateUtils.isDate(String.valueOf(date));
                if (isDate == false){
                    System.out.println("--Dữ liệu nhập không đúng--");
                    System.out.println("--Nhập lại--");
                }
            }
        } while (isDate == false);
        return date;
    }

}
