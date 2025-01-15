package DataObject;

import Core.Interfaces.IEmployeeDAO;
import Core.Entities.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeDAO implements IEmployeeDAO {

    private List<Employee> employeeList = new ArrayList<>();
    private final FileManager fileManager;

    public EmployeeDAO() throws Exception {
        this.fileManager = new FileManager("Employees.txt");
        loadDataFromFile();
    }

    //--------------------------------------------------   
    public void loadDataFromFile() throws Exception {
        String id, name, email;
        List<String> empData = fileManager.readDataFromFile();
        employeeList.clear();
        for (String e : empData) {
            List<String> emp = Arrays.asList(e.split(","));
            id = emp.get(0).trim();
            name = emp.get(1).trim();
            email = emp.get(2).trim();
            Employee newEmp = new Employee(id, name, email);
            employeeList.add(newEmp);
            if (employeeList.isEmpty()) {
                throw new Exception("Employee list is empty.");
            }
        }
    }

    //--------------------------------------------------
    @Override
    public List<Employee> getEmployees() throws Exception {
        Collections.sort(employeeList, (e1, e2) -> e1.getId().compareTo(e2.getId()));
        return employeeList;
    }

    //--------------------------------------------------
    @Override
    public void addEmployee(Employee employee) throws Exception {
        employeeList.add(employee);
        saveEmployeeListToFile();
    }
    //--------------------------------------------------

    @Override
    public void saveEmployeeListToFile() throws Exception {
        List<String> stringObjects = employeeList.stream().map(String::valueOf).collect(Collectors.toList());
        String data = String.join("\n", stringObjects);
        fileManager.saveDataToFile(data);
    }

    //--------------------------------------------------
    @Override
    public void updateEmployee(Employee employee) throws Exception {
        Employee employeeUpdate = getEmployeeById(employee.getId());
        if (employeeUpdate != null) {
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setEmail(employee.getEmail());
            saveEmployeeListToFile();
        }
    }

    //--------------------------------------------------
    @Override
    public void removeEmployee(Employee employee) throws Exception {
        Employee emp = getEmployeeById(employee.getId());
        if (emp != null) {
            employeeList.remove(emp);
            saveEmployeeListToFile();
        }
    }

    @Override
    public Employee getEmployeeById(String id) throws Exception {
        if (employeeList.isEmpty()) {
            getEmployees();
        }
        Employee employee = employeeList.stream()
                .filter(e -> e.getId()
                .equalsIgnoreCase(id)).findAny().orElse(null);
        return employee;
    }

    @Override
    public List<Employee> search(Predicate<Employee> predicate) throws Exception {
        return employeeList.stream().
                filter(employee -> predicate.test(employee)).collect(Collectors.toList());
    }
    //--------------------------------------------------------------------
    //More the methods here..........
}
