package Service;

import Model.FAB;
import Model.Order;
import Model.OrderItem;
import Model.User;
import Repository.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    public OrderItemService() {
        orderItemRepository = new OrderItemRepository();
    }
    public List<OrderItem> getOrderItemsByIdOrder(long idOrder) {
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < allOrderItems.size(); i++) {
            if (allOrderItems.get(i).getIdOrder() == idOrder) {
                orderItems.add(allOrderItems.get(i));
            }
        }
        return orderItems;
    }
    public OrderItem getOrderItemsByIdOrderAndIdFaB(long idOrder, long idFaB) {
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        OrderItem orderItems = null ;
        for (int i = 0; i < allOrderItems.size(); i++) {
            if (allOrderItems.get(i).getIdOrder() == idOrder && allOrderItems.get(i).getIdFAB() == idFaB) {
                orderItems = allOrderItems.get(i);
            }
        }
        return orderItems;
    }
    public OrderItem findOrderItemById (long id){
        return orderItemRepository.findById(id);
    }
    public void updateOrderItemById (long id, OrderItem orderItem){
        orderItemRepository.updateById(id, orderItem);
    }
    public void addOrderItem (OrderItem OI){
        orderItemRepository.add(OI);
    }

}
