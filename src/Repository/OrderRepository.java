package Repository;

import Model.Order;
import Model.OrderItem;
import Service.FileContext;

public class OrderRepository extends FileContext<Order> {
    public OrderRepository() {
        filePath = "C:\\Phu Phan\\CinemaManager-FaB-\\data\\Order.txt";
        tClass = Order.class;
    }
}
