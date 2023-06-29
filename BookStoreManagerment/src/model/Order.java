package model;

import repository.IModel;
import utils.DateUtils;

import java.util.Date;

public class Order implements IModel<Order>{

    private long idOrder;
    private String nameCustomer;
    private long total;
    private Date createAt;
    private EStatusOrder eStatusOrder;
    public long getId() {
        return idOrder;
    }

    @Override
    public void update(Order objNew) {
        this.nameCustomer = objNew.getNameCustomer();
        this.createAt = objNew.getCreateAt();
        this.total = objNew.getTotal();
        this.eStatusOrder = objNew.geteStatusOrder();
    }
    public Order() {

    }

    @Override
    public String getName() {
        return nameCustomer;
    }



    @Override
    public Order parseData(String line) {
        String[] items = line.split(",");
        long idOrder = Long.parseLong(items[0]);
        String nameCustomer = items[1];
        long total = Long.parseLong(items[2]);
        Date createAt = DateUtils.parseDate(items[3]);
        EStatusOrder eStatusOrder = EStatusOrder.getEStatusOrderByName(items[4]);

        Order order = new Order(idOrder, nameCustomer, total, createAt, eStatusOrder);
        return order;
    }
    @Override
    public String toString() {
        //1,Quang Dang,50000,8-3-1999,paid
        //idOrder,nameCustomer,total,date,eStatusOrder
        return String.format("%s,%s,%s,%s,%s",
                this.idOrder, this.nameCustomer, this.total, DateUtils.convertDateToString(this.createAt), this.eStatusOrder);
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


    public Order(long idOrder, String nameCustomer, long total, Date createAt) {
        this.idOrder = idOrder;
        this.nameCustomer = nameCustomer;
        this.total = total;
        this.createAt = createAt;
    }

    public Order(long idOrder, String nameCustomer, long total, Date createAt, EStatusOrder eStatusOrder) {
        this.idOrder = idOrder;
        this.nameCustomer = nameCustomer;
        this.total = total;
        this.createAt = createAt;
        this.eStatusOrder = eStatusOrder;
    }
}
