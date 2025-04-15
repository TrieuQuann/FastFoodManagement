/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Category;
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
public class CategoryDAO implements DAO.InterfaceDAO<DTO.Category>{

    @Override
    public int insert(Category t) {
        int result = 0;
        String sql = "INSERT into category (category_id, name) VALUES (?)";
        
        try(
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                )
        {
            pst.setString(1, t.getName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int insert(String name) {
        int result = 0;
        String sql = "INSERT into category (category_id, name) VALUES (?)";
        
        try(
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                )
        {
            pst.setString(1, name);
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
    public int update(Category t) {
        int result = 0;
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";

        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) 
        {

            pst.setString(1, t.getName());
            pst.setInt(2, t.getCategoryId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Category t) {
        int result = 0;
        String sql = "DELETE from category WHERE category_id=?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql))
        {
            pst.setInt(1, t.getCategoryId());

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int delete(int id) {
        int result = 0;
        String sql = "DELETE from category WHERE category_id=?";
        try (
            Connection con = DAO.ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql))
        {
            pst.setInt(1,id);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Category> selectAll() {
        ArrayList<DTO.Category> result = new ArrayList<DTO.Category>();
        String sql = "SELECT * FROM category";
        
        try(
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            )
        {
            while (rs.next()){
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");
                
                DTO.Category sp = new DTO.Category(category_id, name);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    @Override
    public Category selectById(int t) {
        DTO.Category result = null;
        int category_id = t;
        String sql = "SELECT * FROM category WHERE category_id=?";
        try(
            Connection con = ConnectionDB.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            )
        {
            pst.setInt(1, category_id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                
                result = new DTO.Category(category_id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Category> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
