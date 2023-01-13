package View;

import App.InitApp;
import App.SupportApp;
import Comporator.ComparatorDecreasingByName;
import Comporator.ComparatorDecreasingByPrice;
import Comporator.ComparatorIncreasingByName;
import Comporator.ComparatorIncreasingByPrice;
import Model.*;

import Service.FABService;
import Unit.ValidateUtils;


import java.util.*;

public class FABView {
    private Scanner scanner = new Scanner(System.in);
    private FABService fabService;
    public static LoginView loginView = new LoginView();
    public FABView() {
        fabService = new FABService();
    }
    public void renderFABMenu() {
        System.out.println("Food and Beverage manager Menu");
        System.out.println("1. Show list FAB");
        System.out.println("2. Add new FAB");
        System.out.println("3. Edit FAB's info");
        System.out.println("4. Delete FAB");
        System.out.println("5. Sort FAB");
        System.out.println("6. Find FAB by name");
        System.out.println("7. Return");
        System.out.println("8. Exit System");
    }
    public void renderUpdateFABMenu() {
        System.out.println("Update FAB Menu");
        System.out.println("1. Update Name");
        System.out.println("2. Update Size");
        System.out.println("3. Update Price");
        System.out.println("4. Update Status");
        System.out.println("5. Return Menu");
        System.out.println("6. Exit System");
    }
    public void FABlaucher() {
        boolean checkAction = false;
        do{
            renderFABMenu();
            int actionMenuProduct = Integer.parseInt(scanner.nextLine());
            switch (actionMenuProduct) {
                case 1:
                    showFABView();
                    break;
                case 2:
                    addNewFAB();
                    break;
                case 4:
                    deleteFAB();
                    break;
                case 3:
                    editFaBView();
                    break;
                case 5:
                    sortFWBView(fabService.getAllFAB());
                    break;
                case 6:
                    searchFaBByNameView();
                    break;
                case 7:
                    loginView.renderAdminMenu();
                    break;
                case 8:
                    System.exit(5);
                    break;
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
    public void showFABView() {
        System.out.println("======================================================================");
        System.out.printf("%10s|%20s|%10s|%15s|%10s\n","ID","NAME","SIZE","PRICE","STATUS");
        System.out.println("======================================================================");
        for (FAB p : fabService.getAllFAB()) {
            System.out.println(p.toFABView());
            System.out.println("======================================================================");
        }
    }
    private void showFABView(List<FAB> fabs) {
        System.out.println("======================================================================");
        System.out.printf("%10s|%20s|%10s|%15s|%10s\n","ID","NAME","SIZE","PRICE","STATUS");
        System.out.println("======================================================================");
        for (FAB p : fabs) {
            System.out.println(p.toFABView());
            System.out.println("======================================================================");
        }
    }
    public void show(FAB fab) {
        System.out.printf("%10s|%20s|%10s|%15s|%10s\n","ID","NAME","SIZE","PRICE","STATUS");
        System.out.println("======================================================================");
        System.out.println(fab.toFABView());
    }
    private void addNewFAB() {
        boolean check;
        do {
            check = false;
            try {
                long id = System.currentTimeMillis() / 1000;
                String name = inputFABName();
                boolean checkName = fabService.existByName(name);
                if(checkName){
                    System.out.println("FaB product is already on the list");
                    addNewFAB();
                }else{
                    continue;
                }
                System.out.println("Enter size: 1 - M/ 2 - L/ 3 - OTHER");
                int sizeID = Integer.parseInt(scanner.nextLine());
                ESize size = ESize.toSize(sizeID);
                double price = inputPrice();
                System.out.println("Enter size: 1 - STOCKING/ 2 - SOLD_OUT");
                int statusID = Integer.parseInt(scanner.nextLine());
                EStatus status = EStatus.toStatus(statusID);
                FAB fab = new FAB(id, name,size, price,status);
                System.out.println("Please check new staff's information");
                show(fab);
                System.out.println("Do you want to save? Y/N");
                String choice = scanner.nextLine();
                choice = choice.trim().toUpperCase();
                switch (choice) {
                    case "Y":
                        fabService.addFAB(fab);
                        System.out.println("---------Done---------");
                        showFABView();
                        break;
                    case "N":
                        FABlaucher();
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
    public String inputFABName (){
        String nameProduct;
        boolean checkContinueAction;
        do {
            checkContinueAction = true;
            System.out.println(" Enter FAB name");
            nameProduct = scanner.nextLine();
            if (ValidateUtils.validateProductName(ValidateUtils.removeAccent(nameProduct))){
                checkContinueAction = false;
            }
            else System.out.println("The name of product you entered DO NOT MATCH please try again.");
        }
        while (checkContinueAction);
        return nameProduct;
    }
    public double inputPrice (){
        double price = 0.0;
        boolean checkInputPrice = false;
        do {
            try {
                checkInputPrice = false;
                System.out.println("Enter price");
                price = Double.parseDouble(scanner.nextLine());
                if (price<0||price>10000000){
                    System.out.println("Price must be less than 10000000 and greater than 0");
                    checkInputPrice = true;
                }
            }
            catch (Exception e){
                System.out.println("Price is not empty and must a number");
                checkInputPrice = true;
            }
        }
        while (checkInputPrice);
        return price;
    }
    public void deleteFAB() {
        boolean checkRemoveAction;
        do {
            checkRemoveAction = false;
            showFABView();
            System.out.println("Enter ID User you want to remove");
            Long idfabDel = inputId();
            System.out.println("Are you sure you want to delete this user?");
            String alert = scanner.nextLine();
            if (alert.toLowerCase().equals("y")) {
                fabService.deleteFABById(idfabDel);
            }
            if (alert.toLowerCase().equals("n")) {
                showFABView();
            }
            checkRemoveAction = InitApp.checkContinueRemoveUser();
        } while (checkRemoveAction);
        FABlaucher();
    }
    public Long inputId() {
        Long id;
        boolean isRetry = false;
        do {
            id = Long.parseLong(scanner.nextLine());
            boolean exist = fabService.existById(id);
            if (!exist) {
                System.out.println("ID is not exist");
                System.out.println("1.Continue \t|\t 2. Return \t|\t 0. Exit");
                do {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            deleteFAB();
                            break;
                        case 2:
                            FABlaucher();
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
    public Long inputEditId() {
        Long id;
        boolean isRetry = false;
        do {
            id = Long.parseLong(scanner.nextLine());
            boolean exist = fabService.existById(id);
            if (!exist) {
                System.out.println("ID is not exist");
                System.out.println("1.Continue \t|\t 2. Return \t|\t 0. Exit");
                do {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            editFaBView();
                            break;
                        case 2:
                            FABlaucher();
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
    public void editFaBView() {
        boolean isRetry = false;
        do {
            try {
                showFABView();
                System.out.println("Enter ID User you want to update");
                Long id = inputEditId();
                renderUpdateFABMenu();
//                BannerApp.menuBanner("Update-UserView");
                int option = SupportApp.retryChoose(1, 5);
                FAB newFAB = fabService.findFABById(id);
                String name;
                ESize eSize;
                double price;
                EStatus eStatus;
                switch (option) {
                    case 1:
                        name = inputFABName();
                        newFAB.setName(name);
                        fabService.updateFABById(id,newFAB);
                        System.out.println("Update-Success");
                        break;
                    case 2:
                        System.out.println("Enter size: 1 - M/ 2 - L/ 3 - OTHER");
                        int sizeID = Integer.parseInt(scanner.nextLine());
                        eSize = ESize.toSize(sizeID);
                        newFAB.setESize(eSize);
                        fabService.updateFABById(id,newFAB);
                        System.out.println("Update-Success");
                        break;
                    case 3:
                        price = inputPrice();
                        newFAB.setPrice(price);
                        fabService.updateFABById(id,newFAB);
                        System.out.println("Update-Success");
                        break;
                    case 4:
                        System.out.println("Enter size: 1 - STOCKING/ 2 - SOLD_OUT");
                        int statusID = Integer.parseInt(scanner.nextLine());
                        eStatus = EStatus.toStatus(statusID);
                        newFAB.setEStatus(eStatus);
                        fabService.updateFABById(id,newFAB);
                        System.out.println("Update-Success");
                    case 5:
                        FABlaucher();
                        break;
                    case 6:
                        System.exit(5);
                }
                isRetry = option != 5 && isRetry;

            } catch (Exception e) {
                System.out.println("Error! Type again");
            }
        } while (isRetry);
    }
    public void sortFWBView(List<FAB> fabs) {
        boolean checkSortProduct=false;
        do {
            checkSortProduct = false;
            System.out.println("You want to sort by?");
            System.out.println("1. Sort by Price");
            System.out.println("2. Sort by name");
            String choiceSortProduct = scanner.nextLine();
            switch (choiceSortProduct) {
                case "1":
                    sortByPrice(fabs);
                    break;
                case "2":
                    sortByName(fabs);
                    break;
                case "r":
                    FABlaucher();
                default:
                    checkSortProduct = true;
                    break;
            }
        }
        while (checkSortProduct);
    }
//    How do you want to sort?
    public void sortByPrice(List<FAB> fabs) {
        boolean checkSortByPrice = false;
        Scanner scanner = new Scanner(System.in);
        do {
            checkSortByPrice = false;
            System.out.println("How do you want to sort?");
            System.out.println("1. Increasing");
            System.out.println("2. Decreasing");
//            bannerApp.menuBanner("Sort-by-price");
            int choiceSortByPrice = Integer.parseInt(scanner.nextLine()) ;
            Comparator comparator;
            switch (choiceSortByPrice){
                case 1:
                    comparator = new ComparatorIncreasingByPrice();
                    fabs.sort(comparator);
                    showFABView(fabs);
                    break;
                case 2:
                    comparator = new ComparatorDecreasingByPrice();
                    fabs.sort(comparator);
                    showFABView(fabs);
                    break;
                case 0:
//                    eventApp.returnEvent("Return-sorting-menu");
                    break;
                default:
                    System.out.println("Error value! Type again");
                    checkSortByPrice = true;
                    break;
            }
        }
        while (checkSortByPrice);
    }
    public void sortByName(List<FAB> fabs) {
        boolean checkSortByPrice = false;
        Scanner scanner = new Scanner(System.in);
        do {
            checkSortByPrice = false;
            System.out.println("How do you want to sort?");
            System.out.println("1. Increasing");
            System.out.println("2. Decreasing");
//            bannerApp.menuBanner("Sort-by-price");
            int choiceSortByPrice = Integer.parseInt(scanner.nextLine()) ;
            Comparator comparator;
            switch (choiceSortByPrice){
                case 1:
                    comparator = new ComparatorIncreasingByName();
                    fabs.sort(comparator);
                    showFABView(fabs);
                    break;
                case 2:
                    comparator = new ComparatorDecreasingByName();
                    fabs.sort(comparator);
                    showFABView(fabs);
                    break;
                case 0:
//                    eventApp.returnEvent("Return-sorting-menu");
                    break;
                default:
                    System.out.println("Error value! Type again");
                    checkSortByPrice = true;
                    break;
            }
        }
        while (checkSortByPrice);
    }
    public void searchFaBByNameView() {
        System.out.println("Enter some text to search");
        String s = scanner.nextLine();
        List <FAB> fab = new ArrayList<>();
        for (FAB p : fabService.getAllFAB()) {
            if(p.getName().toUpperCase().contains(s.toUpperCase())){
                fab.add(p);
            }
        }
        if(fab.size()==0){
            System.out.println("This FAB does not exist");
        }else{
            showFABView(fab);
        }
    }
    public static void main(String[] args) {
        FABView f = new FABView();
        f.FABlaucher();
    }
}
