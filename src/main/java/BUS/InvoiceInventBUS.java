/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.InvoiceInventDAO;
import DTO.InvoiceInvent;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class InvoiceInventBUS {
    private InvoiceInventDAO dao = new InvoiceInventDAO();

    // Get all invoices
    public List<InvoiceInvent> getAllInvoice() {
        return dao.getAllInvoiceInventory();
    }

    public boolean addInvoice(InvoiceInvent invoice) {
        return dao.addInvoiceInvent(invoice);
    }
    // Search invoices by keyword
    public List<InvoiceInvent> searchInvoices(String column, String keyword) {
        return dao.searchInvoices(column, keyword);
    }
}
