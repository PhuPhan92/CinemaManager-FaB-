package View;

import App.InitApp;
import App.SupportApp;
import Model.EGender;
import Model.EPosition;
import Model.User;
import Service.UserService;
import Unit.DateUnit;
import Unit.ValidateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private DateUnit dateUnit = new DateUnit();
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    public static LoginView loginView = new LoginView();
    public UserView() {
        userService = new UserService();
    }
    public void renderUserMenu() {
        System.out.println("Customer manager Menu");
        System.out.println("1. Show list customer");
        System.out.println("2. Add new customer");
        System.out.println("3. Edit customer's profile");
        System.out.println("4. Delete customer");
        System.out.println("5. Find customers by name");
        System.out.println("6. Return");
        System.out.println("7. Exit System");


    }
    public void renderUpdateUserMenu() {
        System.out.println("Update Customer Menu");
        System.out.println("1. Update Name");
        System.out.println("2. Update Phone Number");
        System.out.println("3. Update Address");
        System.out.println("4. Return Menu");
        System.out.println("5. Exit System");

    }
    public void userlaucher() {
        boolean checkAction = false;
        do{
            try {
                renderUserMenu();
                int actionMenuProduct = Integer.parseInt(scanner.nextLine());
                switch (actionMenuProduct) {
                    case 1:
                        showUserView();
                        break;
                    case 2:
                        addNewUser();
                        break;
                    case 4:
                        deleteUser();
                        break;
                    case 3:
                        editUserView();
                        break;
                    case 5:
                        findUserByName();
                        break;
                    case 6:
                        loginView.renderAdminMenu();
                        break;
                    case 7:
                        System.exit(5);
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Error value!Type again!");
                checkAction = true;
            }
            checkAction = checkActionContinue();
        }while (checkAction);
    }
    public boolean checkActionContinue() {
        boolean checkActionContinue = false;
        do {
            System.out.println("Continue? Y/N");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    checkActionContinue = true;
            }
        } while (checkActionContinue);
        return false;
    }
    public void showUserView() {
        System.out.println("=======================================================================================================================================================");
        System.out.printf("%10s|%20s|%15s|%15s|%15s|%15s|%12s|%12s|%20s\n","ID","NAME","USER NAME","PASSWORD","MOBILE","ADDRESS","GENDER","POSITION","CREAT DAY");
        System.out.println("=======================================================================================================================================================");
        for (User p : userService.getUser()) {
            System.out.println(p.toView());
            System.out.println("=======================================================================================================================================================");
        }
    }
    private void showUserView(List<User> users) {
        System.out.println("=======================================================================================================================================================");
        System.out.printf("%10s|%20s|%15s|%15s|%15s|%15s|%12s|%12s|%20s\n","ID","NAME","USER NAME","PASSWORD","MOBILE","ADDRESS","GENDER","POSITION","CREAT DAY");
        System.out.println("=======================================================================================================================================================");
        for (User p : users) {
            System.out.println(p.toView());
            System.out.println("=======================================================================================================================================================");
        }
    }
    public void show(User user) {
        System.out.printf("%10s|%20s|%15s|%15s|%15s|%15s|%12s|%12s|%20s\n","ID","NAME","USER NAME","PASSWORD","MOBILE","ADDRESS","GENDER","POSITION","CREAT DAY");
        System.out.println("=======================================================================================================================================================");
        System.out.println(user.toView());
    }
    private void addNewUser() {
        boolean check;
        do {
        check = false;
        try {long id = System.currentTimeMillis() / 1000;
            String name = inputName();
            String userName = inputUsername();
            String pw = inputPassword("");
            String mobile = inputPhone();
            String address = inputAddress() ;
            System.out.println("Enter customer's gender: 1 - Male/ 2 - Female");
            int genderID = Integer.parseInt(scanner.nextLine());
            EGender gender = EGender.toEGender(genderID);
            EPosition pos = EPosition.USER;
            Date day = new Date();
            User customer = new User(id, name,userName, pw,mobile, address, gender,pos, day);
            System.out.println("Please check new customer's information");
            show(customer);
            System.out.println("Do you want to save? Y/N");
            String choice = scanner.nextLine();
            choice = choice.trim().toUpperCase();
            switch (choice) {
                case "Y":
                    userService.addCustomer(customer);
                    System.out.println("---------Done---------");
                    showUserView();
                    break;
                case "N":
                    userlaucher();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            System.out.println("Error value!Type again!");
            check = true;
        }

        }while (check);
    }
    public String inputName() {
        System.out.println("Enter Customer's name");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
            System.out.println("NAME IS NOT MATCH (THE FIRST LETTERS MUST BE CAPITALIZED)");
            System.out.print("ENTER FULLNAME AGAIN: ");
        }
        return fullName;
    }
    public String inputUsername() {
        System.out.println("Enter Customer's user name");
        String username;

        do {
            if (!ValidateUtils.isUsernameValid(username = SupportApp.retryString())) {
                System.out.println("YOUR USERNAME IS NOT MATCH (>7 charactors, excluding special characters)");
                System.out.println("\"ENTER USERNAME: ");
                continue;
            }
            if (userService.existsByUsername(username)) {
                System.out.println("YOUR USERNAME IS EXIST, TYPE AGAIN");
                System.out.println("ENTER USERNAME: ");
                continue;
            }
            break;
        } while (true);
        return username;
    }
    public String inputPassword(String name) {
        System.out.println(" Enter password for " + name + ": ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("PASSWORD MUST MINIMUM 8 CHARACTERS, INCLUDING 1 CAPITAL, 1 NUMBER, 1 SPECIAL CHARACTER");
            System.out.print("TYPE PASSWORD AGAIN: ");
        }
        return password;
    }
    public String inputPhone() {
        String phone;
        do {
            System.out.println("Enter Customer's mobile ");
            phone = scanner.nextLine();
            if (phone.isEmpty()) {
                break;
            }
            if (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("YOUR PHONENUMBER IS NOT MATCH (START IS 0 AND HAVE 10 NUMBERS) ");
                continue;
            }
            if (userService.existsByPhone(phone)) {
                System.out.println("THIS PHONENUMBER IS EXIST, TYPE AGAIN");
                System.out.print("ENTER PHONENUMBER: ");
                continue;
            }
            break;
        } while (true);
        return phone;
    }
    private String inputAddress() {
        String address;
        System.out.println("Enter Customer's address");
        address = scanner.nextLine();
        do {
            if (address.trim().isEmpty()) {
                System.out.println("DONT TYPE SPACE");
                System.out.print("TYPE AGAIN: ");
                address = scanner.nextLine();
            }
        } while (address.trim().isEmpty());
        return address;
    }
    private void deleteUser() {
        boolean checkRemoveAction;
        do {
            checkRemoveAction = false;
            showUserView();
            System.out.println("Enter ID User you want to remove");
            Long idUserDel = inputId();
            System.out.println("Are you sure you want to delete this user?");
            String alert = scanner.nextLine();
            if (alert.toLowerCase().equals("y")) {
                userService.deleteCustomerById(idUserDel);
            }
            if (alert.toLowerCase().equals("n")) {
                showUserView();
            }
            checkRemoveAction = InitApp.checkContinueRemoveUser();
        } while (checkRemoveAction);
        checkActionContinue();
    }
    private Long inputId() {
        Long id;
        boolean isRetry = false;
        do {
            id = Long.parseLong(scanner.nextLine());
            boolean exist = userService.existById(id);
            if (!exist) {
                System.out.println("ID is not exist");
                System.out.println("1.Continue \t|\t 2. Return \t|\t 0. Exit");
                do {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            deleteUser();
                            break;
                        case 2:
                            userlaucher();
                            break;
                        case 0:
                            System.exit(5);
                        default:
                            System.out.println("Error Value! Type again!");
                            break;
                    }
                } while (true);
            }

        } while (isRetry);
        return id;
    }
    private void editUserView() {
        boolean isRetry = false;
        do {
            try {
                showUserView();
                System.out.println("Enter ID User you want to update");
                Long id = inputId();
                renderUpdateUserMenu();
//                BannerApp.menuBanner("Update-UserView");
                int option = SupportApp.retryChoose(1, 5);
                User newUser = userService.findCustomerById(id);
                String name;
                String phone;
                String address;
                String email;
                switch (option) {
                    case 1:
                        name = inputName();
                        newUser.setName(name);
                        userService.updateCustomerById(id,newUser);
                        System.out.println("Update-Success");
                        break;
                    case 2:
                        phone = inputPhone();
                        newUser.setMobile(phone);
                        userService.updateCustomerById(id,newUser);
                        System.out.println("Update-Success");
                        break;
                    case 3:
                        address = inputAddress();
                        newUser.setAddress(address);
                        userService.updateCustomerById(id,newUser);
                        System.out.println("Update-Success");
                        break;
                    case 4:
                        userlaucher();
                        break;
                    case 5:
                        System.exit(5);
                }
                isRetry = option != 5 && isRetry;

            } catch (Exception e) {
                System.out.println("Error! Type again");
            }
        } while (isRetry);
    }
    public void findUserByName() {
        System.out.println("Enter some text to search");
        String s = scanner.nextLine();
        List<User> user = new ArrayList<>();
        for (User p : userService.getUser()) {
            if (p.getName().toUpperCase().contains(s.toUpperCase())) {
                user.add(p);
            }
        }
        if (user.size() == 0) {
            System.out.println("This FAB does not exist");
        } else {
            showUserView(user);
        }
    }

    public static void main(String[] args) {
        UserView u = new UserView();
        u.userlaucher();

    }
}
