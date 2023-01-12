package Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUnit {
    private static SimpleDateFormat formatter = new SimpleDateFormat("hh:mm dd-MM-yyyy");
    private static SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm");
    public static Date convertStringToDateAndHour(String strDate) {
        try {
            return formatter.parse(strDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return null;
    }
    public static String convertDateAndHourToString(Date date) {
        return formatter.format(date);
    }public static Date convertStringToHour(String strDate) {
        try {
            return formatter2.parse(strDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return null;
    }
    public static String convertHourToString(Date date) {
        return formatter2.format(date);
    }
}
