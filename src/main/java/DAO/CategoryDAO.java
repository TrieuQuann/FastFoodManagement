/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Category;
import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {

    public int insert(Category t) {
        int result = 0;
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setString(1, t.getName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(String name) {
        int result = 0;
        String sql = "INSERT INTO category (name) VALUES (?)"; // status mặc định 1

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pst.setString(1, name);
            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        result = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(Category t) {
        int result = 0;
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setString(1, t.getName());
            pst.setInt(2, t.getCategoryId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Category t) {
        return delete(t.getCategoryId());
    }

    public int delete(int id) {
        int result = 0;
        String sql = "UPDATE category SET status = 0 WHERE category_id = ?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setInt(1, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Category> selectAll() {
        ArrayList<Category> result = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE status = 1";
        try (
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");
                Category category = new Category(category_id, name);
                result.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Category selectById(int id) {
        Category result = null;
        String sql = "SELECT * FROM category WHERE category_id = ? AND status = 1";
        try (
            Connection con = ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                result = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNameById(int id) {
        Category category = selectById(id);
        if (category == null) {
            System.err.println("Không tìm thấy category với ID: " + id);
            return null;
        }
        return category.getName();
    }

    public ArrayList<Category> searchByName(String search) {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE name LIKE ? AND status = 1";
        try (
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                    rs.getInt("category_id"),
                    rs.getString("name")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
