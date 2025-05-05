/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InformationProductPanel extends JPanel {
    private ProductInfo jpnInfo;
    private ProductTable jpnTable;

    public JPanel getJpnInfo() {
        return jpnInfo;
    }

    public void setJpnInfo(ProductInfo jpnInfo) {
        this.jpnInfo = jpnInfo;
    }

    public JPanel getJpnTable() {
        return jpnTable;
    }

    public void setJpnTable(ProductTable jpnTable) {
        this.jpnTable = jpnTable;
    }

    public InformationProductPanel() {
        setPreferredSize(new Dimension(1000, 800));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new BorderLayout(10, 10));

        JLabel jlbTitle = new JLabel("Thông tin sản phẩm");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        jlbTitle.setBorder(new EmptyBorder(10, 20, 0, 10));
        jlbTitle.setOpaque(false);
        add(jlbTitle, BorderLayout.NORTH);

        jpnInfo = new ProductInfo(null); 
        jpnTable = new ProductTable(jpnInfo);
        jpnInfo.setPnTable(jpnTable); 

        add(jpnInfo, BorderLayout.CENTER);
        add(jpnTable, BorderLayout.SOUTH);
    }
}
