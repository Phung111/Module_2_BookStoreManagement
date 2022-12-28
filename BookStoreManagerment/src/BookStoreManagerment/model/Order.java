package BookStoreManagerment.model;

import BookStoreManagerment.repository.IModel;

import java.util.Date;
import java.util.List;

public class Order implements IModel<Order> {

    private long idOrder;
    private String nameCustomer;
    private long total;
    private Date createAt;
    private EStatusOrder eStatusOrder;
    private List<OrderItem> orderItems;
    public long getId() {
        return idOrder;
    }

    @Override
    public void update(Order objNew) {
        this.nameCustomer = objNew.getNameCustomer();
        this.createAt = objNew.getCreateAt();
        this.total = objNew.getTotal();
        this.orderItems = objNew.getOrderItems();
        this.eStatusOrder = objNew.geteStatusOrder();
    }

    @Override
    public String getName() {
        return null;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public EStatusOrder geteStatusOrder() {
        return eStatusOrder;
    }

    public void seteStatusOrder(EStatusOrder eStatusOrder) {
        this.eStatusOrder = eStatusOrder;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order(long idOrder, String nameCustomer, long total, Date createAt, List<OrderItem> orderItems) {
        this.idOrder = idOrder;
        this.nameCustomer = nameCustomer;
        this.total = total;
        this.createAt = createAt;
        this.orderItems = orderItems;
    }

    public Order(long idOrder, String nameCustomer, long total, Date createAt, EStatusOrder eStatusOrder) {
        this.idOrder = idOrder;
        this.nameCustomer = nameCustomer;
        this.total = total;
        this.createAt = createAt;
        this.eStatusOrder = eStatusOrder;
    }
}
