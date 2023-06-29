package zStringFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateFormat {
    public static String ddMMMyyyy(Date date){
        DateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
