package repository.inmemory;

import model.Order;
import model.OrderItem;
import model.EStatusOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepository extends MemoryContext<Order>{
    @Override
    public void init() {
        list = new ArrayList<>();

//        Order order1 = new Order(1,"Quang Dang", 500000, new Date(), EStatusOrder.NEW);
//        List<OrderItem> orderItems1 = new ArrayList<>();
//        orderItems1.add(new OrderItem(1, 1 , 1, 5));
//        order1.setOrderItems(orderItems1);
//
//        Order order2 = new Order(2, "Thiên Phụng", 1550000, new Date(), EStatusOrder.NEW);
//        List<OrderItem> orderItems2 = new ArrayList<>();
//        orderItems2.add(new OrderItem(1, 2 , 4, 5));
//        orderItems2.add(new OrderItem(2, 2 , 3, 5));
//        order2.setOrderItems(orderItems2);
//        list.add(order1);
//        list.add(order2);
    }
}
