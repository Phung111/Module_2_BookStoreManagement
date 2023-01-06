package BookStoreManagerment.utils;

import java.util.regex.Pattern;

public class ValidateUtils {

    public static final String LONGNUMBER_PATTERN = "^[0-9][0-9]*$";
    public static boolean isLongNumber(String number){
        return Pattern.matches(LONGNUMBER_PATTERN, number);
    }
//    public static final String DATE_PATTERN = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String DATE_PATTERN = "^(0?[1-9]|[12][0-9]|3[01])[\\-](0?[1-9]|1[012])[\\-]\\d{4}$";
    public static boolean isDate(String date){
        return Pattern.matches(DATE_PATTERN, date);
    }

    public static final String ADDRESS_PATTERN = "^[A-Za-z0-9][A-Za-z0-9\\s]{7,19}$";

    public static boolean isAddressValid(String address) {
        return Pattern.matches(ADDRESS_PATTERN, address);
    }
}
