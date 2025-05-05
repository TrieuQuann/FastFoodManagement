/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.*;
import DTO.Users;
/**
 *
 * @author Lenovo
 */
public class UsersDAO {
    private Connection conn = ConnectionDB.getConnection();
    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Users u = new Users(
                    rs.getInt("user_id"),
                    rs.getInt("role_id"),
                    rs.getInt("eid"),
                    rs.getString("username"),
                    rs.getString("paswd"));
                list.add(u);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    public void insertUser(Users user){
        String sql = "INSERT INTO users(role_id,eid,username,paswd) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, user.getRole_id());
            stmt.setInt(2, user.getEmployee_id());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateUser(Users user){
        String sql = "UPDATE users SET role_id = ?, eid = ?, username = ?, paswd = ? WHERE user_id = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(5, user.getUser_id());
            stmt.setInt(1, user.getRole_id());
            stmt.setInt(2, user.getEmployee_id());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteUser(int id){
        String sql="DELETE FROM users WHERE user_id = ?";
        try(PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
