package BookStoreManagerment.service;

import BookStoreManagerment.comparator.ComparatorByAmount;
import BookStoreManagerment.comparator.ComparatorById;
import BookStoreManagerment.comparator.ComparatorByName;
import BookStoreManagerment.comparator.ComparatorByPrice;
import BookStoreManagerment.model.Book;
import BookStoreManagerment.repository.file.BookRepository;
//import BookStoreManagerment.repository.inmemory.BookRepository;

import java.util.Comparator;
import java.util.List;

public class BookService {
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
    public List<Book> searchBooksByAuthor(String nameBook){

        return bookRepository.searchByAuthor(nameBook);
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

    public List<Book> sortBookById() {
        List<Book> books = getAllBooks();
        Comparator<Book> comparator = new ComparatorById();
        books.sort(comparator);
        return books;
    }

    public List<Book> sortBookByName() {
        List<Book> books = getAllBooks();
        Comparator<Book> comparator = new ComparatorByName();
        books.sort(comparator);
        return books;
    }

    public List<Book> sortBookByAmount() {
        List<Book> books = getAllBooks();
        Comparator<Book> comparator = new ComparatorByAmount();
        books.sort(comparator);
        return books;
    }

    public List<Book> sortBookByPrice() {
        List<Book> books = getAllBooks();
        Comparator<Book> comparator = new ComparatorByPrice();
        books.sort(comparator);
        return books;
    }
}
