package BookStoreManagerment.service;

import BookStoreManagerment.model.Order;
import BookStoreManagerment.repository.OrderRepository;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;
    public OrderService() {
        orderRepository = new OrderRepository();
    }

    public List<Order> getAllOrders(){
        return orderRepository.getAll();
    }

    public Order findOrderById(long idOrder){
        return orderRepository.findById(idOrder);
    }
    public void add(Order newOrder) {
        orderRepository.add(newOrder);
    }
}
