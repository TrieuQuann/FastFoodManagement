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

/**
 *
 * @author LENOVO
 */
public class RecipeInfo extends JPanel{
    private JPanel pnTopInfo;
    private JPanel pnBottomInfo;

    public RecipeInfo() {
        this.pnTopInfo = new RecipeTopInfo();
        this.pnBottomInfo = new RecipeBottomInfo();
        initRecipeInfo();
    }

    public JPanel getPnTopInfo() {
        return pnTopInfo;
    }

    public void setPnTopInfo(JPanel pnTopInfo) {
        this.pnTopInfo = pnTopInfo;
    }

    public JPanel getPnBottomInfo() {
        return pnBottomInfo;
    }

    public void setPnBottomInfo(JPanel pnBottomInfo) {
        this.pnBottomInfo = pnBottomInfo;
    }
    
    private void initRecipeInfo(){
        setPreferredSize(new Dimension(600, 700));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        
        pnTopInfo = new RecipeTopInfo();
        add(pnTopInfo);
        
        
        pnBottomInfo = new RecipeBottomInfo();
        add(pnBottomInfo);
        
    }
}
