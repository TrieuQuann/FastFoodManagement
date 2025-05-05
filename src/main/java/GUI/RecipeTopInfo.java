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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
    
    RecipeTopInfo(){
        initRecipeTopInfo();
    }
    
    private void initRecipeTopInfo(){
        setPreferredSize(new Dimension(450, 200));
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
        jcbCategory = new JComboBox<String> ();
        jcbCategory.setPreferredSize(new Dimension(150, 40));
        jtfPrice =new JTextField();
        jtfPrice.setPreferredSize(new Dimension(170, 40));
        
        JButton jbtChoose1 = new JButton("Chọn");
        jbtChoose1.setPreferredSize(new Dimension(70, 35));
        JButton jbtChoose2 = new JButton("Chọn");
        jbtChoose2.setPreferredSize(new Dimension(70, 35));
        JButton jbtChoose3 = new JButton("Xác nhận");
        jbtChoose3.setPreferredSize(new Dimension(90, 35));
        
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
    
}