package Repository;

import Dao.OrderDao;
import Model.Customer;
import Model.Order;
import java.util.ArrayList;
import java.util.List;

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
        OrderDao dao = new OrderDao();
        
        List<Order> temp = dao.getAll();
        
        if (temp == null) {
            this.orderList = new ArrayList<>();
        } else {
            this.orderList = temp;
        }
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
        orderList.stream()
               .forEach(order -> System.out.println(order));
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