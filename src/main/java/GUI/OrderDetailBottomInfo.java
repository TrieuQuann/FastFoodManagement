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
public class OrderDetailBottomInfo extends JPanel{
    private JComboBox<DTO.Product> jcbProduct;
    private JTextField jtfQuantity;
    private JButton btAdd;
    private JPanel pnTable;
    
    public OrderDetailBottomInfo(JPanel pnTable) {
        this.pnTable = pnTable;
        initOrderDetailBottomInfo();
    }
    public void initOrderDetailBottomInfo(){
        setPreferredSize(new Dimension(510, 250));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER,50,30));
        
        JLabel jlbProduct = new JLabel("Chọn sản phẩm:");
        jlbProduct.setPreferredSize(new Dimension(110, 40));
        jlbProduct.setOpaque(false);
        JLabel jlbquantity = new JLabel("Nhập số lượng:");
        jlbquantity.setPreferredSize(new Dimension(110, 40));
        jlbquantity.setOpaque(false);
        
        jcbProduct = new JComboBox<> ();
        for (DTO.Product pro : new BUS.ProductsBUS().getAll()){
            jcbProduct.addItem(pro);
        }
        jcbProduct.setPreferredSize(new Dimension(200, 40));
        
        jtfQuantity = new JTextField();
        jtfQuantity.setPreferredSize(new Dimension(200, 40));
        
        btAdd = new JButton("Thêm vào danh sách sản phẩm");
        btAdd.setPreferredSize(new Dimension(220, 40));
        btAdd.setBackground(new Color(30, 144, 255)); 
        btAdd.setForeground(Color.WHITE); 
        btAdd.setFocusPainted(false); 
        btAdd.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btAdd.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btAdd.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btAdd.setBackground(new Color(30, 144, 255));
            }
        });
        btAdd.addActionListener(e -> handleAddListProducts());
        add(jlbProduct);
        add(jcbProduct);
        add(jlbquantity);
        add(jtfQuantity);
        add(btAdd);
        

        
        
    }
    
    public void handleAddListProducts() {
        DTO.Product selectedPro = (DTO.Product) jcbProduct.getSelectedItem();
        if (selectedPro == null)  {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để thêm vào hóa đơn.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tfQuantity = jtfQuantity.getText().trim();
        if (tfQuantity.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng sản phẩm để thêm vào hóa đơn.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(tfQuantity);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0.", "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên hợp lệ.", "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if ( this.pnTable instanceof OrderDetailTable) {
            OrderDetailTable oTable = (OrderDetailTable) pnTable;
            String idCus = oTable.getJtfCusId().getText().trim();
            if (idCus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin người nhập và khách hàng.", "Chưa tạo mới hóa đơn", JOptionPane.WARNING_MESSAGE);
            return;
            }
            
            int pId = ((DTO.Product) jcbProduct.getSelectedItem()).getProductId();
            
            int pQuantity = new BUS.ProductsBUS().getQuantityById(pId);
            if (pQuantity < quantity){
                    JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không đủ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
            double price = new BUS.ProductsBUS().getPriceById(pId);
            double total_price = price * quantity;
            oTable.addProductRow(selectedPro.toString(), quantity, price, total_price);
            double tongtien = Double.parseDouble(oTable.getTotalPrice().toString());
            tongtien += total_price;
            oTable.setTotalPrice(tongtien);
            oTable.updateTotalPriceToVND();
            JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào danh sách mua hàng.");
        }
    }

}
