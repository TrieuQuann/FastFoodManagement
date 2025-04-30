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

    public int getInven_id() {
        return inven_id;
    }

    public void setInven_id(int inven_id) {
        this.inven_id = inven_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
