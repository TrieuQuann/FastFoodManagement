/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Users {
    private int User_id,role_id,eid;
    private String username,password;

    public Users(int User_id, int role_id, int employee_id, String username, String password) {
        this.User_id = User_id;
        this.role_id = role_id;
        this.eid = employee_id;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return User_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public int getEmployee_id() {
        return eid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setEmployee_id(int eid) {
        this.eid = eid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
