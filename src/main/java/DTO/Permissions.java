/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Permissions {
    private int permission_id;
    private String name;

    public Permissions(int permission_id, String name) {
        this.permission_id = permission_id;
        this.name = name;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public String getName() {
        return name;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
