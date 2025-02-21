package Repository;

import Dao.OrderDao;
import Model.Order;
import Repository.Interface.IOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author LGD
 */
public class OrderRepository implements IOrderRepository {
    
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
    public boolean update(Order oldOrder, Order newOrder) {
        if (newOrder == null || oldOrder == null || orderList == null) {
            System.out.println("Order cannot be null!");
            return false;
        }

        int index = orderList.indexOf(oldOrder);
        if (index == -1) {
            System.out.println("Old order not found!");
            return false;
        }

        orderList.set(index, newOrder);
        return true;
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