package comparator;

import model.Book;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) > 0) {
            return 1;
        }else{
            if (o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) == 0) {
                return 0;
            }
        }
        return -1;
    }
}
