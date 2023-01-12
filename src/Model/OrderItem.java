package Model;

import Repository.IModel;
import Service.FABService;
import Unit.DateUnit;

public class OrderItem implements IModel<OrderItem> {
    private long id;
    private long idFAB;
    private long idOrder;
    private int quantity;
    private double price;
    public OrderItem(){

    }
    public OrderItem(long id, long idOrder, long idFAB, int quantity, double price) {
        this.id = id;
        this.idFAB = idFAB;
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFAB() {
        return idFAB;
    }

    public void setIdFAB(long idFAB) {
        this.idFAB = idFAB;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    public void update(OrderItem objNew) {
    this.idFAB = objNew.getIdFAB();
    this.idOrder = objNew.getIdOrder();
    this.quantity = objNew.getQuantity();
    this.price = objNew.getPrice();
    }

    @Override
    public OrderItem parseData(String line) {
        String [] items = line.split(",");
        long id = Long.parseLong(items[0]);
        long idFAB = Long.parseLong(items[2]);
//        long id, long idFAB, long idOrder, int quantity, double price
        long idOrder = Long.parseLong(items[1]);
        int quantity = Integer.parseInt(items[3]);
        double price = Double.parseDouble(items[4]);
        OrderItem orderItem = new OrderItem (id,idOrder,idFAB,quantity, price);
        return orderItem;
    }
    @Override
    public String toString(){
        return String.format("%s,%s,%s,%s,%s",
                this.getId(),
                this.getIdOrder(),
                this.getIdFAB(),
                this.getQuantity(),
                this.getPrice());
    }
}
