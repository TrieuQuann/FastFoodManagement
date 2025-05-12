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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class CreateOrderPanel extends JPanel{
    private JPanel jpnTopInfo;
    private JPanel jpnBottomInfo;
    private JPanel jpnTable;

    public CreateOrderPanel() {
        setPreferredSize(new Dimension(1400, 900));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER,50,50));

        JPanel pnTitle = new JPanel();
        pnTitle.setPreferredSize(new Dimension(1150, 90));
        JLabel jlbTitle = new JLabel("Tạo hóa đơn");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        jlbTitle.setOpaque(false);
        pnTitle.add(jlbTitle);
        add(pnTitle);

        jpnTable = new OrderDetailTable();
        jpnTopInfo = new OrderDetailTopInfo(jpnTable);
        jpnBottomInfo = new OrderDetailBottomInfo(jpnTable);
        
        
        JPanel pncon1 = new JPanel();
        pncon1.setPreferredSize(new Dimension(1190, 700));
//        pncon1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        pncon1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel pncon2 = new JPanel();
        pncon2.setPreferredSize(new Dimension(530, 690));
        pncon2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        pncon2.setLayout(new FlowLayout(FlowLayout.CENTER));
        pncon2.add(jpnTopInfo);
        pncon2.add(jpnBottomInfo);
        
        pncon1.add(pncon2);
        pncon1.add(jpnTable);
        
        add(pncon1);
    }
    
}
