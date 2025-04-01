/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Category;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CategoryDAO implements InterfaceDAO<DTO.Category>{

    @Override
    public int insert(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Category> selectAll() {
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
    public Category selectById(Category t) {
        DTO.Category result = null ;
        try{
            
            //
            Connection con = ConnectionDB.getConnection();
            
            //
            Statement st = con.createStatement();
            
            //
            String sql = "SELECT * FROM category WHERE id='"+t.getCategoryId()+"'";
            ResultSet rs = st.executeQuery(sql);
            
            //
            while(rs.next()){
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");

                
//                NhanVien nv = new NhanVien(id, fullName, ngaySinh, email, sdt, diaChi, phongBan);
                result = new DTO.Category(category_id, name);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
        
    }

    @Override
    public ArrayList<Category> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
