package comparator;

import model.Book;

import java.util.Comparator;

public class ComparatorByPrice implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getPrice() - o2.getPrice() > 0) {
            return 1;
        }else{
            if (o1.getPrice() - o2.getPrice() == 0) {
                return 0;
            }
        }
        return -1;
    }
}