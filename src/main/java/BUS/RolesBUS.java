/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RolesDAO;
import DTO.Roles;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class RolesBUS {
    private RolesDAO dao = new RolesDAO();
    public ArrayList<Roles> getAll(){
        return dao.getAllRoles();
    }
    public void addRole(Roles role){
        if(role.getRolename().isEmpty()){
            throw new IllegalArgumentException("rolename không hợp lệ");
        }
        dao.addRole(role);
    }
    public void updateRole(Roles role){
        if(role.getRole_id()<=0){
            throw new IllegalArgumentException("ID không hợp lệ");
        }
        dao.updateRole(role);
    }
    public void deleteRole(int id){
        if(id<=0){
            throw new IllegalArgumentException("ID không hợp lệ");
        }
        dao.deleteRole(id);
    }
    public String searchrolename(int id){
        return dao.searchbyId(id);
    }
    public int searchroleid(String name){
        return dao.searchbyname(name);
    }
}
