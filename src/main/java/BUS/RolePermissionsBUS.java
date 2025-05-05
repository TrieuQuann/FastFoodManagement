/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RolePermissionsDAO;
import DTO.RolePermissions;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class RolePermissionsBUS {
    private RolePermissionsDAO dao = new RolePermissionsDAO();
    public ArrayList<RolePermissions> getAll(){
        return dao.getAllRP();
    }
    public void addRP(RolePermissions rp){
        if(rp.getPermission_id()<=0||rp.getRole_id()<=0||rp.getProp().isEmpty()){
            throw new IllegalArgumentException("Các thông tin không được để trống");
        }
        dao.addRP(rp);
    }
    public void updateRP(RolePermissions rp){
        if(rp.getPermission_id()<=0||rp.getRole_id()<=0||rp.getProp().isEmpty()){
            throw new IllegalArgumentException("Thông tin không hợp lệ");
        }
        dao.updateRP(rp);
    }
    public void deleteRP(RolePermissions rp){
        if(rp.getPermission_id()<=0||rp.getRole_id()<=0||rp.getProp().isEmpty()){
            throw new IllegalArgumentException("Các thông tin không được để trống");
        }
        dao.deleteRP(rp);
    }
    public ArrayList<RolePermissions> getbyID(int id){
        return dao.filterbyID(id);
    }
}
