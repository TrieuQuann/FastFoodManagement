/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Customers {
    private int customers_id;
    private String name;
    private String phone;
    private String email;
    // Getters and Setters

    public int getCustomerId() {
        return customers_id;
    }

    public void setCustomerId(int customers_id) {
        this.customers_id = customers_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customers(int customers_id, String name, String phone, String email) {
        this.customers_id = customers_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
}
