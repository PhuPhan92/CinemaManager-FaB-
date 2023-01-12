package Model;

import Repository.IModel;
import Unit.DateUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements IModel<Order> {
    private String nameUser;
    private long idUser;
    private long id;
    private Date dateOrder;
    private double total;
    private List<OrderItem> orderItems;
    private EFaBStatus status;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public EFaBStatus getStatus() {
        return status;
    }

    public void setStatus(EFaBStatus status) {
        this.status = status;
    }
    public Order(){

    }

    public Order( long id,long idUser, double total, Date dateOrder, List<OrderItem> orderItems) {
        this.idUser = idUser;
        this.id = id;
        this.dateOrder = dateOrder;
        this.total = total;
        this.orderItems = orderItems;

    }
    public Order( long id,long idUser, double total, Date dateOrder, EFaBStatus status) {
        this.idUser = idUser;
        this.id = id;
        this.dateOrder = dateOrder;
        this.total = total;
        this.status = status;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public EPosition getEPosition() {
        return null;
    }

    @Override
    public void update(Order objNew) {
        this.idUser = objNew.getIdUser();;
        this.total = objNew.getTotal();
        this.dateOrder = objNew.getDateOrder();
        this.orderItems = objNew.getOrderItems();
        this.status = objNew.getStatus();

    }
//    ong id,long idUser, double total, Date dateOrder, List<OrderItem> orderItems
    @Override
    public Order parseData(String line) {
        String[] items = line.split(",");
        Order order = new Order();
        long idOrder = Long.parseLong(items[0]);
        long idUser = Long.parseLong(items[1]);
        double total = Double.parseDouble(items[2]);
        Date creatAt = DateUnit.convertStringToDateAndHour(items[3]);
        EFaBStatus faBStatus = EFaBStatus.getEGenderByName(items[4].trim());
        order.setId(idOrder);
        order.setIdUser(idUser);
        order.setTotal(total);
        order.setDateOrder(creatAt);
        order.setStatus(faBStatus);
        return order;
    }
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%s",
                this.getId(),
                this.getIdUser(),
                this.getTotal(),
                DateUnit.convertDateAndHourToString(this.getDateOrder()),
                String.valueOf(this.getStatus()));
    }

}
