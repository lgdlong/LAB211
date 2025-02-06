package Repository;

import Dao.CustomerDao;
import Model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LGD
 */
public class CustomerRepository implements I_Repository<Customer>{
    List<Customer> cusList = null;
    
    public CustomerRepository() {
        loadData();
    }
    
    private void loadData() {
        CustomerDao dao = new CustomerDao();
        
        List<Customer> temp = dao.getAll();
        
        if (temp == null) {
            this.cusList = new ArrayList<>();
        } else {
            this.cusList = temp;
        }
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
    public void display() {
        if (cusList.isEmpty()) {
            System.out.println("There is no customer to display.");
        }
        cusList.stream()
               .forEach(cus -> System.out.println(cus));
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
