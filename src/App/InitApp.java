package App;

import java.util.Scanner;

public class InitApp {
    public  static Scanner scanner = new Scanner(System.in);
    public static String isNotEmpty (){
        String value = scanner.nextLine();
        if (value.isEmpty()){
            System.out.println("Username must be not empty. Please try again!");
        }
        return value;
    }
    public static boolean checkContinueActionMenu() {
        System.out.println("Are your sure to continue: 【Yes】(Y) or 【No】(N)");
        String choiceContinueAction = scanner.nextLine();
        switch (choiceContinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueActionOrder (){
        System.out.println("Do you want to continue Order: 【Yes】(Y) or 【No】(N)");
        String choiceContinueActionOrder = scanner.nextLine();
        switch (choiceContinueActionOrder){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueUpdateOrder (){
        System.out.println("Do you want to continue update Order: 【Yes】(Y) or 【No】(N)");
        String choiceContinueActionOrder = scanner.nextLine();
        switch (choiceContinueActionOrder){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueAddProduct() {
        System.out.println("Do you want to continue adding Product: 【Yes】(Y) or 【No】(N)");
        String choiceContinueAction = scanner.nextLine();
        switch (choiceContinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueRemoveUser() {
        System.out.println("Do you want to continue remove User: 【Yes】(Y) or 【No】(N)");
        String choiceContinueAction = scanner.nextLine().toLowerCase();
        switch (choiceContinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }
    public static boolean checkContinueAddOrder() {
        System.out.println("Do you want to continue adding Order: 【Yes】(Y) or 【No】(N)");
        String choiceContinueAction = scanner.nextLine();
        switch (choiceContinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueWatchPage (){
        System.out.println("Do you want to continue watch page: 【Yes】(Y) or 【No】(N)");
        String choicecontinueAction = scanner.nextLine();
        switch (choicecontinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }

    public static boolean checkContinueUpdateMenu (){
        System.out.println("Do you want to continue update: 【Yes】(Y) or 【No】(N)");
        String choicecontinueAction = scanner.nextLine();
        switch (choicecontinueAction){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }
    public static boolean checkContinueSearchOrderStatusMenu (){
        System.out.println("Do you want to continue search Order by Status: 【Yes】(Y) or 【No】(N)");
        String choice = scanner.nextLine();
        switch (choice){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }
    public static boolean checkContinueSearchOrderByDurationTime (){
        System.out.println("Do you want to continue search Order by Duration Time: 【Yes】(Y) or 【No】(N)");
        String choice = scanner.nextLine();
        switch (choice){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }
    public static boolean checkContinueActionShowProfit (){
        System.out.println("Do you want to continue show profit: 【Yes】(Y) or 【No】(N)");
        String choice = scanner.nextLine();
        switch (choice){
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Error Value. Please Type again");
                return true;
        }
    }
}
