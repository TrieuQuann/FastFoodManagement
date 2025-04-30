// package BUS;

// import DAO.CustomersDAO;
// import DTO.Customers;
// import java.util.List;

// public class CustomersBUS {
//     private CustomersDAO customersDAO = new CustomersDAO();

//     // Thêm các phương thức mới
//     public boolean addCustomer(Customers customer) {
//         return customersDAO.addCustomer(customer);
//     }

//     // Trong lớp CustomersBUS
//     public Customers getCustomerById(int customerId) {
//         return customersDAO.getCustomerById(customerId); // Gọi phương thức từ DAO
//     }

//     public List<Customers> getAllCustomers() {
//         return customersDAO.getAllCustomers();
//     }

//     public boolean updateCustomer(Customers customer) {
//         return customersDAO.updateCustomer(customer);
//     }

//     public boolean deleteCustomer(int customerId) {
//         return customersDAO.deleteCustomer(customerId);
//     }

//     public List<Customers> searchCustomers(String searchType, String keyword) {
//         return customersDAO.searchCustomers(searchType, keyword);
//     }
// }
package BUS;

import DAO.CustomersDAO;
import DTO.Customers;
import java.util.List;

public class CustomersBUS {
    private CustomersDAO customersDAO = new CustomersDAO();

    public List<Customers> getAllCustomers() {
        return customersDAO.getAllCustomers();
    }

    public boolean addCustomer(Customers customer) {
        return customersDAO.addCustomer(customer);
    }

    public boolean updateCustomer(Customers customer) {
        return customersDAO.updateCustomer(customer);
    }
    
    public boolean deleteCustomer(int customerId) {
        return customersDAO.deleteCustomer(customerId);
    }

    public List<Customers> searchCustomers(String column, String keyword) {
        return customersDAO.searchCustomers(column, keyword);
    }

    public Customers getCustomerById(int customerId) {
        return customersDAO.getCustomerById(customerId);
    }
}