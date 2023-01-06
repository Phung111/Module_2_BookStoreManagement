import BookStoreManagerment.model.Account;
import BookStoreManagerment.view.book.BookView;
import BookStoreManagerment.service.AccountService;
import BookStoreManagerment.view.order.OrderView;


public class Main {
    public static void main(String[] args) {
//        BookView bookStoreList = new BookView();
//        bookStoreList.laucher();

        OrderView orderView = new OrderView();
        orderView.laucher();
    }
}