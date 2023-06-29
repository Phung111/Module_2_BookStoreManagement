package service;

import model.EStatusOrder;
import model.Order;
import repository.ISearchByKeyWord;
import repository.file.OrderRepository;
import utils.DateUtils;
//import repository.inmemory.OrderRepository;

import java.time.LocalDate;
import java.util.*;

import static zStringFormat.dateToLocalDate.datetoLocalDate;

public class OrderService {
    private OrderRepository orderRepository;
    public OrderService() {
        orderRepository = new OrderRepository();
    }

    public List<Order> getAllOrders(){
        List<Order> allOrders = orderRepository.getAll();
        return allOrders;
    }

    public void updateOrderById(long idOrder, Order order){
        orderRepository.updateById(idOrder, order);
    }

    public Order findOrderById(long idOrder){
        List<Order> allOrder = orderRepository.getAll();
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < allOrder.size(); i++) {
            if (allOrder.get(i).getId() == idOrder) {
                orders.add(allOrder.get(i));
            }
        }
        Order order = orderRepository.findById(idOrder);
        return order;
    }
    public void add(Order newOrder) {
        orderRepository.add(newOrder);
    }

    public List<Order> searchOrderByKeyWord(String keyword) {
        ISearchByKeyWord<Order> iSearchByKeyWord = new ISearchByKeyWord<Order>() {

            @Override
            public boolean searchByKeyWord(String keyword, Order obj) {
                String strKeyword = keyword.toUpperCase();
                String strName = obj.getName();
                String strDate = DateUtils.convertDateToString(obj.getCreateAt()).toUpperCase();
                String strTotal = String.valueOf(obj.getTotal()).toUpperCase();
                String strStatus = String.valueOf(obj.geteStatusOrder()).toUpperCase();
                boolean checkSearch = strName.contains(strKeyword)
                        || strDate.contains(strKeyword)
                        || strTotal.contains(strKeyword)
                        || strStatus.contains(strKeyword)
                        ;
                if (checkSearch) {
                    return true;
                }
                return false;
            }
        };
        return orderRepository.searchByKeyWord(keyword, iSearchByKeyWord);
    }

    public List<Order> sortOrderById() {
        List<Order> orders = getAllOrders();
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if(o1.getId() > o2.getId()){
                    return 1;
                } else if (o1.getId() == o2.getId()){
                    return 0;
                } else
                    return -1;
            }
        });
        return orders;
    }

    public List<Order> sortOrderByName() {
        List<Order> orders = getAllOrders();
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) > 0) {
                    return 1;
                }else if (o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase()) == 0){
                   return 0;
                } else {
                    return -1;
                }
            }
        });
        return orders;
    }

    public List<Order> sortOrderByTotal() {
        List<Order> orders = getAllOrders();
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if(o1.getTotal() > o2.getTotal()){
                    return 1;
                } else if (o1.getTotal() == o2.getTotal()){
                    return 0;
                } else
                    return -1;
            }
        });
        return orders;
    }

    public List<Order> sortOrderByDate() {
        List<Order> orders = getAllOrders();
        Collections.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if(o1.getCreateAt().after(o2.getCreateAt())){
                    return 1;
                } else if (o1.getCreateAt().before(o2.getCreateAt())){
                    return -1;
                } else
                    return 0;
            }
        });
        return orders;
    }

    public List<Order> getOrderByMonth(long month, long year){
        String dateStr = "01-"+month+"-"+year;
        Date date = DateUtils.parseDate(dateStr);
        LocalDate localDate = datetoLocalDate(date);
        LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
        LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<Order> result = getOrderByDateToDate(firstDayOfMonth,lastDayOfMonth);
        return result;
    }

    public List<Order> getOrderByDateToDate(LocalDate start, LocalDate end){
        List<Order> orders = getAllOrders();
        List<Order> result = new ArrayList<>();
        LocalDate dayStart = start.minusDays(1);
        LocalDate dayEnd = end.plusDays(1);
        for(Order order: orders){
            LocalDate dayOrder = datetoLocalDate(order.getCreateAt());
            if(dayOrder.isAfter(dayStart) && dayOrder.isBefore(dayEnd) && order.geteStatusOrder() == EStatusOrder.PAID || order.geteStatusOrder() == EStatusOrder.BORROWPAID){
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> getOrderInYear(long year) {
        String dateStr = "01-01-"+year;
        Date date = DateUtils.parseDate(dateStr);
        LocalDate localDate = datetoLocalDate(date);
        LocalDate firstDayOfYear = localDate.withDayOfYear(1);
        LocalDate lastDayOfYear = localDate.withDayOfYear(localDate.lengthOfYear());

        List<Order> result = getOrderByDateToDate(firstDayOfYear,lastDayOfYear);
        return result;
    }
}
