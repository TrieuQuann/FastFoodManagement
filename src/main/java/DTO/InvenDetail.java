/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class InvenDetail {
     private int inven_id;
     private int invoice_id;
     private int quantity;
 public InvenDetail(int inven_id, int invoice_id, int quantity) {
        this.inven_id = inven_id;
        this.invoice_id = invoice_id;
        this.quantity = quantity;
    }

public int getinven_id() {
        return inven_id;
    }

    public void set(int inven_id) {
        this.inven_id = inven_id;
    }

    public int getinvoice_id() {
        return invoice_id;
    }

    public void set(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getquantity() {
        return quantity;
    }

    public void set(int quantity) {
        this.quantity = quantity;
    }
}
