package App;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class SupportApp {
    static Scanner scanner = new Scanner(System.in);

    public static String retryString() {
        String result;
        while ((result = scanner.nextLine()).isEmpty()) {
            System.out.printf("Dont type Space \n");
            System.out.print("►►►►►► Type again: ");
        }
        return result;
    }

    public static int retryChoose(int min, int max) {
        int option;
        do {
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option > max || option < min) {
                    System.out.println("ERROR VALUE PLEASE TYPE VALUE " + min + " TO " + max );
                    continue;
                }
                break;
            } catch (Exception ex) {
                System.out.println("ERROR VALUE PLEASE TYPE VALUE ");
            }
        } while (true);
        return option;
    }

    public static String formatNumber(double a) {
        String result;
        Locale locale = new Locale("eu", "EU");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        return result = numberFormat.format(a);
    }
}
