package Repository;

import Model.FAB;
import Model.OrderItem;
import Service.FileContext;

public class OrderItemRepository extends FileContext<OrderItem> {
    public OrderItemRepository() {
        filePath = "C:\\Phu Phan\\CinemaManager-FaB-\\data\\OrderItem.txt";
        tClass = OrderItem.class;
    }
}
