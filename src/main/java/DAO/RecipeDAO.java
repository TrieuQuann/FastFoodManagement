/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class RecipeDAO implements DAO.InterfaceDAO{

    @Override
    public int insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList selectAll() {
        ArrayList<DTO.Category> result =new ArrayList<DTO.Category>();
        
        try{
            //
            Connection con = ConnectionDB.getConnection();
            
            //
            Statement st = con.createStatement();
            
            //
            String sql = "SELECT * FROM category";
            ResultSet rs = st.executeQuery(sql);
            
            //
            while(rs.next()){
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");
                
                DTO.Category dm = new DTO.Category(category_id, name);
                result.add(dm);            
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public Object selectById(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
