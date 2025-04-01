/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class ProductDAO implements DAO.InterfaceDAO<DTO.Product>{

    @Override
    public int insert(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<DTO.Product> result = new ArrayList<DTO.Product>();
        
        try{
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM product";
            ResultSet rs = st.executeQuery(sql);
            
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
        try{
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM product WHERE product_id='"+t.getProductId()+"'  ";
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                int product_id = rs.getInt("product_id");
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
