package BookStoreManagerment.service;

import BookStoreManagerment.model.OrderItem;
import BookStoreManagerment.repository.file.OrderItemRepository;
import BookStoreManagerment.repository.file.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    public OrderItemService(){
        orderItemRepository = new OrderItemRepository();
    }
    public void add(OrderItem newOrderItem) {
        orderItemRepository.add(newOrderItem);
    }

    public List<OrderItem> getAllOrderItem(){
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        return allOrderItems;
    }

    public List<OrderItem> getOrderItemsByIdOrder(long idOrder){
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < allOrderItems.size(); i++) {
            if (allOrderItems.get(i).getIdOrder() == idOrder) {
                orderItems.add(allOrderItems.get(i));
            }
        }
        return orderItems;
    }

    public void updateOrderItemById(long id, OrderItem orderItem){
        orderItemRepository.updateById(id, orderItem);
    }
    public OrderItem findOrderItemById(long idOrder){
        return orderItemRepository.findById(idOrder);
    }

}
