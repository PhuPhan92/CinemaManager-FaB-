package View;

import App.InitApp;
import App.SupportApp;
import Model.*;
import Repository.OrderItemRepository;
import Service.FABService;
import Service.OrderItemService;
import Service.OrderService;
import Service.UserService;
import Unit.DateUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public static LoginView loginView  = new LoginView();
    public static void main(String[] args) {
        OrderView o = new OrderView();
        o.OrderLauncher();
    }
    private OrderItemRepository orderItemRepository;
    private OrderItemService orderItemService;
    private OrderService orderService;
    private FABService fabService;
    Scanner scanner = new Scanner(System.in);
    private UserService userService;
    User checkUser;
    public OrderView() {
        orderService = new OrderService();
        fabService = new FABService();
        orderItemService = new OrderItemService();
        orderItemRepository = new OrderItemRepository();
    }
    public void RenderOrderMenu (){
        System.out.println("Order Manager Menu");
        System.out.println("1. Show detail Order ");
        System.out.println("2. Add new Order");
        System.out.println("3. Update Order");
        System.out.println("4. Search Order By Status");
        System.out.println("5. Search Order By Duration Time");
        System.out.println("6. Show all order");
        System.out.println("7. Remove Order  ");
        System.out.println("8. Show Revenue");
        System.out.println("9. Return");
        System.out.println("0. Exit System");
    }
    public void renderUpdateOrderMenu() {
        System.out.println("Update Order Menu");
        System.out.println("1. Update Status");
        System.out.println("2. Update Amount");
        System.out.println("3. Update FaB");
        System.out.println("4. Return Menu");
        System.out.println("5. Exit System");
    }
    public void OrderLauncher(){
        boolean checkAction = false;

        do{
            RenderOrderMenu();
            int actionMenuProduct = Integer.parseInt(scanner.nextLine());
            switch (actionMenuProduct) {
                case 1:
                    ShowDetailOrder();
                    break;
                case 2:
                    addNewOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    searchOrderByStatus();
                    break;
                case 5:
                    searchOrderByTimes();
                    break;
                case 6:
                    showAllOrder();
                    break;
                case 7:
                    deleteOrder();
                    break;
                case 8:
                    showRevenue();
                    break;
                case 9:
                    loginView.renderAdminMenu();
                    break;
                case 0:
                    System.exit(5);
                    break;
            }
            checkAction = checkActionContinue();
        }while (checkAction);
    }
    public void searchOrderByTimes() {
        boolean checkContinue;
        List <Order> list = orderService.getAllOrders();
        do {
            checkContinue = false;
            System.out.println("What Time do you want to find order by?");
            System.out.println("1. By day");
            System.out.println("2. By mouth");
            System.out.println("r. Return Order menu");
//            BannerApp.menuBanner("Search-Order-ByDurationTime");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Order> list1 = searchOrderByDate(list);
                    showAllOrder(list1);
                    break;
                case "2":
                    List<Order> list2 = searchOrderByMonth(list);
                    showAllOrder(list2);
                    break;
                case "r":
                    OrderLauncher();
                default:
                    System.out.println("Error Value. Type again");
                    checkContinue = true;
            }
        }
        while (checkContinue);
    }
    public List<Order> searchOrderByDate (List<Order> list){
        List<Order> orderList = new ArrayList<>();
        System.out.println("Enter Date (dd-mm-yyyy)");
        String sdate = scanner.nextLine();
        for (Order order : list){
            if (getDataByDate(DateUnit.convertDateAndHourToString(order.getDateOrder())).equals(sdate)){
                orderList.add(order);
            }
        }
        return orderList;
    }
    public List<Order> searchOrderByMonth (List<Order> list){
        List<Order> orderList = new ArrayList<>();
        System.out.println("Enter Month (mm)");
        String sMonth = scanner.nextLine();
        for (Order order : list){
            if (getDataByMonth(DateUnit.convertDateAndHourToString(order.getDateOrder())).equals(sMonth)){
                orderList.add(order);
            }
        }
        return orderList;
    }
    public String getDataByDate(String date){
        date = date.trim();
        if (date.indexOf(" ")>=0){
            int index = date.lastIndexOf(" ");
            return date.substring(index+1);
        }
        else return date;
    }
    public String getDataByMonth(String month){
        month = month.trim();
        if (month.indexOf(" ")>=0){
            int index = month.lastIndexOf(" ");
            return month.substring(index+4,index+6);
        }
        else return month;
    }
    public void searchOrderByStatus() {boolean checkOrderStatus;
        do {
            checkOrderStatus = false;
            List<Order> listOrder = orderService.getAllOrders();
            System.out.println("What status do you want to find order by?");
            System.out.println("1. Complete order");
            System.out.println("2. Canceled order");
            System.out.println("r. Return Order menu");
//            BannerApp.menuBanner("Search-Order-Status-View");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "1":
                    List<Order> listOrderResult = new ArrayList<>();
                    for (Order order : listOrder){
                        if ( order.getStatus().getValue().equals("OK")){
                            listOrderResult.add(order);
                        }
                    }
                    showAllOrder(listOrderResult);
                    break;
                case "2":
                    List<Order> listOrderResult2 = new ArrayList<>();
                    for (Order order : listOrder){
                        if ( order.getStatus().getValue().equals("CANCELED")){
                            listOrderResult2.add(order);
                        }
                    }
                    showAllOrder(listOrderResult2);
                    break;
                case "r":
                    OrderLauncher();
            }
        }
        while (checkOrderStatus);
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
    public void showAllOrder(List<Order> list) {
        String fmtOrderHeader = String.format("%-10s|%-10s|%-10s|%-20s|%-15s", "ID ORDER", "ID STAFF", "TOTAL","DAY","STATUS");
        System.out.println(fmtOrderHeader);
        for (Order order : list) {
            String fmtOrderContent = String.format("%-10s|%-10s|%-10s|%-20s|%-15s", order.getId(), order.getIdUser(), order.getTotal(), DateUnit.convertDateAndHourToString(order.getDateOrder()),order.getStatus());
            System.out.println(fmtOrderContent);
        }
    }
    public void showAllOrder() {
        List<Order> orders = orderService.getAllOrders();
        String fmtOrderHeader = String.format("%-10s|%-10s|%-10s|%-20s|%-15s", "ID ORDER", "ID STAFF", "TOTAL","DAY","STATUS");
        System.out.println(fmtOrderHeader);
        for (Order order : orders) {
            String fmtOrderContent = String.format("%-10s|%-10s|%-10s|%-20s|%-15s", order.getId(), order.getIdUser(), order.getTotal(), DateUnit.convertDateAndHourToString(order.getDateOrder()),order.getStatus());
            System.out.println(fmtOrderContent);
        }
    }
    public void ShowDetailOrder() {
        System.out.println("Nhập ID order");
        long idOrder = Long.parseLong(scanner.nextLine());
        Order order = orderService.findOrderById(idOrder);
        System.out.println();
        String fmtOrderDetailHeader = String.format("ID ORDER: %-10s|ID STAFF: %-15s|Total: %-10s|Status: %-10s", order.getId(), order.getIdUser(), order.getTotal(), order.getStatus());
        System.out.println(fmtOrderDetailHeader);

        String fmtOrderItemHeader = String.format("%-20s|%-10s|%-10s|%-10s", "Name Product","Size", "Quantity","Total");
        System.out.println(fmtOrderItemHeader);
        for (OrderItem orderItem : order.getOrderItems()) {
            FAB fab = fabService.findFABById(orderItem.getIdFAB());
            String fmtOrderItem = String.format("%-20s|%-10s|%-10s|%-10s", fab.getName(),fab.getESize(), orderItem.getQuantity(), fab.getPrice()*orderItem.getQuantity());
            System.out.println(fmtOrderItem);
        }
    }
    public void ShowDetailOrder(long id) {
        Order order = orderService.findOrderById(id);
        System.out.println();
        String fmtOrderDetailHeader = String.format("ID ORDER: %-10s|ID STAFF: %-15s|Total: %-10s|Status: %-10s", order.getId(), order.getIdUser(), order.getTotal(), order.getStatus());
        System.out.println(fmtOrderDetailHeader);

        String fmtOrderItemHeader = String.format("%-12s|%-20s|%-10s|%-10s|%-10s", "OrderItemID","Name Product","Size", "Quantity","Total");
        System.out.println(fmtOrderItemHeader);
        for (OrderItem orderItem : order.getOrderItems()) {
            FAB fab = fabService.findFABById(orderItem.getIdFAB());
            String fmtOrderItem = String.format("%-12s|%-20s|%-10s|%-10s|%-10s",orderItem.getId(), fab.getName(),fab.getESize(), orderItem.getQuantity(), fab.getPrice()*orderItem.getQuantity());
            System.out.println(fmtOrderItem);
        }
    }
    public void addNewOrder() {
        boolean check;
        do {
            check = false;
            try {
                Order order = new Order();
                long id = System.currentTimeMillis() / 1000;
                order.setId(id);
                order.setDateOrder(new Date());
                order.setStatus(EFaBStatus.PENDING);
                order.setIdUser(LoginView.userLogin.getId());
//                order.setIdUser(System.currentTimeMillis() / 1000);
                addNewOrderItem( id);
                double total = getTotalForOrder(id);
                order.setTotal(total);
                String fmtOrderDetailHeader = String.format("ID ORDER: %-10s|ID STAFF: %-15s|Total: %-10s|Status: %-10s",id, order.getIdUser(), order.getTotal(), order.getStatus());
                System.out.println(fmtOrderDetailHeader);
                boolean checkPay = paymentConfirmation();
                if (checkPay){
                    order.setStatus(EFaBStatus.OK);
                }else {
                    order.setStatus(EFaBStatus.CANCELED);
                }
                orderService.addOrder(order);
            }
            catch (Exception e){
                System.out.println("Error value!Type again!");
                check = true;
            }
        }while (check);
    }
    public boolean paymentConfirmation(){
        System.out.println("You want to pay this order? Y/N");
        String check = scanner.nextLine();
        if (check.toUpperCase().equals("Y")){
            return true;
        }else if (!check.toUpperCase().equals("Y")&& !check.toUpperCase().equals("N")){
            System.out.println("Error Value! Type again");
            paymentConfirmation();
        }else if (check.toUpperCase().equals("N")){
            return false;
        }
        return true;
    }
    public double getTotalForOrder(long id){
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        double total = 0.0;
        for (int i = 0; i < allOrderItems.size(); i++) {
            if (allOrderItems.get(i).getIdOrder() == id) {
                total += allOrderItems.get(i).getPrice();
            }
        }
        return total;
    }
    public void addNewOrderItem (long orderID){
        boolean continueOrder = false;
        do {
            try {
                long ID = orderID;
                long IdFAB = checkIDFab();
                boolean check = checkStatus(IdFAB);
                if (!check){
                    checkIDFab();
                }
                FAB fab = fabService.findFABById(IdFAB);
                int amount = checkAmountFaB();
                OrderItem orderItem;
                if ((orderItem = orderItemService.getOrderItemsByIdOrderAndIdFaB(orderID,IdFAB)) != null) {
                    orderItem.setQuantity(amount + orderItem.getQuantity());
                    orderItem.setPrice(orderItem.getQuantity() * fab.getPrice());
                    String fmtOrderItemHeader = String.format("%-20s|%-10s|%-10s|%-10s", "Name Product","Size", "Quantity","Total");
                    System.out.println(fmtOrderItemHeader);
                    String fmtOrderItem = String.format("%-20s|%-10s|%-10s|%-10s", fab.getName(),fab.getESize(), orderItem.getQuantity(),orderItem.getPrice());
                    System.out.println(fmtOrderItem);
                    orderItemService.updateOrderItemById(orderItem.getId(), orderItem);
                }else if(orderItemService.getOrderItemsByIdOrderAndIdFaB(orderID,IdFAB) == null) {
                    long orderItemID = System.currentTimeMillis() / 1000;
                    double totalItem = amount*fab.getPrice();
                    OrderItem newOrderItem = new OrderItem(orderItemID,ID,IdFAB,amount,totalItem);
                    String fmtOrderItemHeader = String.format("%-20s|%-10s|%-10s|%-10s", "Name Product","Size", "Quantity","Total");
                    System.out.println(fmtOrderItemHeader);
                    String fmtOrderItem = String.format("%-20s|%-10s|%-10s|%-10s", fab.getName(),fab.getESize(), amount,totalItem);
                    System.out.println(fmtOrderItem);
                    orderItemService.addOrderItem(newOrderItem);
                }
            continueOrder = continuteOrder();
            }
        catch (Exception e){
                System.out.println("Error value22!Type again!");
                continueOrder = true;}
        }while(continueOrder);
    }

//    private OrderItem checkProductExistinOrder(Order order, long IdFAB) {
//        if (order.getOrderItems()!= null){
//            for (OrderItem orderItem : order.getOrderItems()) {
//                if (orderItem.getIdFAB() == IdFAB) {
//                    return orderItem;
//                }
//            }
//        }
//        return null;
//    }

    public boolean continuteOrder(){
        System.out.println("Do you want to continue ordering on this bill? Y/N");
        String check = scanner.nextLine();
        if (check.toUpperCase().equals("Y")){
            return true;
        }else if (!check.toUpperCase().equals("Y")&& !check.toUpperCase().equals("N")){
            System.out.println("Error Value! Type again");
            continuteOrder();
        }else if (check.toUpperCase().equals("N")){
            return false;
        }
        return true;
    }
    public long checkIDFab() {
        boolean checkidproduct;
        do {
            checkidproduct = false;
            System.out.println("Enter FaB ID you want to order:");
            long id = Long.parseLong(scanner.nextLine());
            int flag = 0;
            for (FAB fab : fabService.getAllFAB()) {
                if (fab.getId() == id) {
                    return id;
                }else {
                    flag = -1;
                }
            }
            if (flag == -1) {
                System.out.println("Error Value! Type again");
                checkidproduct = true;
            }
        } while (checkidproduct) ;
            return -1;
    }
    public boolean checkStatus(long id){
        FAB fab1 = fabService.findFABById(id);
        if (fab1.getEStatus().getValue().equals("STOCKING")) {
            return true;
        }else if (fab1.getEStatus().getValue().equals("SOLD_OUT")) {
            System.out.println("Sorry! this item is SOLD_OUT, pls choose again");
            return false;
        }
        return true;
    }
    public int checkAmountFaB(){
        boolean checkAmountFaB;
        do{
            try {
                checkAmountFaB = false;
                System.out.println("Enter amount you want to order:");
                int a = Integer.parseInt(scanner.nextLine());
                if ( a < 0 || a >50){
                    System.out.println("Amount order can't  upper than 50 and lower than 0");
                    checkAmountFaB = true;
                }else{
                    return a;
                }
            }
            catch (Exception e){
                System.out.println("Amount is not empty and must be a number");
                checkAmountFaB = true;
            }
        }while(checkAmountFaB);
        return -1;
    }
    public void editOrder(){
        boolean isRetry = false;
        do {
            try {
                showAllOrder();
                System.out.println("Enter ID Order you want to update");
                Long id = inputId();
                renderUpdateOrderMenu();
//                BannerApp.menuBanner("Update-UserView");
                int option = SupportApp.retryChoose(1, 5);
                Order newOrder = orderService.findOrderById(id);
                OrderItem orderItem;
                EFaBStatus eFaBStatus;
                switch (option) {
                    case 1:
                        System.out.println("Enter size: 2 - OK/ 3 - CANCELED");
                        int statusID = Integer.parseInt(scanner.nextLine());
                        eFaBStatus = EFaBStatus.toEFaBStatus(statusID);
                        newOrder.setStatus(eFaBStatus);
                        orderService.updateOrderById(id,newOrder);
                        System.out.println("Update-Success");
                        break;
                    case 2:
                        ShowDetailOrder(id);
                        System.out.println("Enter ID OrderItem you want to update");
                        long orderItemID = Long.parseLong(scanner.nextLine());
                        orderItem = orderItemService.findOrderItemById(orderItemID);
                        System.out.println("Enter new Amount");
                        int amount = checkAmountFaB();
                        FAB fab = fabService.findFABById(orderItem.getIdFAB());
                        double price = fab.getPrice();
                        double totalItem = amount * price;
                        orderItem.setQuantity(amount);
                        orderItem.setPrice(totalItem);
                        orderItemService.updateOrderItemById(orderItemID,orderItem );
                        double OrderTotal = getTotalForOrder(id);
                        newOrder.setTotal(OrderTotal);
                        orderService.updateOrderById(id, newOrder);
                        break;
                    case 3:
                        ShowDetailOrder(id);
                        System.out.println("Enter ID OrderItem you want to update");
                        long orderItemID2 = Long.parseLong(scanner.nextLine());
                        orderItem = orderItemService.findOrderItemById(orderItemID2);
                        System.out.println("Enter new FaB ID");
                        long fabId = Long.parseLong(scanner.nextLine());
                        FAB fab2 = fabService.findFABById((fabId));
                        double price2 = fab2.getPrice();
                        double totalItem2 = orderItem.getQuantity() * price2;
                        orderItem.setIdFAB(fabId);
                        orderItem.setPrice(totalItem2);
                        orderItemService.updateOrderItemById(orderItemID2,orderItem );
                        double OrderTotal2 = getTotalForOrder(id);
                        newOrder.setTotal(OrderTotal2);
                        orderService.updateOrderById(id, newOrder);
                        break;
                    case 4:
                        OrderLauncher();
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
    public void deleteOrder(){
        boolean checkRemoveAction;
        do {
            checkRemoveAction = false;
            showAllOrder();
            System.out.println("Enter ID Order you want to remove");
            Long idOrderDel = inputId();
            System.out.println("Are you sure you want to delete this Order?");
            String alert = scanner.nextLine();
            if (alert.toLowerCase().equals("y")) {
                orderService.deleteOrderById(idOrderDel);
            }
            if (alert.toLowerCase().equals("n")) {
                showAllOrder();
            }
            checkRemoveAction = InitApp.checkContinueRemoveUser();
        } while (checkRemoveAction);
        OrderLauncher();
    }
    public void showRevenue(){
        boolean check;
        do {
            System.out.println("You want to check revenue by??");
            System.out.println("1. By day");
            System.out.println("2. By month");
            System.out.println("r. Return Order menu");
//            BannerApp.menuBanner("profitMenu");
            check = false;
            List<Order> completeOrderList = new ArrayList<>();
            List<Order> listOrder = orderService.getAllOrders();
            for (Order order : listOrder){
                if ( order.getStatus().getValue().equals("OK")){
                    completeOrderList.add(order);
                }
            }
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter the Day you want to show:");
                    double total1 = 0;
                    String day = scanner.nextLine();
                    List<Order> orderList1 = new ArrayList<>();
                    for (Order order : completeOrderList) {
                        if (getDataByDate(DateUnit.convertDateAndHourToString(order.getDateOrder())).equals(day)) {
                            total1 += order.getTotal();
                            orderList1.add(order);
                        }
                    }
                    showAllOrder(orderList1);
                    System.out.print("Total:" + total1 + "\n");
                    break;
                case "2":
                    System.out.println("Enter the Month you want to show:");
                    double total2 = 0;
                    String month = scanner.nextLine();
                    List<Order> orderList2 = new ArrayList<>();
                    for (Order order : completeOrderList) {
                        if (getDataByMonth(DateUnit.convertDateAndHourToString(order.getDateOrder())).equals(month)) {
                            total2 += order.getTotal();
                            orderList2.add(order);
                        }
                    }
                    showAllOrder(orderList2);
                    System.out.print("Total:" + total2 + "\n");
                    break;
                case "r":
                    OrderLauncher();
                default:
                    System.out.println("Wrong value! Type again");
                    check = true;
            }
        }
        while (check);
    }
    public Long inputId() {
        Long id;
        boolean isRetry = false;
        do {
            id = Long.parseLong(scanner.nextLine());
            boolean exist = orderService.existById(id);
            if (!exist) {
                System.out.println("ID is not exist. Pls choose again!");
                inputId();
            }
        }while (isRetry) ;
        return id;
    }


}
