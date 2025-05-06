package DAO;

import DTO.Customers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO {
    public List<Customers> getAllCustomers() {
        List<Customers> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customers c = new Customers(
                    rs.getInt("cus_id"),
                    rs.getString("cus_name"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addCustomer(Customers customer) {
        String sql = "INSERT INTO customers (cus_name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customers customer) {
        String sql = "UPDATE customers SET cus_name=?, phone=?, email=? WHERE cus_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getCustomer_name());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getCustomer_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE cus_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customers> searchCustomers(String column, String keyword) {
        List<Customers> list = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customers c = new Customers(
                        rs.getInt("cus_id"),
                        rs.getString("cus_name"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customers getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE cus_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customers(
                        rs.getInt("cus_id"),
                        rs.getString("cus_name"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}