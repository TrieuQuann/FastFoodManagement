/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Lenovo
 */
public class InvoiceInventory {
    private int invoice_id;
    private int supplier_id;
    private int quantityadded;
    private Date date;
 public InvoiceInventory(int invoice_id, int supplier_id , int quantityadded , Date date) {
        this.invoice_id = invoice_id;
        this.supplier_id = supplier_id;
        this.quantityadded = quantityadded;
        this.date = date;
    }

public int getinvoice_id() {
        return invoice_id;
    }

    public void set(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getsupplier_id() {
        return supplier_id;
    }

    public void set(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getquantityadded() {
        return quantityadded;
    }

    public void set(int quantityadded) {
        this.quantityadded = quantityadded;
    }

    public Date getdate() {
        return date;
    }

    public void set(Date date) {
        this.date = date;
    }
 
}
