package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
//    private static String pattern = "dd-MM-yyyy hh:mm:ss";
    private static String pattern = "dd-MM-yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    public static String convertDateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String strDate) {
        try {
            return simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
