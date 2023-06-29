package service;

import comparator.ComparatorByAmount;
import comparator.ComparatorById;
import comparator.ComparatorByName;
import comparator.ComparatorByPrice;
import model.Book;
import repository.file.BookRepository;
//import repository.inmemory.BookRepository;

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
        ComparatorById comparator = new ComparatorById();
        books.sort((Comparator<? super Book>) comparator);
        return books;
    }

    public List<Book> sortBookByName() {
        List<Book> books = getAllBooks();
        ComparatorByName comparator = new ComparatorByName();
        books.sort((Comparator<? super Book>) comparator);
        return books;
    }

    public List<Book> sortBookByAmount() {
        List<Book> books = getAllBooks();
        ComparatorByAmount comparator = new ComparatorByAmount();
        books.sort((Comparator<? super Book>) comparator);
        return books;
    }

    public List<Book> sortBookByPrice() {
        List<Book> books = getAllBooks();
        ComparatorByPrice comparator = new ComparatorByPrice();
        books.sort((Comparator<? super Book>) comparator);
        return books;
    }
}
