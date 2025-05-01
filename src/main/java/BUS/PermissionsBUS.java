/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PermissionsDAO;
import DTO.Permissions;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class PermissionsBUS {
    private PermissionsDAO dao = new PermissionsDAO();
    public ArrayList<Permissions> getAll(){
        return dao.getAllPermissions();
    }
    public void addPermission(Permissions per){
        if(per.getName().isEmpty()){
            throw new IllegalArgumentException("ID và tên quyền không được để trống");
        }
        dao.addPermission(per);
    }
    public void updatePermission(Permissions per){
        if(per.getPermission_id()<=0){
            throw new IllegalArgumentException("Mã quyền không hợp lệ");
        }
        dao.updatePermission(per);
    }
    public void deletePermission(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("ID quyền không hợp lệ");
        }
        dao.deletePermission(id);
    }
    public int searchpermissionid(String name){
        return dao.searchbyname(name);
    }
}
