package repository.inmemory;

import model.Book;

import java.util.ArrayList;
import java.util.Date;

public class BookRepository extends MemoryContext<Book> {

    @Override
    public void init() {
        list = new ArrayList<>();
        //long id, String name, String author, double price, int avaiable, int amount, Date dateAdd
        list.add(new Book(1, "Đao kiếm thần vực"                                   ,"Kawahara Reki"        , 100000, 5 , 10, new Date()));
        list.add(new Book(2, "My Youth Romantic Comedy Is Wrong As I Expected"     ,"WatarU WatarI"        , 175000, 0 , 10, new Date()));
        list.add(new Book(3, "Overlord"                                            ,"Kugane Maruyama"      , 110000, 1 , 10, new Date()));
        list.add(new Book(4, "Thất Nghiệp Chuyển Sinh"                             ,"Rifujin Na Magonote"  , 200000, 10, 10, new Date()));
        list.add(new Book(5, "5 cm/s"                                              ,"Shinkai Makoto"       , 85000 , 1 , 1 , new Date()));
        // Your Name 75000
        // Khu Vườn Ngôn Từ 66000
        // Weathering With You 195000
    }
}
