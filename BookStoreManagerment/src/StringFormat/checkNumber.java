package StringFormat;

import java.util.Scanner;

public class checkNumber {
    public static void checkNumberIntoLongAndPosity(long number){
        Scanner sc = new Scanner(System.in);
//        int numberer;
        do {
            System.out.println("Please enter a positive number!");
            while (!sc.hasNextLong()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            number = sc.nextLong();
        } while (number <= 0);
        System.out.println("Thank you! Got " + number);
    }
}
