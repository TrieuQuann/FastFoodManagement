/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class InvoiceInventory {
    private int invoice_id;
    private int supplier_id;
    private int quantityadded;
    private Date date;

    public InvoiceInventory(int invoice_id, int supplier_id, int quantityadded, Date date) {
        this.invoice_id = invoice_id;
        this.supplier_id = supplier_id;
        this.quantityadded = quantityadded;
        this.date = date;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getQuantityadded() {
        return quantityadded;
    }

    public void setQuantityadded(int quantityadded) {
        this.quantityadded = quantityadded;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
