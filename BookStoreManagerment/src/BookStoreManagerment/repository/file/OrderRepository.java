package BookStoreManagerment.repository.file;

import BookStoreManagerment.model.Order;

import java.util.List;

public class OrderRepository extends FileContext<Order> {

    public OrderRepository(){
        filePath = "./BookStoreManagerment/src/BookStoreManagerment/data/order.csv";
        tClass = Order.class;
    }

}
