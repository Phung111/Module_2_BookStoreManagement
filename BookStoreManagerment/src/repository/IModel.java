package repository;

public interface IModel<T> {
    long getId();
    void update(T objNew);

    String getName();
    T parseData(String line);
}
