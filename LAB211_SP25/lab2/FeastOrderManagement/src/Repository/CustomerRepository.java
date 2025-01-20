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
        System.out.println("Load customers successful.");
    }
    
    private void loadData() {
        CustomerDao dao = new CustomerDao();
        this.cusList = dao.getAll();
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
            if (name.equalsIgnoreCase(cus.getName())) {
                nameList.add(cus);
            }
        }
        
        return nameList;
    }
    
    public boolean isCodeExist(String code) {
        return getByCode(code) != null;
    }

    
}
