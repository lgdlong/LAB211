package repository;

import dao.CustomerDao;
import model.Customer;
import repository.itf.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author LGD
 */
public class CustomerRepository implements ICustomerRepository {
    List<Customer> cusList = null;
    
    public CustomerRepository() {
        loadData();
    }

    /**
     * Loads customer data from the database using {@link CustomerDao}.
     * If the retrieved list is {@code null}, initializes an empty {@code ArrayList}.
     */
    private void loadData() {
        this.cusList = Optional.ofNullable(new CustomerDao().getAll())
                            .orElseGet(ArrayList::new);
    }

    public List<Customer> getCusList() {
        return cusList;
    }
    
    @Override
    public boolean add(Customer item) {
        if (item == null) {
            return false;
        }
        return cusList.add(item);
    }

    @Override
    public boolean update(Customer updatedCustomer) {
        if (updatedCustomer == null) {
            System.out.println("Customer cannot be null!");
            return false;
        }

        List<Customer> customers = cusList;
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty!");
            return false;
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCode().equals(updatedCustomer.getCode())) {
                customers.set(i, updatedCustomer);
                return true;
            }
        }

        System.out.println("Customer not found!");
        return false;
    }

    public Customer getByCode(String code) {
        return cusList.stream()
                .filter(cus -> code.equalsIgnoreCase(cus.getCode()))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getListByName(String name) {
        List<Customer> nameList = new ArrayList<>();
        
        for (Customer cus : cusList) {
            if (cus.getName().contains(name)) {
                nameList.add(cus);
            }
        }
        
        return nameList;
    }
    
    public boolean isExist(String code) {
        return getByCode(code) != null;
    }
}
