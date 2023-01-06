package BookStoreManagerment.repository.file;

import BookStoreManagerment.model.Book;

public class BookRepository extends FileContext<Book> {

    public BookRepository() {
        filePath = "./BookStoreManagerment/src/BookStoreManagerment/data/book.csv";
        tClass = Book.class;
    }
}
