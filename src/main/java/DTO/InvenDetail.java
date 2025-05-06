package DTO;

public class InvenDetail {
    private int invenId;
    private int invoiceId;
    private int quantity;

    public InvenDetail(int invenId, int invoiceId, int quantity) {
        this.invenId = invenId;
        this.invoiceId = invoiceId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getInvenId() { return invenId; }
    public void setInvenId(int invenId) { this.invenId = invenId; }
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
