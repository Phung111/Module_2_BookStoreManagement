package BookStoreManagerment.view;

import BookStoreManagerment.model.*;
import BookStoreManagerment.service.BookService;
import BookStoreManagerment.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static StringFormat.centerString.centerString;
import static StringFormat.dateFormat.ddMMMyyyy;

public class OrderView {
    private OrderService orderService;
    private BookService bookService;
    private Scanner scanner = new Scanner(System.in);

    public OrderView(){
        orderService = new OrderService();
        bookService = new BookService();
    }

    public void laucher(){
        boolean checkActionMenuOrder = false;
        do{
            System.out.println("Menu order");
            System.out.println("[1]Thêm order");
            System.out.println("[2]Xem danh sách order");
            System.out.println("[3]Xem chi tiết order");
            System.out.println("[4]Cập nhật trạng thái đơn hàng");

            int actionMenuBook = Integer.parseInt(scanner.nextLine());
            switch (actionMenuBook) {
                case 1:
                    createOrderView();
                    break;
                case 2:
                    showOrdersView();
                    break;
                case 3:
                    showDetailOrderView();
                    break;
                case 4:
//                    updateStatusOrderView();
                    break;
            }
            checkActionMenuOrder = checkActionContinue("Order","Hoàn toàn");
        }while (checkActionMenuOrder);
    }

    private void createOrderView() {
        //Xử lý order
        System.out.println("Nhập tên khách hàng");
        String nameCustomer = scanner.nextLine();
        long            idOrder         = getMaxIdOrder()+1;
        Date            dateCreateOrder = new Date();
        EStatusOrder    statusOrder     = EStatusOrder.NEW;
        long            totalOrder      = 0;
        Order newOrder = new Order(idOrder, nameCustomer, totalOrder, dateCreateOrder, statusOrder);
        orderService.add(newOrder);

        //Hiện book list
        showBooksOrderView();

        //Xử lý orderItems
        List<OrderItem> orderItems = addOrderItem(newOrder);
        for (OrderItem items : orderItems) {
            items.setIdOrder(newOrder.getId());
        }
        newOrder.setTotal(getBigTotal(idOrder));
        newOrder.setOrderItems(orderItems);
        System.out.println("---------------Your order---------------");
        showDetailOrderView(newOrder.getId());

    }

    public List<OrderItem> addOrderItem(Order order){
        boolean checkActionAddItem = true;
        List<OrderItem> orderItems = new ArrayList<>();
        order.setOrderItems(orderItems);
        long idOrderItem = 1; /////Sửa lại sau

            do{
                //cần Check id
                long idBookOrder;
                boolean checkIdBookContain = false;
                do{
                    System.out.println("Nhập Id Book bạn muốn order");
                    idBookOrder = Long.parseLong(scanner.nextLine());
                    checkIdBookContain = checkIdBookContain(idBookOrder);
                    if(checkIdBookContain == false){
                        System.out.println("--Không tìm thấy Book theo Id, mời bạn nhập lại--");
                    }
                } while(checkIdBookContain == false);
                //cần Check số lượng
                long amountOrder;
                boolean checkOverAvaiable = false;
                do{
                    System.out.println("Nhập số lượng");
                    amountOrder = Long.parseLong(scanner.nextLine());
                    checkOverAvaiable = checkOverAvaiable(amountOrder,idBookOrder);
                    if(checkOverAvaiable == true){
                        System.out.println("--Số lượng vượt quá book avaiable, mời bạn nhập lại--");
                    }
                } while (checkOverAvaiable == true);

//                OrderItem orderItem = new OrderItem(idOrderItem, idBookOrder ,amountOrder);

                addBookToOrderItems(idBookOrder, amountOrder,order, orderItems);

                System.out.println("Tiếp tục order[1] hoặc Đến in hoá đơn[2]");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1) {
                    checkActionAddItem = false;
                }            } while (checkActionAddItem == true);
        return orderItems;
    }

    private void addBookToOrderItems(long idBookOrder, long amountOrder, Order order, List<OrderItem> orderItems) {
        boolean flag = false;
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getIdBook() == idBookOrder) {
                //tăng số lượng
                orderItems.get(i).setAmount(orderItems.get(i).getAmount() + amountOrder);
                flag = true;
            }
        }
        if(flag == false){
            orderItems.add(new OrderItem(orderItems.size()+1, order.getId(), idBookOrder, amountOrder));
        }

    }

    public void showBooks(Book book){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s|%-50s|%-20s|%-12s|%-10s|%-10s|%-16s|\n",
                centerString(5, "#"),
                centerString(51, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount"),
                centerString(16, " Date Add")
        );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(book.toViewer());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void showBooksOrderView(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-5s|%-50s|%-20s|%-12s|%-10s|%-10s|%-16s|\n",
                centerString(5, "#"),
                centerString(51, "Name"),
                centerString(21, "Author"),
                centerString(12, " Price") ,
                centerString(10, "Avaiable"),
                centerString(10, "Amount" + ""),
                centerString(16, " Date Add")
        );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Book book : bookService.getAllBooks()){
            System.out.println(book.toViewer());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public long getBigTotal(long idOrder){
        Order order = orderService.findOrderById(idOrder);
        long bigToTal =0;
        for (OrderItem orderItem : order.getOrderItems()) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            bigToTal += book.getPrice()*orderItem.getAmount();
        }
        return bigToTal;
    }

    private void showDetailOrderView(long idOrder) {
        Order order = orderService.findOrderById(idOrder);
        System.out.println("--------------------------------------------------------------------------");
        String fmtOrderDetailHeader = String.format(
                "|%-10s|%-20s|%-16s|%-10s|%-10s|",
                centerString(10, "Id Order"),
                centerString(20, "Tên Khách Hàng"),
                centerString(16, "Ngày Đặt"),
                centerString(10, "Status"),
                centerString(12, "Tổng Tiền")
        );
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(fmtOrderDetailHeader);
        String fmtOrderDetailBody = String.format(
                "|%-10s|%-20s|%-16s|%-10s|%,10d đ|",
                centerString(10, String.valueOf(order.getId())),
                order.getNameCustomer(),
                centerString(16,ddMMMyyyy(order.getCreateAt())),
                centerString(10, String.valueOf(order.geteStatusOrder())),
                order.getTotal()
        );
        System.out.println(fmtOrderDetailBody);
        String fmtOrderItemHeader = String.format(
                "|%-15s|%-10s|%-50s|%-10s|%-10s|%-10s|",
                centerString(15, "Id Order Item"),
                centerString(10, "Id Book"),
                centerString(50, "Name Book"),
                centerString(10, "Amount"),
                centerString(12, "Price"),
                centerString(12, "Total")
        );
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(fmtOrderItemHeader);
        long bigToTal = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            String fmtOrderItem = String.format(
                    "|%-15s|%-10s|%-50s|%10s|%,10d đ|%,10d đ|",
                    centerString(15, String.valueOf(orderItem.getId())),
                    centerString(10,String.valueOf(book.getId())),
                    book.getName(),
                    orderItem.getAmount(),
                    book.getPrice(),
                    book.getPrice()*orderItem.getAmount()
            );
            System.out.println(fmtOrderItem);
            bigToTal += book.getPrice()*orderItem.getAmount();
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        String fmtOrderFooter = String.format("|%100s  %,10d đ|", "Big Total:    ", bigToTal );
        System.out.println(fmtOrderFooter);
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    //Show bill
    private void showDetailOrderView() {
        System.out.println("Nhập ID order");
        long idOrder = Long.parseLong(scanner.nextLine());
        Order order = orderService.findOrderById(idOrder);
        System.out.println("--------------------------------------------------------------------------");
        String fmtOrderDetailHeader = String.format(
                "|%-10s|%-20s|%-16s|%-10s|%-10s|",
                centerString(10, "Id Order"),
                centerString(20, "Tên Khách Hàng"),
                centerString(16, "Ngày Đặt"),
                centerString(10, "Status"),
                centerString(12, "Tổng Tiền")
        );
        System.out.println(fmtOrderDetailHeader);
        String fmtOrderDetailBody = String.format(
                "|%-10s|%-20s|%-16s|%-10s|%,10d đ|",
                centerString(10, String.valueOf(order.getId())),
                order.getNameCustomer(),
                centerString(16,ddMMMyyyy(order.getCreateAt())),
                centerString(10, String.valueOf(order.geteStatusOrder())),
                order.getTotal()
        );
        System.out.println(fmtOrderDetailBody);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        String fmtOrderItemHeader = String.format(
                "|%-15s|%-10s|%-50s|%-10s|%-10s|%-10s|",
                centerString(15, "Id Order Item"),
                centerString(10, "Id Book"),
                centerString(50, "Name Book"),
                centerString(10, "Amount"),
                centerString(12, "Price"),
                centerString(12, "Total")
        );
        System.out.println(fmtOrderItemHeader);
        long bigToTal = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            String fmtOrderItem = String.format("|%-15s|%-10s|%-50s|%10s|%,10d đ|%,10d đ|", centerString(15, String.valueOf(orderItem.getId())),centerString(10,String.valueOf(book.getId())), book.getName(),orderItem.getAmount(),book.getPrice(), book.getPrice()*orderItem.getAmount());
            System.out.println(fmtOrderItem);
            bigToTal += book.getPrice()*orderItem.getAmount();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        String fmtOrderFooter = String.format("|%100s %,10d đ|", "Big Total:    ", bigToTal );
        System.out.println(fmtOrderFooter);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    private long getMaxIdOrder(){
        long maxIdOrder = 0;
        for( int i = 0; i < orderService.getAllOrders().size(); i++){
            if(orderService.getAllOrders().get(i).getId() > maxIdOrder){
                maxIdOrder = orderService.getAllOrders().get(i).getId();
            }
        }
        return maxIdOrder;
    }

    private boolean checkOverAvaiable(long amountOrder, long idBookOrder){
        boolean checkOverAvaiable = false;
        Book book = bookService.findBookById(idBookOrder);
        if( amountOrder > book.getAvaiable()){
            checkOverAvaiable = true;
        } else {
            checkOverAvaiable = false;
        }
        return  checkOverAvaiable;
    }

    private boolean checkIdBookContain(long idBookOrder){
        boolean checkIdBookContain = false;
        Book book = bookService.findBookById(idBookOrder);
        if(book == null){
            checkIdBookContain = false;
        } else {
            checkIdBookContain = true;
        }
        return checkIdBookContain;
    }
    private void showOrdersView() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("--------------------------------------------------------------");
        String fmtOrderHeader = String.format(
                "|%-10s|%-20s|%-10s|%-16s|",
                centerString(10, "IDORDER"),
                centerString(20, "TEN KHACH HANG"),
                centerString(12, "TONG TIEN"),
                centerString(16, "NGAY DAT")
        );
        System.out.println(fmtOrderHeader);
        System.out.println("--------------------------------------------------------------");
        for (Order order : orders) {
            String fmtOrderContent = String.format(
                    "|%-10s|%-20s|%,10d đ|%-16s|",
                    centerString(10, String.valueOf(order.getId())),
                    order.getNameCustomer(),
                    order.getTotal(),
                    centerString(16, ddMMMyyyy(order.getCreateAt()))
            );
            System.out.println(fmtOrderContent);
        }
        System.out.println("--------------------------------------------------------------");
    }

    public boolean checkActionContinue(String string,String string2){
        boolean checkActionContinue = false;
        do {
            System.out.println("Bạn có muốn tiếp tục " + string + "?");
            System.out.println("[y] Đồng ý");
            System.out.println("[n] Thoát (" + string2 + ")");
            String actionContinue = scanner.nextLine();
            switch (actionContinue){
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    checkActionContinue = true;
            }
        } while (checkActionContinue = true);
        return false;
    }
}
