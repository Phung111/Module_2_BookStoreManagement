package BookStoreManagerment.repository;

import BookStoreManagerment.model.Book;

public interface IModel<T> {
    long getId();
    void update(T objNew);

    String getName();
    T parseData(String line);
}
