/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class OrderDetailTopInfo extends JPanel{
    private JComboBox<DTO.Customers> jcbCustomer;
    private JComboBox<DTO.Employee> jcbEmployee;
    private JButton btNew;
    private JPanel pnTable;
    
    public OrderDetailTopInfo(JPanel pnTable) {
        this.pnTable = pnTable;
        initOrderDetailTopInfo();
    }

    public void initOrderDetailTopInfo(){
        setPreferredSize(new Dimension(510, 250));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
        
        JLabel jlbCus = new JLabel("Chọn người nhập:");
        jlbCus.setPreferredSize(new Dimension(110, 40));
        jlbCus.setOpaque(false);
        JLabel jlbEmp = new JLabel("Chọn khách hàng:");
        jlbEmp.setPreferredSize(new Dimension(110, 40));
        jlbEmp.setOpaque(false);
        
        jcbCustomer = new JComboBox<> ();
        for (DTO.Customers cus : new BUS.CustomersBUS().getAllCustomers()){
            jcbCustomer.addItem(cus);
        }
        jcbCustomer.setPreferredSize(new Dimension(200, 40));
        
        jcbEmployee = new JComboBox<> ();
        for (DTO.Employee emp : new BUS.EmployeeBUS().getAllEmployees()){
            jcbEmployee.addItem(emp);
        }
        jcbEmployee.setPreferredSize(new Dimension(200, 40));

        
        btNew = new JButton("Hóa đơn mới");
        btNew.setPreferredSize(new Dimension(120, 40));
        btNew.setBackground(new Color(30, 144, 255)); 
        btNew.setForeground(Color.WHITE); 
        btNew.setFocusPainted(false); 
        btNew.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btNew.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btNew.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btNew.setBackground(new Color(30, 144, 255));
            }
        });
        btNew.addActionListener(e -> handleCreateOrder());
        add(jlbCus);
        add(jcbCustomer);
        add(jlbEmp);
        add(jcbEmployee);
        add(btNew);
        

        
        
    }
    
    public void handleCreateOrder(){
        DTO.Customers selectedCus = (DTO.Customers) jcbCustomer.getSelectedItem();
        if (selectedCus == null)  {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng trước khi tạo hóa đơn.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cId = selectedCus.getCustomer_id();
        DTO.Employee selectedEmp = (DTO.Employee) jcbEmployee.getSelectedItem();
        if (selectedEmp == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn người nhập trước khi tạo hóa đơn.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int eId = selectedEmp.getEmployee_id();
        if ( this.pnTable instanceof OrderDetailTable) {
            OrderDetailTable oTable = (OrderDetailTable) pnTable;
        oTable.getJtfCusName().setText(jcbCustomer.getSelectedItem().toString());
        oTable.getJtfCusId().setText(String.valueOf(cId));
            System.out.println("cid : "+cId);
        oTable.getJtfEmpId().setText(String.valueOf(eId));
            System.out.println("eId : "+eId);
        oTable.updateTotalPriceToVND();
        }
    }
}
