package Repository;

import Dao.OrderDao;
import Model.Order;
import java.util.List;

/**
 *
 * @author LGD
 */
public class OrderRepository implements I_Repository<Order>{
    
    List<Order> orderList = null;
    
    public OrderRepository() {
        loadData();
        System.out.println("Load orders successful.");
    }

    public List<Order> getOrderList() {
        return orderList;
    }
    
    private void loadData() {
        OrderDao dao = new OrderDao();
        this.orderList = dao.getAll();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean isExist(String id) {
        for (Order order : orderList) {
            if (id.equalsIgnoreCase(order.getId())) {
                return true;
            }
        }
        return false;
    }
}
