/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Suppliers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class SuppliersDAO {
      public List<Suppliers> getAllSuppliers() {
        List<Suppliers> list = new ArrayList<>();
        String sql = "SELECT * FROM Suppliers";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Suppliers emp = new Suppliers(
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("phone")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addSuppliers(Suppliers emp) {
        String sql = "INSERT INTO suppliers (supplier_name ,email ,address ,phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getSupplier_name());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getAddress());
            ps.setString(4, emp.getPhone());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSuppliers(Suppliers emp) {
        String sql = "UPDATE suppliers SET supplier_name=?, email=?, address=?, phone=? WHERE supplier_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getSupplier_name());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getAddress());
            ps.setString(4, emp.getPhone());
            ps.setInt(5, emp.getSupplier_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSuppliers(int supplier_id) {
        String sql = "DELETE FROM suppliers WHERE supplier_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplier_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Suppliers> searchSuppliers(String column, String keyword) {
        List<Suppliers> list = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Suppliers emp = new Suppliers(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone")
                    );
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Suppliers getSuppliersById(int supplier_id) {
        String sql = "SELECT * FROM suppliers WHERE supplier_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supplier_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Suppliers(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    } 
}