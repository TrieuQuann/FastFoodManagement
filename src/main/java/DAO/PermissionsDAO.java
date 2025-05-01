/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.*;
import DTO.Permissions;
/**
 *
 * @author Lenovo
 */
public class PermissionsDAO {
    private Connection conn = ConnectionDB.getConnection();
    public ArrayList<Permissions> getAllPermissions(){
        ArrayList<Permissions> list=new ArrayList<>();
        String sql="SELECT * FROM permissions";
        try(PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                Permissions per=new Permissions(rs.getInt("permission_id"), rs.getString("permission_name"));
                list.add(per);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void addPermission(Permissions per){
        String sql="INSERT INTO permissions(permission_name) VALUES (?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, per.getName());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updatePermission(Permissions per){
        String sql="UPDATE permissions SET permission_id = ?, permission_name = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, per.getPermission_id());
            stmt.setString(2, per.getName());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deletePermission(int id){
        String sql="DELETE FROM permissions WHERE permission_id = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public int searchbyname(String name){
        String sql ="SELECT * FROM permissions WHERE permission_name = ?";
        int resultint=0;
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, name);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                resultint = rs.getInt("permission_id");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return resultint;
    }
}
