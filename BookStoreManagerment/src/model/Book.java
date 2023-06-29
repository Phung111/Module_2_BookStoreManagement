package model;

import java.util.Date;
import repository.IModel;
import repository.ISearch;
import utils.DateUtils;

import static zStringFormat.centerString.centerString;
import static zStringFormat.dateFormat.ddMMMyyyy;

public class Book implements IModel<Book>, ISearch<Book> {
    private long id;
    private String name;
    private String author;
    private long price;
    private long avaiable;
    private long amount;
    private Date dateAdd;

    public Book() {
    }

    public long getId() {
        return id;
    }

    @Override
    public void update(Book objNew) {
        this.name = objNew.getName();
        this.author = objNew.getAuthor();
        this.price = objNew.getPrice();
        this.avaiable = objNew.getAvaiable();
        this.amount = objNew.getAmount();
        this.dateAdd = objNew.getDateAdd();
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Book parseData(String line) {
        //5,5 cm/s,Shinkai Makoto,85000,1,1,20-12-2022 08:30:45
        String[] items = line.split(",");
        long id = Long.parseLong(items[0]);
        String name = items[1];
        String author = items[2];
        long price = Long.parseLong(items[3]);
        long avaiable = Long.parseLong(items[4]);
        long amount = Long.parseLong(items[5]);
        Date dateAdd = DateUtils.parseDate(items[6]);

        //id, name, author, price, avaiable, amount, dateAdd
        Book b = new Book(id, name, author, price, avaiable, amount, dateAdd);
        return b;
    }
    @Override
    public String toString(){
        //5,5 cm/s,Shinkai Makoto,85000,1,1,20-12-2022 08:30:
        String strDate = DateUtils.convertDateToString(this.dateAdd);
        return String.format("%s,%s,%s,%s,%s,%s,%s", this.id, this.name, this.author, this.price, this.avaiable, this.amount, strDate);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(long avaiable) {
        this.avaiable = avaiable;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public void updateBook(Book book){
        this.setId(book.getId());
        this.setName(book.getName());
        this.setAuthor(book.getAuthor());
        this.setPrice(book.getPrice());
        this.setAvaiable(book.getAvaiable());
        this.setAmount(book.getAmount());
        this.setDateAdd(book.getDateAdd());
    }

    public Book(long id, String name, String author, long price, long avaiable, long amount, Date dateAdd) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.avaiable = avaiable;
        this.amount = amount;
        this.dateAdd = dateAdd;
    }

    public String toViewer(){
        return String.format(
                "┃%-5s┃%-50s┃%-21s┃%,10d đ┃%10s┃%10s┃%-16s┃",
                centerString(5, String.valueOf(this.getId())),
                this.getName(),
                this.getAuthor(),
                this.getPrice(),
                this.getAvaiable(),
                this.getAmount(),
                centerString(16, ddMMMyyyy(this.getDateAdd()))
        );
    }
}
