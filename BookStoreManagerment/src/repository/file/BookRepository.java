package repository.file;

import model.Book;

public class BookRepository extends FileContext<Book> {

    public BookRepository() {
        filePath = "./BookStoreManagerment/src/data/book.csv";
        tClass = Book.class;
    }
}
