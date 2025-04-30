/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CustomersDAO;
import DTO.Customers;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class CustomersBUS {
    private CustomersDAO customersDAO = new CustomersDAO();

    public List<Customers> getAllCustomers() {
        return customersDAO.getAllCustomers();
    }

    public boolean addCustomer(Customers cus) {
        return customersDAO.addCustomer(cus);
    }

    public boolean updateCustomer(Customers cus) {
        return customersDAO.updateCustomer(cus);
    }

    public boolean deleteCustomer(int customersId) {
        return customersDAO.deleteCustomer(customersId);
    }

    public List<Customers> searchCustomers(String keyword) {
        return customersDAO.searchCustomers(keyword);
    }
}
