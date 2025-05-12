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
        setPreferredSize(new Dimension(1500, 900));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel jlbTitle = new JLabel("Thông tin sản phẩm");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        jlbTitle.setBorder(new EmptyBorder(10, 20, 0, 10));
        jlbTitle.setOpaque(false);
        add(jlbTitle);

        JPanel pncon1 = new JPanel();
        pncon1.setLayout(new BorderLayout(10, 10));
        pncon1.setPreferredSize(new Dimension(1390, 850));
        jpnInfo = new ProductInfo(null); 
        jpnTable = new ProductTable(jpnInfo);
        jpnInfo.setPnTable(jpnTable); 

        pncon1.add(jpnInfo, BorderLayout.CENTER);
        pncon1.add(jpnTable, BorderLayout.SOUTH);
        
        add(pncon1);
    }
}
