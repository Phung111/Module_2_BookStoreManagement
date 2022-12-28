package BookStoreManagerment.model;

import BookStoreManagerment.repository.IModel;

public class OrderItem implements IModel<OrderItem> {
    private long id;
    private long idOrder;
    private long idBook;
    private long amount;
    private String nameBook;

    public OrderItem(long id, long idOrder, long idBook, long amount, String nameBook) {
        this.id = id;
        this.idOrder = idOrder;
        this.idBook = idBook;
        this.amount = amount;
        this.nameBook = nameBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public OrderItem(long id, long idOrder, long idBook, long amount) {
        this.id = id;
        this.idOrder = idOrder;
        this.idBook = idBook;
        this.amount = amount;

    }

    public OrderItem(long id, long idBook, long amount) {
        this.id = id;
        this.idOrder = idOrder;
        this.idBook = idBook;
        this.amount = amount;

    }
    public OrderItem() {

    }

    public long getId() {
        return id;
    }

    @Override
    public void update(OrderItem objNew) {
        this.idBook = objNew.getIdBook();
        this.amount = objNew.getAmount();
        this.idOrder = objNew.getIdOrder();
    }

    @Override
    public String getName() {
        return null;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
