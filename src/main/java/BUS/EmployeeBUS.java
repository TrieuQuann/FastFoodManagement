/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.EmployeeDAO;
import DTO.Employee;
import java.util.List;

/**
 *
 * @author Lenovo
 */
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

    public boolean deleteEmployee(int employeeId) {
        return employeeDAO.deleteEmployee(employeeId);
    }

    public List<Employee> searchEmployees(String keyword) {
        return employeeDAO.searchEmployees(keyword);
    }
    public List<Employee> getEmployeesById(int employee_id) {
        return employeeDAO.getEmployeesById(employeeId);
    }
}
