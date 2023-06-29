package model;

import repository.IModel;

public class OrderItem implements IModel<OrderItem> {
    private long id;
    private long idOrder;
    private long idBook;
    private long amount;


    public OrderItem(long id, long idOrder, long idBook, long amount) {
        this.id = id;
        this.idOrder = idOrder;
        this.idBook = idBook;
        this.amount = amount;

    }

    public OrderItem() {

    }
    public long getId(){
        return id;
    }

    public long getIdOrderItem() {
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

    @Override
    public OrderItem parseData(String line) {
    //1,1,1,5
        // idOrderItem, idOrder, idBook, amount
        String[] items = line.split(",");
        long idOrderItem = Long.parseLong(items[0]);
        long idOrder = Long.parseLong(items[1]);
        long idBook = Long.parseLong(items[2]);
        long amount = Long.parseLong(items[3]);
        OrderItem orderItem = new OrderItem(idOrderItem, idOrder, idBook, amount);
        return  orderItem;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s",
                this.id, this.idOrder, this.idBook, this.amount);
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
