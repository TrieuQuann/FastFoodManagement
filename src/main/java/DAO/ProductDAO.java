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
public class ProductDAO implements DAO.InterfaceDAO<DTO.Product>{

    @Override
    public int insert(Product t) {
        int result = 0;
        String sql = "INSERT INTO product (productName, price, image, categoryId, expectedQuantity) "+
                "VALUES (?,?,?,?,?)";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) 
        {

            pst.setString(1, t.getProductName());
            pst.setDouble(2, t.getPrice());
            pst.setString(3, t.getImage());
            pst.setInt(4, t.getCategoryId());
            pst.setInt(5, t.getExpectedQuantity());

            result = pst.executeUpdate();
            if (result!=0){
                try (ResultSet rs = pst.getGeneratedKeys()){
                    if (rs.next()){
                        int generatedId = rs.getInt(1);
                        result=generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int update(Product t) {
        int result = 0;
        String sql = "UPDATE product SET productName = ?, price = ?, image = ?, categoryId = ?, expectedQuantity = ? WHERE product_id = ?";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) 
        {

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


    @Override
    public int delete(Product t) {
        int result = 0;
        String sql = "DELETE from product WHERE product_id=?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql))
        {
            pst.setInt(1, t.getProductId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<DTO.Product> result = new ArrayList<DTO.Product>();
        String sql = "SELECT * FROM product";
        
        try(
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            )
        {
            while (rs.next()){
                int product_id = rs.getInt("product_id");
                String productName = rs.getString("productName");
                Double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("categoryId");
                int expectedQuantity = rs.getInt("expectedQuantity");
                
                DTO.Product sp = new DTO.Product(product_id, productName, price, image, categoryId, expectedQuantity);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public Product selectById(Product t) {
        DTO.Product result = null;
        int product_id = t.getProductId();
        String sql = "SELECT * FROM product WHERE product_id=?";
        try(
            Connection con = ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            )
        {
            pst.setInt(1, product_id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String productName = rs.getString("productName");
                Double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("categoryId");
                int expectedQuantity = rs.getInt("expectedQuantity");
                
                result = new DTO.Product(product_id, productName, price, image, categoryId, expectedQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Product> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
