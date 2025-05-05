/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.InvoiceInventory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class InvoiceInventoryDAO {
      public List<InvoiceInventory> getAllInvoiceInventory() {
        List<InvoiceInventory> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceinvent";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InvoiceInventory emp = new InvoiceInventory(
                    rs.getInt("invoice_id"),
                    rs.getInt("supplier_id"),
                    rs.getInt("quantityadded"),
                    rs.getDate("date")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addInvoiceInventory(InvoiceInventory emp) {
        String sql = "INSERT INTO invoiceinvent (supplier_id, quantityadded, date) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp.getSupplier_id());
            ps.setInt(2, emp.getQuantityadded());
            ps.setDate(3, emp.getDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInvoiceInventory(InvoiceInventory emp) {
        String sql = "UPDATE invoiceinvent SET supplier_id=?, quantityadded=?, date=? WHERE invoice_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp.getSupplier_id());
            ps.setInt(2, emp.getQuantityadded());
            ps.setDate(3, emp.getDate());
            ps.setInt(4, emp.getInvoice_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInvoiceInventory(int invoice_id) {
        String sql = "DELETE FROM invoiceinvent WHERE invoice_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoice_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<InvoiceInventory> searchInvoiceInventory(String column, String keyword) {
        List<InvoiceInventory> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceinvent WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvoiceInventory emp = new InvoiceInventory(
                         rs.getInt("invoice_id"),
                         rs.getInt("supplier_id"),
                         rs.getInt("quantityadded"),
                         rs.getDate("date")
                    );
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee getInvoiceInventoryById(int invoice_id) {
        String sql = "SELECT * FROM invoiceinvent WHERE invoice_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoice_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("invoice_id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("quantityadded"),
                        rs.getDate("date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
