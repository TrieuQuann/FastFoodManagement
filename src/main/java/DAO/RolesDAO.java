/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Roles;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class RolesDAO {
    private Connection conn = ConnectionDB.getConnection();
    public ArrayList<Roles> getAllRoles(){
        ArrayList<Roles> list = new ArrayList<>();
        String sql = "SELECT * FROM roles";
        try(PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Roles role=new Roles(rs.getInt("role_id"), rs.getString("role_name"));
                list.add(role);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void addRole(Roles role){
        String sql="INSERT INTO roles(role_name) VALUES (?)";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1, role.getRolename());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateRole(Roles role){
        String sql="UPDATE roles SET role_id = ?, role_name = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, role.getRole_id());
            stmt.setString(2, role.getRolename());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteRole(int id){
        String sql="DELETE FROM roles WHERE role_id = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public String searchbyId(int id){
        String sql="SELECT * FROM roles WHERE role_id = ? ";
        String resultstring="";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resultstring=rs.getString("role_name");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return resultstring;
    }
    public int searchbyname(String name){
        String sql = "SELECT * FROM roles WHERE role_name = ?";
        int resultint= 0 ;
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resultint = rs.getInt("role_id");
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return resultint;
    }
}
