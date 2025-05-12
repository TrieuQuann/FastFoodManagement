/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.UsersDAO;
import DTO.Users;
import java.util.*;
/**
 *
 * @author Lenovo
 */
public class UsersBUS {
    private UsersDAO dao = new UsersDAO();
    public ArrayList<Users> getALL(){
        return dao.getAllUsers();
    }
    public void addUser(Users user){
        if(user.getUsername().isEmpty()||user.getPassword().isEmpty()){
            throw new IllegalArgumentException("Tên đăng nhập và mật khẩu không được để trống");
        }
        user.setPassword(Hasher.hashPassword(user.getPassword()));
        dao.insertUser(user);
    }
    public void updateUser(Users user){
        if(user.getUsername().isEmpty()){
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        dao.updateUser(user);
    }
    public void deleteUser(int id){
        if(id<=0){
            throw new IllegalArgumentException("ID không hợp lệ");
        }
        dao.deleteUser(id);
    }
}
