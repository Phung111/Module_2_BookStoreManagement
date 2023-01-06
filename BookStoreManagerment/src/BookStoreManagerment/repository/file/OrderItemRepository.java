package BookStoreManagerment.repository.file;

import BookStoreManagerment.model.OrderItem;

public class OrderItemRepository extends FileContext<OrderItem>{
    public OrderItemRepository() {
        filePath = "./BookStoreManagerment/src/BookStoreManagerment/data/orderItem.csv";
        tClass = OrderItem.class;
    }
}
