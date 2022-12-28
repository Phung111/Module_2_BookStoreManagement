package BookStoreManagerment.service;

import BookStoreManagerment.model.Book;
import BookStoreManagerment.repository.BookRepository;

import java.util.List;

public class BookService {
    private List<Book> books;
    private BookRepository bookRepository;
    public BookService() {
        bookRepository = new BookRepository();
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public List<Book> searchBooksByAmount(long amountBook){
        return bookRepository.searchByAmount(amountBook);
    }
    public List<Book> searchBooksByName(String nameBook){
        return bookRepository.searchByName(nameBook);
    }

    public void updateBooktById(long id, Book book){

        bookRepository.updateById(id, book);
    }

    public Book findBookById(long idBook){

        return bookRepository.findById(idBook);
    }

    public void deleteBookById(long idBook) {

        bookRepository.deleteById(idBook);
    }

    public void addBook(Book book) {
        bookRepository.add(book);
    }

}
