package comparator;

import model.Order;

import java.util.Comparator;

public class ByOrderPrice implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getTotalCost(), o2.getTotalCost());
    }
}