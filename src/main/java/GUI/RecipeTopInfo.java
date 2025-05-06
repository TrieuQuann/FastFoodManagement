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
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
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
        jbtChoose1.addActionListener(e -> handleChoose1());
        JButton jbtChoose2 = new JButton("Chọn");
        jbtChoose2.setPreferredSize(new Dimension(70, 35));
        jbtChoose2.addActionListener(e -> handleChoose2());
        JButton jbtChoose3 = new JButton("Xác nhận");
        jbtChoose3.setPreferredSize(new Dimension(90, 35));
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


    private void handleChoose2() {
        String searchFood = getJcbFood().getSelectedItem().toString();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private String formatCurrency(Double amount) {
        if (amount == null) {
            return "0 VNĐ";
        }
        return String.format("%,.0f VNĐ", amount);
    }
}