/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.InvenDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class InvenDetailDAO {
    public List<InvenDetail> getDetailsByInvoiceId(int invoiceId) {
        List<InvenDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM invendetail WHERE invoice_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invoiceId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvenDetail detail = new InvenDetail(
                        rs.getInt("inven_id"),
                        rs.getInt("invoice_id"),
                        rs.getInt("quantity")
                    );
                    list.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addInvendetail(InvenDetail detail) {
        String sql = "INSERT INTO invendetail (inven_id, invoice_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getInvenId());
            ps.setInt(2, detail.getInvoiceId());
            ps.setInt(3, detail.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDetail(InvenDetail detail) {
        String sql = "UPDATE invendetail SET quantity = ? WHERE inven_id = ? AND invoice_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getQuantity());
            ps.setInt(2, detail.getInvenId());
            ps.setInt(3, detail.getInvoiceId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteDetail(int invenId, int invoiceId) {
        String sql = "DELETE FROM invendetail WHERE inven_id = ? AND invoice_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invenId);
            ps.setInt(2, invoiceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public InvenDetail getDetailById(int invenId) {
        InvenDetail detail = null;
        String sql = "SELECT * FROM invendetail WHERE inven_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, invenId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    detail = new InvenDetail(
                        rs.getInt("inven_id"),
                        rs.getInt("invoice_id"),
                        rs.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }
    public List<InvenDetail> searchDetails(String column, String keyword) {
        List<InvenDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM invendetail WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvenDetail detail = new InvenDetail(
                        rs.getInt("inven_id"),
                        rs.getInt("invoice_id"),
                        rs.getInt("quantity")
                    );
                    list.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
