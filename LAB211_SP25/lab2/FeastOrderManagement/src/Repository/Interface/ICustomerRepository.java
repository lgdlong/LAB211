package Repository.Interface;

import Model.Customer;

import java.util.List;

public interface ICustomerRepository extends I_Repository<Customer> {
    List<Customer> getCusList();
    boolean update(Customer updatedCustomer);
    Customer getByCode(String code);
    List<Customer> getListByName(String name);
    boolean isExist(String code);
}