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
public class RecipeDAO implements DAO.InterfaceDAO<DTO.Recipe>{

    @Override
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
            pst.setString(3, t.getUnit());
            pst.setFloat(4, t.getAmount());
            
            result = pst.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Recipe t) {
        int result = 0;
        String sql = "UPDATE recipe SET unit = ?, amount = ? WHERE product_id = ?, inven_id = ?";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) 
        {
            pst.setString(1, t.getUnit());
            pst.setFloat(2, t.getAmount());
            pst.setInt(3, t.getProductId());
            pst.setInt(4, t.getInventoryId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
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

    @Override
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
                int inven_id = rs.getInt("invent_id");
                String unit = rs.getString("unit");
                float amount = rs.getFloat("amount");
                
                DTO.Recipe sp = new DTO.Recipe(product_id, inven_id, unit, amount);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public Recipe selectById(int product_id, int inven_id) {
        DTO.Recipe result = null;

        String sql = "SELECT * FROM recipe WHERE product_id=?, inven_id=?";
        try(
            Connection con = ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            )
        {
            pst.setInt(1, product_id);
            pst.setInt(2, inven_id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String unit = rs.getString("unit");
                float amount = rs.getFloat("amount");
                
                result = new DTO.Recipe(product_id, inven_id, unit, amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Recipe> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Recipe selectById(int t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }