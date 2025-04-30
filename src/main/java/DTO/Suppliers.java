/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Suppliers {
   private int supplier_id;
   private String supplier_name;
   private String email;
   private String address;
   private String phone;
    
 public Suppliers(int supplier_id, String supplier_name, String email, String address, String phone) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

 public int getSupplier_id() {
    return supplier_id;
 }

 public void setSupplier_id(int supplier_id) {
    this.supplier_id = supplier_id;
 }

 public String getSupplier_name() {
    return supplier_name;
 }

 public void setSupplier_name(String supplier_name) {
    this.supplier_name = supplier_name;
 }

 public String getEmail() {
    return email;
 }

 public void setEmail(String email) {
    this.email = email;
 }

 public String getAddress() {
    return address;
 }

 public void setAddress(String address) {
    this.address = address;
 }

 public String getPhone() {
    return phone;
 }

 public void setPhone(String phone) {
    this.phone = phone;
 }


}
