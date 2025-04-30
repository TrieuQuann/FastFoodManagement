package BUS;

import DAO.EmployeeDAO;
import DTO.Employee;
import java.util.List;

public class EmployeeBUS {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public boolean addEmployee(Employee emp) {
        return employeeDAO.addEmployee(emp);
    }

    public boolean updateEmployee(Employee emp) {
        return employeeDAO.updateEmployee(emp);
    }

    public boolean deleteEmployee(int employee_id) {
        return employeeDAO.deleteEmployee(employee_id);
    }

    public List<Employee> searchEmployees(String column, String keyword) {
        return employeeDAO.searchEmployees(column, keyword);
    }

    public Employee getEmployeeById(int employee_id) {
        return employeeDAO.getEmployeeById(employee_id);
    }
}