/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ProductDAO  {

    public int insert(Product t) {
        int result = 0;
        String sql = "INSERT INTO products (name, price, image, category_id, expected_quantity) " +
                "VALUES (?,?,?,?,?)";

        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, t.getProductName());
            pst.setDouble(2, t.getPrice());
            pst.setString(3, t.getImage());
            pst.setInt(4, t.getCategoryId());
            pst.setInt(5, t.getExpectedQuantity());

            result = pst.executeUpdate();
            if (result != 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        result = generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int insert(String pname, String image, int cid) {
        int result = 0;
        String sql = "INSERT INTO products (name, image, category_id) " +
                "VALUES (?,?,?)";

        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, pname);
            pst.setString(2, image);
            pst.setInt(3, cid);

            result = pst.executeUpdate();
            if (result != 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        result = generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Product t) {
        int result = 0;
        String sql = "UPDATE products SET name = ?, price = ?, image = ?, category_id = ?, expected_quantity = ? WHERE product_id = ?";

        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, t.getProductName());
            pst.setDouble(2, t.getPrice());
            pst.setString(3, t.getImage());
            pst.setInt(4, t.getCategoryId());
            pst.setInt(5, t.getExpectedQuantity());
            pst.setInt(6, t.getProductId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int update(int id, String name, String image, int category_id) {
        int result = 0;
        String sql = "UPDATE products SET name = ?, image = ?, category_id = ? WHERE product_id = ?";

        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, image);
            pst.setInt(3, category_id);
            pst.setInt(4, id);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Product t) {
        int result = 0;
        String sql = "DELETE from products WHERE product_id=?";
        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, t.getProductId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(int id) {
        int result = 0;
        String sql = "UPDATE products SET status = 0 WHERE product_id = ?";
        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Product> selectAll() {
        ArrayList<DTO.Product> result = new ArrayList<DTO.Product>();
        String sql = "SELECT * FROM products WHERE status = 1";

        try (
                Connection con = ConnectionDB.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String productName = rs.getString("name");
                Double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                int expectedQuantity = rs.getInt("expected_quantity");

                DTO.Product sp = new DTO.Product(product_id, productName, price, image, categoryId, expectedQuantity);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Product selectById(int t) {
        DTO.Product result = null;
        int product_id = t;
        String sql = "SELECT * FROM products WHERE product_id=?";
        try (
                Connection con = ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("name");
                Double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                int expectedQuantity = rs.getInt("expected_quantity");

                result = new DTO.Product(product_id, productName, price, image, categoryId, expectedQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int selectCategoryIdByName(String name) {
        int result =0;
        String sql = "SELECT category_id FROM category WHERE name=?";
        try (
                Connection con = ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {  
                result = rs.getInt("category_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getNextProductIdByMax() {
        int nextId = 1;
        String sql = "SELECT MAX(product_id) AS max_id FROM products";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                if (!rs.wasNull()) {
                    nextId = maxId + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextId;
    }
    
    

}
