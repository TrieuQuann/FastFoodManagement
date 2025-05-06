/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.InvoiceInvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class InvoiceInventDAO {
    public List<InvoiceInvent> getAllInvoiceInventory() {
        List<InvoiceInvent> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceinvent";
        try (Connection conn = ConnectionDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                InvoiceInvent emp = new InvoiceInvent(
                        rs.getInt("invoice_id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("quantityadded"),
                        rs.getDate("date"));
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addInvoiceInvent(InvoiceInvent invoice) {
        String sql = "INSERT INTO invoiceinvent (supplier_id, quantityadded, date) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, invoice.getSupplierId());
            ps.setInt(2, invoice.getQuantityAdded());
            ps.setDate(3, new java.sql.Date(invoice.getDate().getTime()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    invoice.setInvoiceId(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<InvoiceInvent> searchInvoices(String column, String keyword) {
        List<InvoiceInvent> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceinvent WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvoiceInvent emp = new InvoiceInvent(
                            rs.getInt("invoice_id"),
                            rs.getInt("supplier_id"),
                            rs.getInt("quantityadded"),
                            rs.getDate("date"));
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
