/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Roles {
    private int role_id;
    private String rolename;

    public Roles(int role_id, String rolename) {
        this.role_id = role_id;
        this.rolename = rolename;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    @Override
    public String toString(){
        return rolename;
    }
}
