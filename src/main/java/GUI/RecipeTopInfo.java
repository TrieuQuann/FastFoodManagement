/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.SystemColor.text;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class RecipeTopInfo extends JPanel{
    private JComboBox<String> jcbCategory;
    private JComboBox<String> jcbFood;
    private JTextField jtfPrice;
    private RecipeTable pnTable;
    private Double TongTien;
    private Double GiaBan;

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public Double getTongTien() {
        return TongTien;
    }

    public void setTongTien(Double TongTien) {
        this.TongTien = TongTien;
    }

    public RecipeTable getPnTable() {
        return pnTable;
    }

    public void setPnTable(RecipeTable pnTable) {
        this.pnTable = pnTable;
    }

    public JTextField getJtfPrice() {
        return jtfPrice;
    }

    public void setJtfPrice(JTextField jtfPrice) {
        this.jtfPrice = jtfPrice;
    }

    public JComboBox<String> getJcbCategory() {
        return jcbCategory;
    }

    public void setJcbCategory(JComboBox<String> jcbCategory) {
        this.jcbCategory = jcbCategory;
    }

    public JComboBox<String> getJcbFood() {
        return jcbFood;
    }

    public void setJcbFood(JComboBox<String> jcbFood) {
        this.jcbFood = jcbFood;
    }
    
    RecipeTopInfo(RecipeTable pnTable){
        this.pnTable = pnTable;
        initRecipeTopInfo();
    }
    
    private void initRecipeTopInfo(){
        setPreferredSize(new Dimension(450, 210));
        setBorder(BorderFactory.createLineBorder(new Color(0, 51, 153), 2));
        setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        
        JLabel jlbCategory = new JLabel("Chọn loại món:");
        jlbCategory.setPreferredSize(new Dimension(95, 40));
        jlbCategory.setOpaque(false);
        JLabel jlbFood = new JLabel("Chọn món ăn:");
        jlbFood.setPreferredSize(new Dimension(95, 40));
        jlbFood.setOpaque(false);
        JLabel jlbPrice = new JLabel("Giá bán:");
        jlbPrice.setPreferredSize(new Dimension(75, 40));
        jlbPrice.setOpaque(false);
        
        jcbFood = new JComboBox<String> ();
        jcbFood.setPreferredSize(new Dimension(150, 40));
        jcbCategory = new JComboBox<>(new BUS.CategoryBUS().getAllCategoryName());
        jcbCategory.setPreferredSize(new Dimension(150, 40));
        jtfPrice =new JTextField();
        jtfPrice.setPreferredSize(new Dimension(170, 40));
        
        JButton jbtChoose1 = new JButton("Chọn");
        jbtChoose1.setPreferredSize(new Dimension(70, 35));
//        jbtChoose1.setActionCommand(text);
        jbtChoose1.setBackground(new Color(30, 144, 255)); 
        jbtChoose1.setForeground(Color.WHITE); 
        jbtChoose1.setFocusPainted(false); 
        jbtChoose1.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        jbtChoose1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        jbtChoose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtChoose1.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtChoose1.setBackground(new Color(30, 144, 255));
            }
        });
        jbtChoose1.addActionListener(e -> handleChoose1());
        JButton jbtChoose2 = new JButton("Chọn");
        jbtChoose2.setPreferredSize(new Dimension(70, 35));
//        jbtChoose2.setActionCommand(text);
        jbtChoose2.setBackground(new Color(30, 144, 255)); 
        jbtChoose2.setForeground(Color.WHITE); 
        jbtChoose2.setFocusPainted(false); 
        jbtChoose2.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        jbtChoose2.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        jbtChoose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtChoose2.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtChoose2.setBackground(new Color(30, 144, 255));
            }
        });
        jbtChoose2.addActionListener(e -> handleChoose2());
        JButton jbtChoose3 = new JButton("Xác nhận");
        jbtChoose3.setPreferredSize(new Dimension(90, 35));
//        jbtChoose3.setActionCommand(text);
        jbtChoose3.setBackground(new Color(30, 144, 255)); 
        jbtChoose3.setForeground(Color.WHITE); 
        jbtChoose3.setFocusPainted(false); 
        jbtChoose3.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        jbtChoose3.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        jbtChoose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtChoose3.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtChoose3.setBackground(new Color(30, 144, 255));
            }
        });
        jbtChoose3.addActionListener(e -> handleChoose3());
        
        add(jlbCategory);
        add(jcbCategory);
        add(jbtChoose1);
        add(jlbFood);
        add(jcbFood);
        add(jbtChoose2);
        add(jlbPrice);
        add(jtfPrice);
        add(jbtChoose3);
        
    }

    private void handleChoose1() {
        String searchCategory = getJcbCategory().getSelectedItem().toString();
        if (searchCategory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại món ăn!");
            return;
        }
        String[] foods = new BUS.ProductsBUS().searchByCategoryName(searchCategory);
        if (foods.length == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy món ăn phù hợp.");
            jcbFood = new JComboBox<>(new String[]{"(Không có dữ liệu)"});
        } else {
            jcbFood.setModel(new DefaultComboBoxModel<>(foods));
        }
    }


    void handleChoose2() {
        Object selected = getJcbFood().getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại món ăn!");
            return;
        }
        String searchFood = selected.toString().trim();
        if (searchFood.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn!");
            return;
        }
        int pid = new BUS.ProductsBUS().getIdByName(searchFood);
        if (pnTable != null) {
            pnTable.reloadData(pid);
            TongTien = new BUS.RecipeBUS().getTotalById(pid);
            pnTable.getJtfTongTien().setVisible(true);
            pnTable.getJtfTongTien().setText(formatCurrency(TongTien));
            GiaBan = new BUS.ProductsBUS().getPriceById(pid);
            jtfPrice.setText(GiaBan.toString());
        } else {
            System.err.println("pnTable is null!"); 
        }
        
    }

    private void handleChoose3() {
        Object selected = getJcbFood().getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn!");
            return;
        }
        String foodName = selected.toString().trim();
        if (foodName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn!");
            return;
        }


        int pId = new BUS.ProductsBUS().getIdByName(foodName);

        String priceText = jtfPrice.getText().trim();
        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống!");
            return;
        }

    
        double newPrice;
        try {
            TongTien = new BUS.RecipeBUS().getTotalById(pId);
            newPrice = Double.parseDouble(priceText);
            if (newPrice <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
                return;
            }
            if (newPrice <= TongTien) {
                JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn tổng chi phí!");
                return;
            }
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số hợp lệ!");
            return;
        }

        boolean updated = new BUS.ProductsBUS().updatePriceById(pId, newPrice);
        if (updated) {
            JOptionPane.showMessageDialog(this, "Cập nhật giá bán thành công.");
            this.setGiaBan(newPrice);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật giá bán thất bại!");
        }
    }

    
    private String formatCurrency(Double amount) {
        if (amount == null) {
            return "0 VNĐ";
        }
        return String.format("%,.0f VNĐ", amount);
    }
}