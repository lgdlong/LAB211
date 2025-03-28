package repository.itf;

import model.Order;

import java.util.List;

public interface IOrderRepository extends I_Repository<Order> {
    List<Order> getOrderList();
    boolean update(Order oldOrder, Order newOrder);
    Order getById(String id);
    boolean isExist(String id);
}