/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
public class RecipeBottomInfo extends JPanel{
    
    RecipeBottomInfo(){
        initRecipeBottomInfo();
    }
    
    private void initRecipeBottomInfo(){
        setPreferredSize(new Dimension(450, 300));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
    }
    
}
