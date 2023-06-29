package service;

import model.Book;
import model.OrderItem;
import repository.ISearchByKeyWord;
import repository.file.OrderItemRepository;

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

    public void deleteOrderItemById(long idOrderItem){
        orderItemRepository.deleteById(idOrderItem);
    }
}
