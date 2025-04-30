/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.Customers;

/**
 *
 * @author Lenovo
 */
public class CustomersDAO {
    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customers cus = new Customers(
                    rs.getInt("customers_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
                customers.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Thêm khách hàng
    public boolean addCustomer(Customers cus) {
        String sql = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cus.getName());
            ps.setString(2, cus.getPhone());
            ps.setString(3, cus.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa thông tin khách hàng
    public boolean updateCustomer(Customers cus) {
        String sql = "UPDATE customers SET name=?, phone=?, email=? WHERE customers_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cus.getName());
            ps.setString(2, cus.getPhone());
            ps.setString(3, cus.getEmail());
            ps.setInt(4, cus.getCustomers_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa khách hàng
    public boolean deleteCustomer(int customersId) {
        String sql = "DELETE FROM customers WHERE customers_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customersId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm khách hàng theo tên, số điện thoại hoặc email
    public List<Customers> searchCustomers(String keyword) {
        List<Customers> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customers cus = new Customers(
                        rs.getInt("customers_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                    customers.add(cus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
