package comparator;

import model.Book;

import java.util.Comparator;

public class ComparatorByAmount implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getAmount() - o2.getAmount() > 0) {
            return 1;
        }else{
            if (o1.getAmount() - o2.getAmount() == 0) {
                return 0;
            }
        }
        return -1;
    }
}
