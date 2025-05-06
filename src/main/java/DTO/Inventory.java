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

    public int getInven_id() {
        return inven_id;
    }

    public void setInven_id(int inven_id) {
        this.inven_id = inven_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    // bổ sung thêm price
        public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    private Double price;

    public Inventory(int inven_id, String name, int quantity, String unit, Double price) {
        this.inven_id = inven_id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
    }

}
