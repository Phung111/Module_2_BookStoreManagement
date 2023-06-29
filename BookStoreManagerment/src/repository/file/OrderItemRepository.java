package repository.file;

import model.OrderItem;

public class OrderItemRepository extends FileContext<OrderItem>{
    public OrderItemRepository() {
        filePath = "./BookStoreManagerment/src/data/orderItem.csv";
        tClass = OrderItem.class;
    }
}
