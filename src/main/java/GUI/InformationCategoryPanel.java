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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InformationCategoryPanel extends JPanel {
    private CategoryInfo jpnInfo;
    private CategoryTable jpnTable;

    public JPanel getJpnInfo() {
        return jpnInfo;
    }

    public void setJpnInfo(CategoryInfo jpnInfo) {
        this.jpnInfo = jpnInfo;
    }

    public JPanel getJpnTable() {
        return jpnTable;
    }

    public void setJpnTable(CategoryTable jpnTable) {
        this.jpnTable = jpnTable;
    }

    public InformationCategoryPanel() {
        setPreferredSize(new Dimension(1200, 800));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

        JPanel pnTitle = new JPanel();
        pnTitle.setPreferredSize(new Dimension(1150, 60));
        JLabel jlbTitle = new JLabel("Thông tin danh mục");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 40));
//        jlbTitle.setBorder(new EmptyBorder(10, 20, 0, 10));
        jlbTitle.setOpaque(false);
        pnTitle.add(jlbTitle);
        add(pnTitle);

        jpnInfo = new CategoryInfo(null);
        jpnTable = new CategoryTable(jpnInfo);
        jpnInfo.setPnTable(jpnTable);

        add(jpnTable);  
        add(jpnInfo);

    }
    
    
    
    public static void main(String[] args) {
        InformationCategoryPanel panel = new InformationCategoryPanel();
        JFrame frame = new JFrame("Quản lý danh mục");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); 
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
    
    
}
