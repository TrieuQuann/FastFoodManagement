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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author LENOVO
 */
public class RecipeTable extends JPanel {
    private JPanel jpnBottomInfo;
    private RecipeTableModel model;
    private JTable jTable;

    public JTextField getJtfTongTien() {
        return jtfTongTien;
    }

    public void setJtfTongTien(JTextField jtfTongTien) {
        this.jtfTongTien = jtfTongTien;
    }
    private JTextField jtfTongTien;

    public RecipeTableModel getModel() {
        return model;
    }

    RecipeTable(JPanel jpnBottomInfo) {
        this.model = new RecipeTableModel();
        this.jpnBottomInfo = jpnBottomInfo;
        initRecipeTable();
    }

    RecipeTable() {
        this.model = new RecipeTableModel(); 
        initRecipeTable();
    }

    private void initRecipeTable() {
        setPreferredSize(new Dimension(510, 700));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new BorderLayout());

        JPanel jpnTitle = new JPanel();
        jpnTitle.setPreferredSize(new Dimension(500, 50));
        jpnTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel jlbTitle = new JLabel("Chi tiết công thức");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 19));
        jlbTitle.setOpaque(false);
        jpnTitle.add(jlbTitle);
        add(jpnTitle, BorderLayout.NORTH);

        JPanel jnContainer = new JPanel();
        jnContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JPanel jpnTable = new JPanel();
        jpnTable.setPreferredSize(new Dimension(500, 550));
//        jpnTable.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        jpnTable.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        jTable = new JTable(model); 
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jpnTable.add(jScrollPane, BorderLayout.CENTER);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    int id = new BUS.InventoryBUS().getIdByName((String) jTable.getValueAt(row, 1));
                    showInfo(id);
                }
            }
        });

        jnContainer.add(jpnTable);
        add(jnContainer, BorderLayout.CENTER);

        JPanel jpnTotalPrice = new JPanel();
        jpnTotalPrice.setPreferredSize(new Dimension(450, 60));
        jpnTotalPrice.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JLabel jlbTotalPrice = new JLabel("Tổng chi phí: ");
        jlbTotalPrice.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jlbTotalPrice.setOpaque(false);
        jpnTotalPrice.add(jlbTotalPrice);
        jtfTongTien = new JTextField();
        jtfTongTien.setEditable(false);
        jtfTongTien.setVisible(false);
        jtfTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jpnTotalPrice.add(jtfTongTien);
        add(jpnTotalPrice, BorderLayout.SOUTH);
    }

    public void reloadData() {
        model.reloadData(); 
        revalidate(); 
        repaint(); 
    }
    
    public void reloadData(int pid) {
        model = new RecipeTableModel(pid);
        jTable.setModel(this.model);
        revalidate(); 
        repaint(); 
    }

    private void showInfo(int id) {
        System.out.println("Món ăn ID: " + id);
    }
}
