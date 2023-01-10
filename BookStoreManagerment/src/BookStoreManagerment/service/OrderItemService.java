package BookStoreManagerment.service;

import BookStoreManagerment.model.Book;
import BookStoreManagerment.model.Order;
import BookStoreManagerment.model.OrderItem;
import BookStoreManagerment.repository.ISearch;
import BookStoreManagerment.repository.ISearchByKeyWord;
import BookStoreManagerment.repository.file.OrderItemRepository;
import BookStoreManagerment.repository.file.OrderRepository;
import BookStoreManagerment.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    private BookService bookService;
    public OrderItemService(){
        orderItemRepository = new OrderItemRepository();
        bookService = new BookService();
    }
    public void add(OrderItem newOrderItem) {
        orderItemRepository.add(newOrderItem);
    }

    public List<OrderItem> getAllOrderItem(){
        List<OrderItem> allOrderItems = orderItemRepository.getAll();
        return allOrderItems;
    }
    public OrderItem getOrderItemsByIdOrder(long idOrder){
        return orderItemRepository.findById(idOrder);
    }
//    public List<OrderItem> getOrderItemsByIdOrder(long idOrder){
//        List<OrderItem> allOrderItems = orderItemRepository.getAll();
//        List<OrderItem> orderItems = new ArrayList<>();
//        for (int i = 0; i < allOrderItems.size(); i++) {
//            if (allOrderItems.get(i).getIdOrder() == idOrder) {
//                orderItems.add(allOrderItems.get(i));
//            }
//        }
//        return orderItems;
//    }

    public void updateOrderItemById(long id, OrderItem orderItem){
        orderItemRepository.updateById(id, orderItem);
    }
    public List<OrderItem> findOrderItemListByIdOrder(long idOrder){
        List<OrderItem> lists = getAllOrderItem();
        List<OrderItem> result = new ArrayList<>();
        for(OrderItem list : lists){
            if(list.getIdOrder() == idOrder){
                result.add(list);
            }
        }
        return result;
//        return orderItemRepository.findListById(idOrder);
    }

    public List<OrderItem> searchOrderItemByKeyWord(String keyword) {
        ISearchByKeyWord<OrderItem> iSearchByKeyWord = new ISearchByKeyWord<OrderItem>(){


            @Override
            public boolean searchByKeyWord(String keyword, OrderItem obj) {
                String strKeyword = keyword.toUpperCase();
                Book book = bookService.findBookById(obj.getIdBook());
                String strIdOrderItem = String.valueOf(obj.getIdOrderItem());
                String strIdOrder = String.valueOf(obj.getIdOrder());
                String strIdBook = String.valueOf(obj.getIdBook());
                String strNameBook = book.getName().toUpperCase();
                String strAmount = String.valueOf(obj.getAmount());
                String strPrice = String.valueOf(book.getPrice());
                boolean checkSearch = strIdOrderItem.contains(strKeyword)
                        || strIdOrder.contains(strKeyword)
                        || strIdBook.contains(strKeyword)
                        || strNameBook.contains(strKeyword)
                        || strAmount.contains(strKeyword)
                        || strPrice.contains(strKeyword)
                        ;
                if (checkSearch) {
                    return true;
                }
                return false;
            }
        };
        return orderItemRepository.searchByKeyWord(keyword, iSearchByKeyWord);
    }
}
