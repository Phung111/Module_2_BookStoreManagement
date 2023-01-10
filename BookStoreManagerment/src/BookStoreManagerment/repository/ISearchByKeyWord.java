package BookStoreManagerment.repository;

public interface ISearchByKeyWord<T> {
    boolean searchByKeyWord(String keyword, T obj);
}
