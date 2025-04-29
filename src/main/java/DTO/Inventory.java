/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class Inventory {
    private int inven_id;
    private String name;
    private int quantity;
    private String unit;
 public Inventory(int inven_id, String name, int quantity, String unit) {
        this.inven_id = inven_id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

public int getinven_id() {
        return inven_id;
    }

    public void set(int inven_id) {
        this.inven_id = inven_id;
    }

    public String getname() {
        return name;
    }

    public void set(String name) {
        this.name = name;
    }

    public int getquantity() {
        return quantity;
    }

    public void set(int quantity) {
        this.quantity = quantity;
    }

    public String getunit() {
        return unit;
    }

    public void set(String unit) {
        this.unit = unit;
    }
}
