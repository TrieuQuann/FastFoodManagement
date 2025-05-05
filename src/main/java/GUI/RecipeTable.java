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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author LENOVO
 */
public class RecipeTable extends JPanel{
    private JPanel jpnBottomInfo;
//    private RecipeTableModel model;
    private JTable jTable;
    
    RecipeTable(JPanel jpnBottomInfo){
//        this.model = new RecipeTableModel();
        this.jpnBottomInfo = jpnBottomInfo;
        initRecipeTable();
    }
    
    RecipeTable(){
//        this.model = new RecipeTableModel();
        initRecipeTable();
    }
    
    private void initRecipeTable(){
        setPreferredSize(new Dimension(500, 700));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new BorderLayout());
        
        JPanel jpnTitle = new JPanel();
        jpnTitle.setPreferredSize(new Dimension(450, 50));
        jpnTitle.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        jpnTitle.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        
        JLabel jlbTitle = new JLabel("Chi tiết công thức");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 19));
        jlbTitle.setOpaque(false);
        jpnTitle.add(jlbTitle);
        add(jpnTitle,BorderLayout.NORTH);
        
        JPanel jpnTable = new JPanel();
        jpnTable.setPreferredSize(new Dimension(450, 350));
        jpnTable.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        jpnTable.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        
    }
    
}
