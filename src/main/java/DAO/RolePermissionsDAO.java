/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.RolePermissions;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class RolePermissionsDAO {
    private Connection conn=ConnectionDB.getConnection();
    public ArrayList<RolePermissions> getAllRP(){
        ArrayList<RolePermissions> list=new ArrayList<>();
        String sql="SELECT * FROM rolepermission";
        try(PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery()){
            while(rs.next()){
                RolePermissions rp=new RolePermissions(rs.getInt("role_id"), rs.getInt("permission_id"), rs.getString("prop"));
                list.add(rp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void addRP(RolePermissions rp){
        String sql="INSERT INTO rolepermission(role_id,permission_id,prop) VALUES (?, ?, ?)";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, rp.getRole_id());
            stmt.setInt(2, rp.getPermission_id());
            stmt.setString(3, rp.getProp());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateRP(RolePermissions rp){
        String sql="UPDATE roles SET role_id = ?, permission_id = ?, prop = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, rp.getRole_id());
            stmt.setInt(2, rp.getPermission_id());
            stmt.setString(3, rp.getProp());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteRP(RolePermissions rp){
        String sql="DELETE FROM rolepermission WHERE role_id = ? AND permission_id = ? AND prop = ?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setInt(1, rp.getRole_id());
            stmt.setInt(2, rp.getPermission_id());
            stmt.setString(3, rp.getProp());
            stmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<RolePermissions> getRoleById(int roleId){
        ArrayList<RolePermissions> list=new ArrayList<>();
        String sql="SELECT * FROM rolepermission WHERE role_id=?";
        try(Connection conn = ConnectionDB.getConnection()){
            PreparedStatement stmt =conn.prepareStatement(sql);
            stmt.setInt(1,roleId);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                list.add(new RolePermissions(rs.getInt("role_id"),rs.getInt("permission_id"),rs.getString("prop")));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
    public static void save(int roleId,int permissionId,String prop){
        String checksql="SELECT * FROM rolepermission WHERE role_id = ? AND permission_id = ? AND prop = ?";
        String insertsql="INSERT INTO rolepermission(role_id,permission_id,prop) VALUES (?, ?, ?)";
        try (Connection conn=ConnectionDB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(checksql);
            stmt.setInt(1, roleId);
            stmt.setInt(2, permissionId);
            stmt.setString(3, prop);
            ResultSet rs=stmt.executeQuery();
            if(!rs.next()){
                PreparedStatement instmt=conn.prepareStatement(insertsql);
                instmt.setInt(1, roleId);
                instmt.setInt(2, permissionId);
                instmt.setString(3, prop);
                instmt.executeUpdate();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void deleteAllbyRoleId(int roleId){
        String sql="DELETE FROM rolepermission WHERE role_id = ?";
        try(Connection conn = ConnectionDB.getConnection()){
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setInt(1, roleId);
            stmt.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<RolePermissions> filterbyID(int id){
        ArrayList<RolePermissions> list = new ArrayList<>();
        String sql="SELECT * FROM rolepermission WHERE role_id = ?";
        try(Connection conn = ConnectionDB.getConnection()){
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RolePermissions rp=new RolePermissions(rs.getInt("role_id"), rs.getInt("permission_id"), rs.getString("prop"));
                list.add(rp);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
