/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.ProductsBUS;
import DTO.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class ProductTable extends JPanel {
    private JPanel jpnInfo;
    private ProductTableModel model;
    private JTable jTable; 

    public ProductTableModel getModel() {
        return model;
    }

    public ProductTable(JPanel jpnInfo) {
        this.jpnInfo = jpnInfo;
        this.model = new ProductTableModel(); 
        initProductTable();
    }

    public void reloadData(String search) {
        this.model = new ProductTableModel(search); 
        jTable.setModel(this.model);           
        revalidate();
        repaint();
    }

    private void initProductTable() {
        setPreferredSize(new Dimension(950, 550));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new BorderLayout());

        jTable = new JTable(this.model);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jTable.setRowHeight(25);

        JTableHeader header = jTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(204, 229, 255)); 
        header.setForeground(Color.BLACK);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane, BorderLayout.CENTER);

        // Xử lý sự kiện click vào hàng trong bảng
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    int id = (int) jTable.getValueAt(row, 0);
                    showInfo(id);
                }
            }
        });
    }

    private void showInfo(int id) {
        if (jpnInfo instanceof ProductInfo) {
            ProductInfo infoPanel = (ProductInfo) jpnInfo;
            Product data = getModel().getProductById(id);

            infoPanel.getJtfId().setText(data.getProductId()+"");
            infoPanel.getJtfName().setText(data.getProductName());
            String cName = new BUS.CategoryBUS().getNameById(data.getCategoryId());
            infoPanel.getJcbCategory().setSelectedItem(cName);
            infoPanel.getJtfPrice().setText(formatCurrency(data.getPrice()));
            infoPanel.getJtfQuantity().setText(String.valueOf(data.getExpectedQuantity()));
            infoPanel.setImagePath(data.getImage());
            infoPanel.getBtAddFood().setVisible(false);
            infoPanel.getBtUpdateFood().setVisible(false);
            infoPanel.getBtChooseImage().setVisible(false);
            infoPanel.setEditableAllField();
        }
    }
    
    private String formatCurrency(Double amount) {
        if (amount == null) {
            return "0 VNĐ";
        }
        return String.format("%,.0f VNĐ", amount);
    }
}