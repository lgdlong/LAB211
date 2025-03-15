package comparator;

import model.Customer;

import java.util.Comparator;

public class ByCusName implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c1.getLastName().compareTo(c2.getLastName());
    }
}