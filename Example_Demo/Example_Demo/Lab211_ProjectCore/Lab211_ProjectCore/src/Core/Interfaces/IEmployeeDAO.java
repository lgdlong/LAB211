package Core.Interfaces;

import Core.Entities.Employee;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author SwordLake
 */
public interface IEmployeeDAO{   
    List<Employee>  getEmployees() throws Exception ;  
    Employee  getEmployeeById(String id) throws Exception ; 
    void addEmployee(Employee employee) throws Exception ;   
    void updateEmployee(Employee employee) throws Exception ;   
    void removeEmployee(Employee employee) throws Exception ;   
    void saveEmployeeListToFile() throws Exception ;  
    List<Employee> search(Predicate<Employee> predicate) throws Exception ;
    //--------------------------------------------------  
    //More the method here..........
}
