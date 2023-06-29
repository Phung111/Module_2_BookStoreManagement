package repository.file;

import model.Order;

import java.util.List;

public class OrderRepository extends FileContext<Order> {

    public OrderRepository(){
        filePath = "./BookStoreManagerment/src/data/order.csv";
        tClass = Order.class;
    }

}
