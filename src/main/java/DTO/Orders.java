package DTO;

import java.util.Date;

public class Orders {
    private int order_id;
    private int cus_id;
    private int employee_id;
    private Date order_date;
    private double total_amount;
    private String customerName; // Thêm trường này
    private String employeeName; // Thêm trường này

    // Constructor
    public Orders(int order_id, int cus_id, int employee_id, Date order_date, double total_amount) {
        this.order_id = order_id;
        this.cus_id = cus_id;
        this.employee_id = employee_id;
        this.order_date = order_date;
        this.total_amount = total_amount;
    }

    public Orders(int order_id, int cus_id, int employee_id, Date order_date, double total_amount,
            String customerName, String employeeName) {
        this(order_id, cus_id, employee_id, order_date, total_amount); // Gọi constructor cũ
        this.customerName = customerName;
        this.employeeName = employeeName;
    }

    // Getters and Setters
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}