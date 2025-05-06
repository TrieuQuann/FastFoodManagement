/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Recipe;
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
public class RecipeDAO{

    public int insert(Recipe t) {
        int result = 0;
        String sql = "INSERT into recipe (product_id, inven_id, unit, amount) VALUES (?,?,?,?)";
                       
        try(
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                )
        {
            pst.setInt(1, t.getProductId());
            pst.setInt(2, t.getInventoryId());
//            pst.setString(3, t.getUnit());
            pst.setFloat(4, t.getAmount());
            
            result = pst.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int update(Recipe t) {
        int result = 0;
        String sql = "UPDATE recipe SET unit = ?, amount = ? WHERE product_id = ?, inven_id = ?";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) 
        {
//            pst.setString(1, t.getUnit());
            pst.setFloat(2, t.getAmount());
            pst.setInt(3, t.getProductId());
            pst.setInt(4, t.getInventoryId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Recipe t) {
        int result = 0;
        String sql = "DELETE from recipe WHERE product_id=?, inven_id=?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql))
        {
            pst.setInt(1, t.getProductId());
            pst.setInt(2, t.getInventoryId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Recipe> selectAll() {
        ArrayList<DTO.Recipe> result = new ArrayList<DTO.Recipe>();
        String sql = "SELECT * FROM recipe";
        
        try(
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            )
        {
            while (rs.next()){
                int product_id = rs.getInt("product_id");
                int inven_id = rs.getInt("inven_id");
                Double total_price = rs.getDouble("total_price");
                float amount = rs.getFloat("amount");
//                System.out.println(product_id);
                DTO.Recipe sp = new DTO.Recipe(product_id, inven_id, total_price, amount);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public ArrayList<Recipe> selectByProductId(int product_id) {
    ArrayList<Recipe> result = new ArrayList<>();

    String sql = "SELECT * FROM recipe WHERE product_id=?";
    try (
        Connection con = ConnectionDB.getConnection();
        PreparedStatement pst = con.prepareStatement(sql)
    ) {
        pst.setInt(1, product_id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int inven_id = rs.getInt("inven_id");
            double total_price = rs.getDouble("total_price");
            float amount = rs.getFloat("amount");

            Recipe r = new Recipe(product_id, inven_id, total_price, amount);
            result.add(r);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
    
    public Double getTotalById(int product_id) {
    Double result = 0.0;

    String sql = "SELECT total_price FROM recipe WHERE product_id=?";
    try (
        Connection con = ConnectionDB.getConnection();
        PreparedStatement pst = con.prepareStatement(sql)
    ) {
        pst.setInt(1, product_id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            double total_price = rs.getDouble("total_price");
            result+=total_price;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}

    }