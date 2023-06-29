package view;

import model.*;
import service.BookService;
import service.OrderItemService;
import service.OrderService;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static zStringFormat.centerString.centerString;
import static zStringFormat.dateFormat.ddMMMyyyy;
import static zStringFormat.dateToLocalDate.datetoLocalDate;
import static zStringFormat.formatLongInput.inputDate;
import static zStringFormat.formatLongInput.inputLong;
import static zStringFormat.isContinue.isContinue;
import static zStringFormat.isContinue.isPay;

public class OrderView extends GenericView{
    private OrderService orderService;
    private BookService bookService;
    private OrderItemService orderItemService;
    private BookView bookView;

    public OrderView() {
        orderService = new OrderService();
        bookService = new BookService();
        bookView = new BookView();
        orderItemService = new OrderItemService();
    }

    @Override
    public void launcher() {
        if(account.getErole() == ERole.ADMIN) {
            boolean isContinue = false;
            do {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
                System.out.println("                                                                ┃      Menu Order For Admin     ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("                                                                ┃[1]Thêm order                  ┃");
                System.out.println("                                                                ┃[2]Xem danh sách order         ┃");
                System.out.println("                                                                ┃[3]Xem chi tiết order          ┃");
                System.out.println("                                                                ┃[4]Cập nhật trạng thái order   ┃");
                System.out.println("                                                                ┃[5]Sửa order                   ┃");
                System.out.println("                                                                ┃[6]Tìm kiếm order              ┃");
                System.out.println("                                                                ┃[7]Sắp xếp order               ┃");
                System.out.println("                                                                ┃[8]In hoá đơn                  ┃");
                System.out.println("                                                                ┃[9]Thống kê hoá đơn            ┃");
                System.out.println("                                                                ┃[10]Trả sách đã thuê           ┃");
                System.out.println("                                                                ┃[11]Book Coffee                ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("--Chọn chức năng--");
                String choiceStr = inputLong();
                if (choiceStr.equals(".")) {
                    break;
                }
                int actionMenuBook = Integer.parseInt(choiceStr);
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
                        updateStatusOrderView();
                        break;
                    case 5:
                        editOrderView();
                        break;
                    case 6:
                        menuSearchOrder();
                        break;
                    case 7:
                        sortOrderView();
                        break;
                    case 8:
                        printBill();
                        break;
                    case 9:
                        menuStatistic();
                        break;
                    case 10:
                        returnBook();
                        break;
                    case 11:
                        bookCoffe();
                        break;
                }
                isContinue = isContinue("Quản lý order", "Đến Menu Admin");
            } while (isContinue);
        } else {
            boolean isContinue = false;
            do {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
                System.out.println("                                                                ┃      Menu Order For User      ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("                                                                ┃[1]Thêm order                  ┃");
                System.out.println("                                                                ┃[2]Xem chi tiết order          ┃");
                System.out.println("                                                                ┃[3]Tìm kiếm order              ┃");
                System.out.println("                                                                ┃[4]Sắp xếp order               ┃");
                System.out.println("                                                                ┃[5]In hoá đơn                  ┃");
                System.out.println("                                                                ┃[6]Trả sách đã thuê            ┃");
                System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("--Chọn chức năng--");
                String choiceStr = inputLong();
                if (choiceStr.equals(".")) {
                    break;
                }
                int actionMenuBook = Integer.parseInt(choiceStr);
                switch (actionMenuBook) {
                    case 1:
                        createOrderView();
                        break;
                    case 2:
                        showDetailOrderView();
                        break;
                    case 3:
                        menuSearchOrder();
                        break;
                    case 4:
                        sortOrderView();
                        break;
                    case 5:
                        printBill();
                        break;
                    case 6:
                        returnBook();
                        break;
                }
                isContinue = isContinue("Quản lý order", "Đến Menu User");
            } while (isContinue);
        }
    }
    private void returnBook() {
        boolean isContinue = true;
        do {
            System.out.println("--Nhập idOrder--");
            String idOrderStr = inputLong();
            if(idOrderStr.equals(".")){
                return;
            }
            Long idOrder = Long.parseLong(idOrderStr);
            Order order = orderService.findOrderById(idOrder);
            if (order.geteStatusOrder() == EStatusOrder.BORROWING){
                Date dayOrder = order.getCreateAt();
                LocalDate LdayOrder = datetoLocalDate(dayOrder);
                LocalDate LdayTerm = LdayOrder.plusDays(30);
                Date dayTerm = java.sql.Date.valueOf(LdayTerm);
                Date dayReturn = new Date();
                LocalDate LdayReturn = datetoLocalDate(dayReturn);
                long days = DAYS.between(LdayTerm, LdayReturn);

                long feeBorrowPerDay = 10000;
                long feeDeposit = 500000;
                long feeBorrow = 50000;
                long feeTerm = 0;
                if(days > 0) {
                    feeTerm = days*feeBorrowPerDay;
                } else {
                    days = 0;
                }
                long totalBorrow = feeBorrow + feeTerm;
                long totalReturn = feeDeposit - totalBorrow;
                order.seteStatusOrder(EStatusOrder.BORROWPAID);
                order.setTotal(totalBorrow);
                orderService.updateOrderById(idOrder, order);

                List<OrderItem> orderItems = orderItemService.findOrderItemListByIdOrder(idOrder);
                for(OrderItem orderItem : orderItems){
                    Book book = bookService.findBookById(orderItem.getIdBook());
                    book.setAvaiable(book.getAvaiable() + 1);
                    bookService.updateBooktById(orderItem.getIdBook(), book);
                }

                showDetailOrderViewByIdWithBorrow(
                        idOrder,feeDeposit, feeBorrow, feeTerm, totalBorrow, totalReturn,feeBorrowPerDay,  dayOrder, dayTerm, dayReturn, days);
                System.out.println("--Thượng đế đã trả sách và thanh toán phí--");
                isContinue = true;
            } else {
                System.out.println("--idOrder không phải thuộc sách mượn, vui lòng chọn đúng idOrder--");
                isContinue = false;
            }
        } while (isContinue);
    }

    private void menuStatistic() {

        boolean isContinue = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃     Menu Thống kê hoá đơn     ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Từ ngày đến ngày            ┃");
            System.out.println("                                                                ┃[2]Trong Tháng                 ┃");
            System.out.println("                                                                ┃[3]Trong năm                   ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if(choiceStr.equals(".")){
                return;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    statisticFromDateToDate();
                    break;
                case 2:
                    statisticInMonth();
                    break;
                case 3:
                    statisticByYear();
                    break;
                default:
                    System.out.println("--Chọn sai chức năng, nhập lại--");
                    isContinue = false;
                    break;
            }
            isContinue = isContinue("Thống kê hoá đơn", "Quay lại Menu Order");
        } while (isContinue);
    }

    private void statisticByYear() {
        System.out.println("--Nhập năm--");
        String yearStr = inputLong();
        if (yearStr.equals(".")) {
            return;
        }
        long year = Long.parseLong(yearStr);

        List<Order> orders = orderService.getOrderInYear(year);
        showOrdersView(orders);
        long bigTotal = 0;
        for (Order order : orders) {
            bigTotal += order.getTotal();
        }
        String fmtOrderFooter = String.format("|%58s  %,10d đ|", "Big Total:    ", bigTotal);
        System.out.println(fmtOrderFooter);
        System.out.println("--------------------------------------------------------------------------");
    }

    private void statisticFromDateToDate() {
        List<Order> orders = new ArrayList<>();
        boolean isRetryDate = true;
        do {
            System.out.println("--Nhập ngày bắt đầu--");
            String startStr = inputDate();
            if(startStr.equals(".")){
                break;
            }
            Date startDate = DateUtils.parseDate(startStr);
            LocalDate start = datetoLocalDate(startDate);
            System.out.println("--Nhập ngày kết thúc--");
            String endStr = inputDate();
            if(endStr.equals(".")){
                break;
            }
            Date endDate = DateUtils.parseDate(endStr);
            LocalDate end = datetoLocalDate(endDate);
            if(start.isAfter(end)){
                System.out.println("--Ngày bắt đầu cần phải trước ngày kết thúc--");
                System.out.println("--Vui lòng kiểm tra lại--");
            } else {
                orders = orderService.getOrderByDateToDate(start, end);
                isRetryDate = false;
            }
        } while (isRetryDate);
        showOrdersView(orders);
        long bigTotal = 0;
        for (Order order : orders){
            bigTotal += order.getTotal();
        }
        String fmtOrderFooter = String.format("|%58s  %,10d đ|", "Big Total:    ", bigTotal);
        System.out.println(fmtOrderFooter);
        System.out.println("--------------------------------------------------------------------------");
    }

    private void statisticInMonth() {
        boolean isRetryMonth = true;
        long month = 0;
        do{
            System.out.println("--Nhập tháng cần thống kê--");
            String monthStr = inputLong();
            if (monthStr.equals(".")){
                break;
            }
            month = Long.parseLong(monthStr);
            if (month > 12){
                System.out.println("--Tháng nằm từ 1-12, xin mời nhập lại");
                isRetryMonth = true;
            } else {
                isRetryMonth = false;
            }
        } while (isRetryMonth);

        System.out.println("--Nhập năm--");
        String yearStr = inputLong();
        if (yearStr.equals(".")){
            return;
        }
        long year = Long.parseLong(yearStr);

        List<Order> orders = orderService.getOrderByMonth(month, year);
        showOrdersView(orders);
        long bigTotal = 0;
        for (Order order : orders){
            bigTotal += order.getTotal();
        }
        String fmtOrderFooter = String.format("|%58s  %,10d đ|", "Big Total:    ", bigTotal);
        System.out.println(fmtOrderFooter);
        System.out.println("--------------------------------------------------------------------------");
    }

    private void editOrderView() {
        showOrdersView();
        System.out.println("--Nhập Id bạn muốn sửa--");
        String idStr = inputLong();
        if(idStr.equals(".")){
            return;
        }
        long idOrder = Long.parseLong(idStr);
        Order order = orderService.findOrderById(idOrder);
        showOrdersViewById(idOrder);
        System.out.println("--Nhập tên khách hàng mới--");
        String nameCustomer = scanner.nextLine();
        System.out.println("--Nhập total mới--");
        String totalStr = inputLong();
        if(totalStr.equals(".")){
            return;
        }
        long total = Long.parseLong(totalStr);
        System.out.println("--Nhập date mới--");
        String dateStr = inputDate();
        if(dateStr.equals(".")){
            return;
        }
        Date date = DateUtils.parseDate(dateStr);
        System.out.println("--Chọn status order mới--");
        EStatusOrder eStatusOrder = EStatusOrder.NEW;
        System.out.println("--[1]NEW | [2]PAID | [3]CANCELED--");
        boolean isContinueChoseStatusOrder = false;
        do {
            String choiceStr = inputLong();
            if(choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    eStatusOrder = EStatusOrder.NEW;
                    break;
                case 2:
                    eStatusOrder = EStatusOrder.PAID;
                    break;
                case 3:
                    eStatusOrder = EStatusOrder.CANCELED;
                    break;
                default:
                    System.out.println("--Chọn sai chức năng, chọn lại--");
                    isContinueChoseStatusOrder = true;
                    break;
            }
        } while (isContinueChoseStatusOrder);

        order.setNameCustomer(nameCustomer);
        order.setTotal(total);
        order.setCreateAt(date);
        order.seteStatusOrder(eStatusOrder);
        orderService.updateOrderById(idOrder, order);
        System.out.println("--Done--");
    }

    private void sortOrderView() {
        boolean isContinue = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃     Menu Sắp xếp Order        ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Sắp xếp theo Id             ┃");
            System.out.println("                                                                ┃[2]Sắp xếp theo tên            ┃");
            System.out.println("                                                                ┃[3]Sắp xếp theo doanh thu      ┃");
            System.out.println("                                                                ┃[4]Sắp xếp theo ngày           ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            List<Order> orders = new ArrayList<>();
            switch (choice){
                case 1:
                    orders = orderService.sortOrderById();
                    break;
                case 2:
                    orders = orderService.sortOrderByName();
                    break;
                case 3:
                    orders = orderService.sortOrderByTotal();
                    break;
                case 4:
                    orders = orderService.sortOrderByDate();
                    break;
            }
            showOrdersView(orders);
            isContinue = isContinue("Sắp xếp sách", "Đến Menu quản lý sách");
        } while (isContinue);
    }

    private void printBill(){
        showOrdersView();
        boolean isContinue = true;
        do {
            showDetailOrderView();
            System.out.println("--Xác nhận in hoá đơn [1]yes / [2]no--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")){
                return;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    System.out.println("--Rẹt Rẹt-- *hoá đơn được in ra từ máy* --Rẹt Rẹt--");
                    break;
                case 2:
                    System.out.println("--Khách không thèm lấy hoá đơn--");
                    break;
            }
            isContinue = isContinue("In hoá đơn khác", "Menu Order");
        } while (isContinue);
    }

    private void menuSearchOrder() {
        boolean isContinue = true;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
            System.out.println("                                                                ┃           Menu Search         ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("                                                                ┃[1]Search Order                ┃");
            System.out.println("                                                                ┃[2]Search OrderItem            ┃");
            System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("--Chọn chức năng--");
            String choiceStr = inputLong();
            if (choiceStr.equals(".")) {
                break;
            }
            int actionMenuBook = Integer.parseInt(choiceStr);
            switch (actionMenuBook) {
                case 1:
                    searchOrderByKeyWord();
                    break;
                case 2:
                    searchOrderItemByKeyWord();
                    break;
            }
            isContinue = isContinue("Menu Search ", "Menu Order");
        } while (isContinue);

    }

    private void searchOrderByKeyWord(){
        boolean isContinue = true;
        do {
            System.out.println("--Nhập bất kỳ thứ gì để tìm kiếm--");
            String strSearch = scanner.nextLine();
            List<Order> orders = orderService.searchOrderByKeyWord(strSearch);
            if (orders.size() > 0){
                System.out.println("--Danh sách order tìm kiếm được theo dữ liệu bạn nhập--");
                showOrdersView(orders);
            } else {
                System.out.println("--Không tìm thấy kết quả phù hợp--");
            }
            isContinue = isContinue("Tìm kiếm order", "Quay lại Menu Search");
        } while (isContinue);
    }

    private void searchOrderItemByKeyWord(){
        boolean isContinue = true;
        do {
            System.out.println("--Nhập bất kỳ thứ gì để tìm kiếm--");
            String strSearch = scanner.nextLine();
            List<OrderItem> orderItems = orderItemService.searchOrderItemByKeyWord(strSearch);
            if (orderItems.size() > 0){
                System.out.println("--Danh sách order tìm kiếm được theo dữ liệu bạn nhập--");
                showOrderItemsView(orderItems);
            } else {
                System.out.println("--Không tìm thấy kết quả phù hợp--");

            }
            isContinue = isContinue("Tìm kiếm order", "Quay lại Menu Search");
        } while (isContinue);
    }

    private void updateStatusOrderView() {
        showOrdersView();
        System.out.println("--Nhập idOrder cần cập nhật status--");
        String idOrderStr = inputLong();
        if(idOrderStr.equals(".")){
            return;
        }
        long idOrder = Long.parseLong(idOrderStr);
        System.out.println("--Nhập trạng thái--");
        for (EStatusOrder eStatusOrder : EStatusOrder.values() ) {
            String fmtEStatus = String.format("[%s] %s", eStatusOrder.getId(),eStatusOrder.getValue());
            System.out.println(fmtEStatus);
        }
        Order order = orderService.findOrderById(idOrder);
        String eStatusStr = inputLong();
        if(eStatusStr.equals(".")){
            return;
        }
        long eStatus = Long.parseLong(eStatusStr);
        order.seteStatusOrder(EStatusOrder.toEStatusOrder(eStatus));
        orderService.updateOrderById(idOrder, order);
        showOrdersView();
        System.out.println("--Done--");
    }

    private void createOrderView() {
        //Xử lý order
        long idOrder = getMaxIdOrder() + 1;
        createOrder(idOrder);

        //Xác nhận BUY or BORROW với eStatusOrder
        EStatusOrder eStatusOrder = confirmBuyOrBorrow(idOrder);

        //Hiện book list
        bookView.showBookListAndPage();
//        bookView.showBookListAndPage1();

        //Xử lý orderItems
        long idOrderItem = getMaxIdOrderItem() + 1;
        createOrderItem(idOrder, idOrderItem, eStatusOrder);

        //Xử lý amount
        fixAmountAndAvaiableAfterOrder(idOrder, eStatusOrder);

        //Xử lý hoá đơn
        getBill(idOrder, eStatusOrder);
    }

    private EStatusOrder confirmBuyOrBorrow(long idOrder) {
        EStatusOrder eStatusOrder = EStatusOrder.NEW;
        Order order = orderService.findOrderById(idOrder);
        boolean isContinue = true;
        do {
            System.out.println("--Bạn muốn mua hay thuê sách?--");
            System.out.println("[1]Mua  -  [2]Thuê");
            String choiceStr = inputLong();
            if(choiceStr.equals(".")){
                break;
            }
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 1:
                    eStatusOrder = EStatusOrder.NEW;
                    isContinue = false;
                    break;
                case 2:
                    eStatusOrder = EStatusOrder.BORROW;
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                                                ◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
                    System.out.println("                                                                ┃       Bảng giá mượn sách      ┃");
                    System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                    System.out.println("                                                                ┃Chỉ được mượn 1 cuốn mỗi loại  ┃");
                    System.out.println("                                                                ┃Tiền cọc:          500.000đ    ┃");
                    System.out.println("                                                                ┃Phí thuê:           50.000đ    ┃");
                    System.out.println("                                                                ┃Thời hạn:           30 ngày    ┃");
                    System.out.println("                                                                ┃Phí trễ mỗi ngày:   10.000đ    ┃");
                    System.out.println("                                                                ◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    isContinue = false;
                    break;
                default:
                    System.out.println("--Nhập sai chức năng, xin mời nhập lại--");
                    break;
            }
        } while (isContinue);
        order.seteStatusOrder(eStatusOrder);
        orderService.updateOrderById(idOrder, order);
        return eStatusOrder;
    }

    private void getBill(long idOrder, EStatusOrder eStatusOrder){
        Order order = null;
        boolean confirmPay = isPay();
        if (confirmPay) {
            System.out.println("--Thượng đế đã thanh toán--");
            List<OrderItem> orderItemList = orderItemService.findOrderItemListByIdOrder(idOrder);
            for(OrderItem orderItem : orderItemList){
                Book book = bookService.findBookById(orderItem.getIdBook());
                //Xử lý avaiable của BUY or BORROW ở đây
                long afterAvaiable = book.getAvaiable() - orderItem.getAmount();
                long afterAmount = book.getAmount();
                if(eStatusOrder != EStatusOrder.BORROW){
                    afterAmount -= orderItem.getAmount();
                }
                book.setAvaiable(afterAvaiable);
                book.setAmount(afterAmount);
                bookService.updateBooktById(orderItem.getIdBook(), book);
            }
            order = orderService.findOrderById(idOrder);
            if(eStatusOrder == EStatusOrder.BORROW){
                order.seteStatusOrder(EStatusOrder.BORROWING);
            } else {
                order.seteStatusOrder(EStatusOrder.PAID);
            }
            orderService.updateOrderById(idOrder, order);
        } else {
            System.out.println("--Đã huỷ hoá đơn--");
            order = orderService.findOrderById(idOrder);
            order.seteStatusOrder(EStatusOrder.CANCELED);
            orderService.updateOrderById(idOrder, order);
        }
        showDetailOrderViewById(idOrder);
        showReturnDay(order);
        allowToRemoveSomeOrderItemsInCart(idOrder);
    }
    private void showReturnDay(Order order){
        if(order.geteStatusOrder() == EStatusOrder.BORROWING){
            Date dayOrder = order.getCreateAt();
            LocalDate LdayOrder = datetoLocalDate(dayOrder);
            LocalDate LdayTerm = LdayOrder.plusDays(30);
            Date dayTerm = java.sql.Date.valueOf(LdayTerm);
            String fmtOrderFooter = String.format("┃%113s  %10s┃", "Tiền cọc: 500.000 đ        Hạn trả:  ", ddMMMyyyy(dayTerm));
            System.out.println(fmtOrderFooter);
            System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        }
    }
    private void fixAmountAndAvaiableAfterOrder(long idOrder, EStatusOrder eStatusOrder){
        List<OrderItem> orderItemList = orderItemService.findOrderItemListByIdOrder(idOrder);
        boolean print = false;
        for(OrderItem orderItem : orderItemList){
            Book book = bookService.findBookById(orderItem.getIdBook());
            if(orderItem.getAmount() > book.getAvaiable()){
                orderItem.setAmount(book.getAvaiable());
                print = true;
            }
            if(eStatusOrder == EStatusOrder.BORROW){
                orderItem.setAmount(1);
                print = true;
            }
            orderItemService.updateOrderItemById(orderItem.getId(), orderItem);
        }
        if(print){
            System.out.println("--Sách bạn đặt vượt quá số lượng có sẵn hoặc chỉ được thuê 1 sách mỗi loại--");
            System.out.println("--Tự động điều chỉnh lại ở mức có sẵn--");
        }
        Order order = orderService.findOrderById(idOrder);
        order.setTotal(getBigTotal(idOrder));
        orderService.updateOrderById(idOrder, order);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("---------------Your bill---------------");
        showDetailOrderViewById(idOrder);
        showReturnDay(order);
        allowToRemoveSomeOrderItemsInCart(idOrder);
    }

    private void createOrderItem(long idOrder, long idOrderItem, EStatusOrder eStatusOrder) {
        boolean isContinueAddOrderItem = false;
        do {
            boolean isBookAvaiable = true;
            long idBookOrder = 0;
            do {
                idBookOrder = getIdBookWhileOrder();
                Book book = bookService.findBookById(idBookOrder);
                if(book.getAvaiable() == 0){
                    System.out.println("--Số lượng sách không có sẵn--");
                    System.out.println("--Mời bạn chọn id sách khác--");
                    isBookAvaiable = false;
                } else {
                    isBookAvaiable = true;
                }
            } while (isBookAvaiable == false);

            long amountOrder = getAmountWhileOrder(idBookOrder, eStatusOrder);

            List<OrderItem> orderItemList = orderItemService.findOrderItemListByIdOrder(idOrder);
            if (orderItemList.size() == 5 && eStatusOrder == EStatusOrder.BORROW){
                System.out.println("--Chỉ được thuê tối đa 5 cuốn sách--");
            } else {
                boolean isBookContainInOrder = false;
                for (OrderItem orderItem : orderItemList) {
                    if (orderItem.getIdBook() == idBookOrder) {
                        isBookContainInOrder = true;
                        amountOrder += orderItem.getAmount();
                        orderItem.setAmount(amountOrder);
                        orderItemService.updateOrderItemById(orderItem.getId(), orderItem);
                        break;
                    }
                }
                if(isBookContainInOrder == false){
                    idOrderItem = getMaxIdOrderItem() + 1;
                    OrderItem newOrderItem = new OrderItem(idOrderItem, idOrder, idBookOrder, amountOrder);
                    orderItemService.add(newOrderItem);
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------Your order---------------");
            showOrderItemViewById(idOrder);
            isContinueAddOrderItem = isContinue("Tiếp tục order", " (đến In hoá đơn và thanh toán)");
        } while (isContinueAddOrderItem);
    }

    private void allowToRemoveSomeOrderItemsInCart(long idOrder) {
        boolean isContinue = true;
        do {
            isContinue = isContinue("Bỏ sản phẩm nào đó trong giỏ","");
            if (isContinue){
                showOrderItemViewById(idOrder);
                System.out.println("--Nhập idOrderItem cần bỏ--");
                String idStr = inputLong();
                if(idStr.equals(".")){
                    return;
                }
                long idOrderItem = Long.parseLong(idStr);
                List<OrderItem> orderItems = orderItemService.findOrderItemListByIdOrder(idOrder);
                for(OrderItem orderItem : orderItems){
                    if(orderItem.getIdOrderItem() == idOrderItem){
                        orderItemService.deleteOrderItemById(idOrderItem);
                    }
                }
            }
            System.out.println("--Your Bill--");
            showDetailOrderViewById(idOrder
            );
        } while (isContinue);
    }

    private void createOrder(long idOrder) {
        System.out.println("--Nhập tên khách hàng--");
        String nameCustomer = scanner.nextLine();
        //Kiểm tra tên trùng
        if (nameCustomer.equals(".")) {
            return;
        }
        Date dateCreateOrder = new Date();
        EStatusOrder statusOrder = EStatusOrder.NEW;
        long totalOrder = 0;
        Order newOrder = new Order(idOrder, nameCustomer, totalOrder, dateCreateOrder, statusOrder);
        orderService.add(newOrder);
    }

    private long getAmountWhileOrder(long idBookOrder, EStatusOrder eStatusOrder) {
        long amountOrder = 0;
        if(eStatusOrder == EStatusOrder.BORROW) {
            amountOrder = 1;
        } else {
            boolean checkOverAvaiable = false;
            String amountStr = "";
            do {
                do {
                    System.out.println("--Nhập số lượng--");
                    amountStr = inputLong();
                    amountOrder = Long.parseLong(amountStr);
                    if(amountOrder <= 0){
                        System.out.println("--Số lượng ít nhất là 1 --");
                        System.out.println("--Mời nhập lại--");
                    }
                } while (amountOrder <= 0);
                checkOverAvaiable = checkOverAvaiable(amountOrder, idBookOrder);
                if (checkOverAvaiable == true) {
                    System.out.println("--Số lượng vượt quá book avaiable, mời bạn nhập lại--");
                }
            } while (checkOverAvaiable == true);
        }
        return amountOrder;
    }

    private long getIdBookWhileOrder() {
        long idBookOrder = 0;
        boolean checkIdBookContain = false;
        do {
            System.out.println("--Nhập Id Book bạn muốn order--");
            String idBookStr = inputLong();
            if (idBookStr.equals(".")) {
                break;
            }
            idBookOrder = Long.parseLong(idBookStr);
            checkIdBookContain = checkIdBookContain(idBookOrder);
            if (checkIdBookContain == false) {
                System.out.println("--Không tìm thấy Book theo Id, mời bạn nhập lại--");
            }
        } while (checkIdBookContain == false);
        return idBookOrder;
    }

    public long getBigTotal(long idOrder) {
        List<OrderItem> orderItems = orderItemService.findOrderItemListByIdOrder(idOrder);
        long bigToTal = 0;
        for (OrderItem orderItem : orderItems) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            bigToTal += book.getPrice() * orderItem.getAmount();
        }
        return bigToTal;
    }

    private void showOrdersView() {
        orderViewHeader();
        orderViewContent();
    }
    private void showOrdersView(List<Order> orders ) {
        orderViewHeader();
        orderViewContentForLoop(orders);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    private void showOrdersViewById(long idOrder) {
        orderViewHeader();
        Order order = orderService.findOrderById(idOrder);
        String fmtOrderDetailBody = String.format(
                "┃%-10s┃%-20s┃%-16s┃%-10s┃%,10d đ┃",
                centerString(10, String.valueOf(order.getId())),
                order.getNameCustomer(),
                centerString(16, ddMMMyyyy(order.getCreateAt())),
                centerString(10, String.valueOf(order.geteStatusOrder())),
                order.getTotal()
        );
        System.out.println(fmtOrderDetailBody);
    }
    private void orderViewHeader(){
        String fmtOrderHeader = String.format(
                "┃%-10s┃%-20s┃%-16s┃%-10s┃%-10s┃",
                centerString(10, "IDORDER"),
                centerString(20, "TEN KHACH HANG"),
                centerString(16, "NGAYDAT"),
                centerString(10, "STATUS"),
                centerString(12, "TONG TIEN")
        );
        System.out.println("◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
        System.out.println(fmtOrderHeader);
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }

    private void orderViewContent(){
        List<Order> orders = orderService.getAllOrders();
        orderViewContentForLoop(orders);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    private void orderViewContentForLoop(List<Order> orders){
        for (Order order : orders) {
            String fmtOrderContent = String.format(
                    "┃%-10s┃%-20s┃%-16s┃%-10s┃%,10d đ┃",
                    centerString(10, String.valueOf(order.getId())),
                    order.getNameCustomer(),
                    centerString(16, ddMMMyyyy(order.getCreateAt())),
                    centerString(10, String.valueOf(order.geteStatusOrder())),
                    order.getTotal()
            );
            System.out.println(fmtOrderContent);
        }
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }
    private void showOrderItemsView(List<OrderItem> orderItems ) {
        orderItemHeader();
        orderItemContentForLoop(orderItems);
    }
    private void showOrderItemViewById(long idOrder) {
        orderItemHeader();
        long bigToTal = 0;
        List<OrderItem> orderItems = orderItemService.findOrderItemListByIdOrder(idOrder);
        for (OrderItem orderItem : orderItems) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            String fmtOrderItem = String.format(
                    "┃%-15s┃%-10s┃%-10s┃%-50s┃%10s┃%,10d đ┃%,10d đ┃",
                    centerString(15, String.valueOf(orderItem.getIdOrderItem())),
                    centerString(10, String.valueOf(orderItem.getIdOrder())),
                    centerString(10, String.valueOf(book.getId())),
                    book.getName(),
                    orderItem.getAmount(),
                    book.getPrice(),
                    book.getPrice() * orderItem.getAmount()
            );
            System.out.println(fmtOrderItem);
            bigToTal = getBigTotal(idOrder);
        }
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        String fmtOrderFooter = String.format("┃%111s  %,10d đ┃", "Big Total:    ", bigToTal);
        System.out.println(fmtOrderFooter);
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }
    private void orderItemHeader(){
        String fmtOrderItemHeader = String.format(
                "┃%-15s┃%-10s┃%-10s┃%-50s┃%-10s┃%-10s┃%-10s┃",
                centerString(15, "Id Order Item"),
                centerString(10, "Id Order"),
                centerString(10, "Id Book"),
                centerString(50, "Name Book"),
                centerString(10, "Amount"),
                centerString(12, "Price"),
                centerString(12, "Total")
        );
        System.out.println("◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥");
        System.out.println(fmtOrderItemHeader);
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }
    private void orderItemContentForLoop(List<OrderItem> orderItems ){
        for (OrderItem orderItem : orderItems) {
            Book book = bookService.findBookById(orderItem.getIdBook());
            String fmtOrderItem = String.format(
                    "┃%-15s┃%-10s┃%-10s┃%-50s┃%10s┃%,10d đ┃%,10d đ┃",
                    centerString(15, String.valueOf(orderItem.getIdOrderItem())),
                    centerString(10, String.valueOf(orderItem.getIdOrder())),
                    centerString(10, String.valueOf(book.getId())),
                    book.getName(),
                    orderItem.getAmount(),
                    book.getPrice(),
                    book.getPrice() * orderItem.getAmount()
            );
            System.out.println(fmtOrderItem);
        }
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
    }

    private void showDetailOrderViewByIdWithBorrow(
            long idOrder,long feeDeposit,long feeBorrow,long feeTerm,long totalBorrow,long totalReturn,long feeBorrowPerDay,
            Date dayOrder, Date dayTerm,Date dayReturn,long days ) {
        showOrdersViewById(idOrder);
        orderItemHeader();
        List<OrderItem> orderItems = orderItemService.findOrderItemListByIdOrder(idOrder);
        orderItemContentForLoop(orderItems);
        System.out.println(String.format("┃%80s %10s %20s %,10d đ┃"     ,"Ngày mượn        : ",ddMMMyyyy(dayOrder)  ,"Tiền cọc   : ", feeDeposit));
        System.out.println(String.format("┃%80s %10s %20s %,10d đ┃"     ,"Hạn trả          : ",ddMMMyyyy(dayTerm)   ,"Phí mượn   : ", feeBorrow));
        System.out.println(String.format("┃%80s %10s %20s %,10d đ┃"     ,"Ngày trả         : ",ddMMMyyyy(dayReturn) ,"Phí trễ    : ", feeTerm));
        System.out.println(String.format("┃%80s %10s %20s %,10d đ┃"     ,"Số ngày trễ      : ",days                 ,"Tổng cộng  : ", totalBorrow));
        System.out.println(String.format("┃%80s %,8d đ %20s %,10d đ┃"   ,"Phí mỗi ngày trễ : ",feeBorrowPerDay      ,"Trả lại    : ", totalReturn));
        System.out.println("◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    private void showDetailOrderViewById(long idOrder) {
        showOrdersViewById(idOrder);
        showOrderItemViewById(idOrder);
    }

    private void showDetailOrderView() {
        System.out.println("--Nhập ID order--");
        long idOrder = Long.parseLong(scanner.nextLine());
        showOrdersViewById(idOrder);
        showOrderItemViewById(idOrder);
    }

    private long getMaxIdOrder() {
        long maxIdOrder = 0;
        for (int i = 0; i < orderService.getAllOrders().size(); i++) {
            if (orderService.getAllOrders().get(i).getId() > maxIdOrder) {
                maxIdOrder = orderService.getAllOrders().get(i).getId();
            }
        }
        return maxIdOrder;
    }

    private long getMaxIdOrderItem() {
        long maxIdOrder = 0;
        for (int i = 0; i < orderItemService.getAllOrderItem().size(); i++) {
            if (orderItemService.getAllOrderItem().get(i).getIdOrderItem() > maxIdOrder) {
                maxIdOrder = orderItemService.getAllOrderItem().get(i).getIdOrderItem();
            }
        }
        return maxIdOrder;
    }

    private boolean checkOverAvaiable(long amountOrder, long idBookOrder) {
        boolean checkOverAvaiable = false;
        Book book = bookService.findBookById(idBookOrder);
        if (amountOrder > book.getAvaiable()) {
            checkOverAvaiable = true;
        } else {
            checkOverAvaiable = false;
        }
        return checkOverAvaiable;
    }

    private boolean checkIdBookContain(long idBookOrder) {
        boolean checkIdBookContain = false;
        Book book = bookService.findBookById(idBookOrder);
        if (book == null) {
            checkIdBookContain = false;
        } else {
            checkIdBookContain = true;
        }
        return checkIdBookContain;
    }

    private void bookCoffe() {
        System.out.println("                       ╭──────────────────────────────────────────────────────────╮");
        System.out.println("                       │                    BOOK STORE COFFEE                     │");
        System.out.println("               ▂▃▅▇█▓▒░╰──────────────────────────────────────────────────────────╯░▒▓█▇▅▃▂");
        System.out.println("┌╌╌╌╌╌╌╌╌╌╌╌┐ ╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("┊     V     ┊ ║╭─────────╮            ╭──╮  ║  ║                                           ║");
        System.out.println("┊     I     ┊ ║╰─────────╯            ╰──╯  ║  ║                                           ║");
        System.out.println("┊     E     ┊ ║┌─────────┐            ╭──╮  ║  ║                  BAR                      ║");
        System.out.println("┊     W     ┊ ║│/////////│            ╰──╯  ║  ║                                           ║");
        System.out.println("┊           ┊ ║└─────────┘            ╭──╮  ║  ╚═══════════════════════════════════════════║");
        System.out.println("┊     K     ┊ ║╭─────────╮            ╰──╯  ╚══════════════════════════════════════════════║");
        System.out.println("┊     H     ┊ ║╰─────────╯            ╭──╮  ╭──╮  ╭──╮  ╭──╮  ╭──╮  ╭──╮  ╭──╮  ╭──╮  ╭──╮ ║");
        System.out.println("┊     Ô     ┊ ║-------------          ╰──╯  ╰──╯  ╰──╯  ╰──╯  ╰──╯  ╰──╯  ╰──╯  ╰──╯  ╰──╯ ║");
        System.out.println("┊     N     ┊ ║╭─────────╮                                                                 ║");
        System.out.println("┊     G     ┊ ║╰─────────╯                                                                 ║");
        System.out.println("┊           ┊ ║┌─────────┐                  ╭──╮  ┌────────┐  ╭──╮ ┊ ╭──╮  ┌────────┐  ╭──╮║");
        System.out.println("┊     Đ     ┊ ║│/////////│                  │  │  │        │  │  │ ┊ │  │  │        │  │  │║");
        System.out.println("┊     Ẹ     ┊ ║└─────────┘                  │  │  │////////│  │  │ ┊ │  │  │////////│  │  │║");
        System.out.println("┊     P     ┊ ║╭─────────╮                  │  │  │        │  │  │ ┊ │  │  │        │  │  │║");
        System.out.println("┊           ┊ ║╰─────────╯                  ╰──╯  └────────┘  ╰──╯ ┊ ╰──╯  └────────┘  ╰──╯║");
        System.out.println("└╌╌╌╌╌╌╌╌╌╌╌┘ ╚════════════╗           ╔═══════════════════════════════════════════════════╝");
        System.out.println("┌✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿┐             ┌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┐");
        System.out.println("┊           BỒN HOA      ┊             ┊                      VIEW ĐẸP                     ┊");
        System.out.println("└✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿✿┘             └╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┘");
        System.out.println("--Nhập tên--");
        String name = scanner.nextLine();
        System.out.println("--Số lượng người--");
        String numberStr = inputLong();
        long numberGuest = Long.parseLong(numberStr);
        System.out.println("--Chọn chỗ--");
    }
}
