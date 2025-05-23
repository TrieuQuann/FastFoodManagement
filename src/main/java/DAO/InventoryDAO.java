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
                Inventory inventory = new Inventory(
                        rs.getInt("inven_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getString("unit"));      
                list.add(inventory);
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
            ps.setInt(4, emp.getInvenId());
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
                            rs.getString("unit"));
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
                            rs.getString("unit"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInventoryQuantity(int invenId, int quantity) {
        String sql = "UPDATE inventory SET quantity = quantity + ? WHERE inven_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, invenId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ========================= đừng xóa ==============

    public ArrayList<Inventory> selectAll() {
        ArrayList<Inventory> result = new ArrayList<>();
        String sql = "SELECT * FROM inventory"; // WHERE status = 1";
        try (
                Connection con = ConnectionDB.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int iId = rs.getInt("inven_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String unit = rs.getString("unit");
                Inventory inv = new Inventory(iId, name, quantity, unit);
                result.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // ========================= đừng xóa ==============
    public Inventory selectById(int id) {
        Inventory result = null;
        String sql = "SELECT * FROM inventory WHERE inven_id = ?"; // AND status = 1";
        try (
                Connection con = ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // int iId = rs.getInt("inven_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String unit = rs.getString("unit");
                result = new Inventory(id, name, quantity, unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // =========================== đừng xóa======
    public String getNameById(int id) {
        Inventory inv = selectById(id);
        if (inv == null) {
            System.err.println("Không tìm thấy inventory với ID: " + id);
            return null;
        }
        return inv.getName();
    }

}
