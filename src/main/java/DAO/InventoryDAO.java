/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Inventory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class InventoryDAO {
      public List<Inventory> getAllInventory() {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inventory emp = new Inventory(
                    rs.getInt("inven_id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getString("unit")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addInventory(Inventory emp) {
        String sql = "INSERT INTO inventory (name, quantity, unit) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getQuantity());
            ps.setString(3, emp.getUnit());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInventory(Inventory emp) {
        String sql = "UPDATE inventory SET name=?, quantity=?, unit=? WHERE inven_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getQuantity());
            ps.setString(3, emp.getUnit());
            ps.setInt(4, emp.getInven_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInventory(int inven_id) {
        String sql = "DELETE FROM inventory WHERE inven_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inven_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Inventory> searchInventory(String column, String keyword) {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inventory emp = new Inventory(
                        rs.getInt("inven_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getString("unit")
                    );
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Inventory getInventoryById(int inven_id) {
        String sql = "SELECT * FROM inventory WHERE inven_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inven_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Inventory(
                        rs.getInt("inven_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getString("unit")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
