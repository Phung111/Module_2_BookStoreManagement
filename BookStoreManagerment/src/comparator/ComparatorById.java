package comparator;

import model.Book;

import java.util.Comparator;

public class ComparatorById implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getId() - o2.getId() > 0) {
            return 1;
        }else{
            if (o1.getId() - o2.getId() == 0) {
                return 0;
            }
        }
        return -1;
    }
}
