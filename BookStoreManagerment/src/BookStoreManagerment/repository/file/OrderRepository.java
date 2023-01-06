package BookStoreManagerment.repository.file;

import BookStoreManagerment.model.Order;

public class OrderRepository extends FileContext<Order> {

    public OrderRepository(){
        filePath = "./BookStoreManagerment/src/BookStoreManagerment/data/order.csv";
        tClass = Order.class;
    }
}
