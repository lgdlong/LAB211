package Repository;

import Dao.OrderDao;
import Model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author LGD
 */
public class OrderRepository implements I_Repository<Order>{
    
    List<Order> orderList = null;
    
    public OrderRepository() {
        loadData();
    }

    public List<Order> getOrderList() {
        return orderList;
    }
    
    private void loadData() {
        this.orderList = Optional.ofNullable(new OrderDao().getAll())
                                .orElseGet(ArrayList::new);
    }
    
    @Override
    public boolean add(Order item) {
        if (item == null) {
            return false;
        }
        return orderList.add(item);
    }

    @Override
    public void display() {
        if (orderList.isEmpty()) {
            System.out.println("There is no order to display.");
        }
        orderList.forEach(System.out::println);
    }
    
    public Order getById(String id) {
        return orderList.stream()
                .filter(order -> id.equalsIgnoreCase(order.getId()))
                .findFirst()
                .orElse(null);
    }
    
    public boolean isExist(String id) {
        return getById(id) != null;
    }
}