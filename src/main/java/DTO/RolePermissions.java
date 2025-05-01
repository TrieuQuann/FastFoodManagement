/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class RolePermissions {
    private int role_id, permission_id;
    private String prop;

    public RolePermissions(int role_id, int permission_id, String prop) {
        this.role_id = role_id;
        this.permission_id = permission_id;
        this.prop = prop;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getProp() {
        return prop;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
    
}
