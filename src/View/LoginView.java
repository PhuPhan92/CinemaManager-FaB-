package View;

import App.InitApp;
import Model.EPosition;
import Model.User;
import Service.FABService;
import Service.UserService;
import Unit.CountDownThread;

import java.util.Scanner;



public class LoginView {
    public static UserView userView = new UserView();
    public static FABView fabView = new FABView();
    public static OrderView orderView = new OrderView();
    public static FABService fabService = new FABService();
    private static StaffView staffView = new StaffView();

    private UserService userService;
    public LoginView() {
        userService = new UserService();
    }
    Scanner scanner = new Scanner(System.in);
    public void loginLauncher()  {
        boolean checkUserPassword = false;
        do{
            System.out.println("-------------LOGIN------------");
            System.out.println("--------ENTER USERNAME:-------");
            String username = isNotEmpty();
            System.out.println("--------ENTER PASSWORD:-------");
            String password = isNotEmpty();
            User checkUser = userService.checkUserNamePassword(username, password);
            if ( checkUser != null) {
                // Co ton tai user
                if (checkUser.getEPosition() == EPosition.MANAGER) {
                    menuAdminView(checkUser);
                }else if (checkUser.getEPosition() == EPosition.STAFF){
                    menuStaffView(checkUser);
                }
            }else{
                System.out.println("WRONG !! TRY AGAIN");
                checkUserPassword = true;
            }
        }while (checkUserPassword);

    }

    private String isNotEmpty() {
        String value = scanner.nextLine();
        if (value.isEmpty()){
            System.out.println("Username or Password must be not empty. Please try again!");
            loginLauncher();
        }
        return value;
    }

    private void menuStaffView(User checkUser)  {
        System.out.println("=======================================");
        System.out.println("-----WELCOME TO ASPECT RUM CINEMA------");
        System.out.println("----------It's all just dream----------");
        System.out.println("USER:" + checkUser.getUserName());
        System.out.println("POSITION: " + checkUser.getEPosition());
        boolean checkActionMenuAdminContinue = true;
        do{
            System.out.println("=======================================");
            System.out.println("Please select the task you want to use:");
            System.out.println("1. User Manager");
            System.out.println("2. FaB Manager");
            System.out.println("3. Order Manager");
            System.out.println("4. Return Login");
            int actionMenuAdmin = Integer.parseInt(scanner.nextLine().trim());
            switch (actionMenuAdmin) {
                case 1:
                    userManagerForStaff(checkUser);
                    break;
                case 2:
                    fabManagerForStaff(checkUser);
                    break;
                case 3:
                    orderManagerForStaff(checkUser);
                    break;
                case 4:
                    loginLauncher();
                    break;
                default:
                    checkActionMenuAdminContinue = false;
            }
        }while (checkActionMenuAdminContinue);
    }

    private void orderManagerForStaff(User checkUser) {
        boolean checkActionMenuAdminContinue = true;
        do{
            System.out.println("=======================================");
            System.out.println("Please select the task you want to use:");
            System.out.println("1. Show detail Order ");
            System.out.println("2. Add new Order");
            System.out.println("3. Update Order");
            System.out.println("4. Show all order");
            System.out.println("5. Return Staff Menu");
            int actionMenuAdmin = Integer.parseInt(scanner.nextLine().trim());
            switch (actionMenuAdmin) {
                case 1:
                    orderView.ShowDetailOrder();
                    break;
                case 2:
                    orderView.addNewOrder();
                    break;
                case 3:
                    orderView.editOrder();
                    break;
                case 4:
                    orderView.showAllOrder();
                    break;
                case 5:
                    menuStaffView(checkUser);
                    break;
                default:
                    checkActionMenuAdminContinue = false;
            }
        }while (checkActionMenuAdminContinue);
    }

    private void userManagerForStaff(User checkUser) {
        boolean checkActionMenuAdminContinue = true;
        do{
            System.out.println("=======================================");
            System.out.println("Please select the task you want to use:");
            System.out.println("1. Show list customer");
            System.out.println("2. Find customers by name");
            System.out.println("3. Return Staff Menu");
            int actionMenuAdmin = Integer.parseInt(scanner.nextLine().trim());
            switch (actionMenuAdmin) {
                case 1:
                    userView.showUserView();
                    break;
                case 2:
                    userView.findUserByName();
                    break;
                case 3:
                    menuStaffView(checkUser);
                    break;

                default:
                    checkActionMenuAdminContinue = false;
            }
        }while (checkActionMenuAdminContinue);

    }
    private void fabManagerForStaff(User checkUser) {
        boolean checkActionMenuAdminContinue = true;
        do{
            System.out.println("=======================================");
            System.out.println("Please select the task you want to use:");
            System.out.println("1. Show list FAB");
            System.out.println("2. Sort FAB");
            System.out.println("3. Find FAB by name");
            System.out.println("4. Return Staff Menu");
            int actionMenuAdmin = Integer.parseInt(scanner.nextLine().trim());
            switch (actionMenuAdmin) {
                case 1:
                    fabView.showFABView();
                    break;
                case 2:
                    fabView.sortFWBView(fabService.getAllFAB());
                    break;
                case 3:
                    fabView.searchFaBByNameView();
                    break;
                case 4:
                    menuStaffView(checkUser);
                    break;
                default:
                    checkActionMenuAdminContinue = false;
            }
        }while (checkActionMenuAdminContinue);

    }

    public void menuAdminView(User checkUser)  {
        System.out.println("=======================================");
        System.out.println("-----WELCOME TO ASPECT RUM CINEMA------");
        System.out.println("----------It's all just dream----------");
        System.out.println("USER:" + checkUser.getUserName());
        System.out.println("POSITION: " + checkUser.getEPosition());
        renderAdminMenu();

    }
    public void renderAdminMenu() {
        boolean checkActionMenuAdminContinue = true;
        do{
            System.out.println("=======================================");
            System.out.println("Please select the task you want to use:");
            System.out.println("1. User Manager");
            System.out.println("2. Staff Manager");
            System.out.println("3. FaB Manager");
            System.out.println("4. Order Manager");
            System.out.println("5. Return Login");
            int actionMenuAdmin = Integer.parseInt(scanner.nextLine().trim());
            switch (actionMenuAdmin) {
                case 1:
                    userView.userlaucher();
                    break;
                case 2:
                    staffView.stafflaucher();
                    break;
                case 3:
                    fabView.FABlaucher();
                    break;
                case 4:
                    orderView.OrderLauncher();
                    break;
                case 5:
                    loginLauncher();
                    break;
                default:
                    checkActionMenuAdminContinue = false;
            }
        }while (checkActionMenuAdminContinue);
    }

    public static void main(String[] args) {
        LoginView a = new LoginView();
        a.loginLauncher();
    }
}
