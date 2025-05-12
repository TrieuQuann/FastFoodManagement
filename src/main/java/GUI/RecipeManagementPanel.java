


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

public class RecipeManagementPanel extends JPanel{
    private RecipeInfo pnRecipeInfo;
    private RecipeTable pnRecipeTable;

    public RecipeInfo getPnRecipeInfo() {
        return pnRecipeInfo;
    }

    public void setPnRecipeInfo(RecipeInfo pnRecipeInfo) {
        this.pnRecipeInfo = pnRecipeInfo;
    }

    public RecipeTable getPnRecipeTable() {
        return pnRecipeTable;
    }

    public void setPnRecipeTable(RecipeTable pnRecipeTable) {
        this.pnRecipeTable = pnRecipeTable;
    }



    public RecipeManagementPanel() {
        setPreferredSize(new Dimension(1300, 900));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        

        JPanel jpnTitle = new JPanel();
        jpnTitle.setPreferredSize(new Dimension(1100, 100));
//        jpnTitle.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        jpnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel jlbTitle = new JLabel("Thông tin Công thức");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        jlbTitle.setBorder(new EmptyBorder(10, 20, 0, 10));
        jlbTitle.setOpaque(false);
        
        jpnTitle.add(jlbTitle);
        add(jpnTitle);

        pnRecipeInfo = new RecipeInfo(null);
        pnRecipeTable = new RecipeTable(pnRecipeInfo.getPnBottomInfo());
        pnRecipeInfo.setPnTable(pnRecipeTable);

        JPanel pncon1 = new JPanel();
        pncon1.setLayout(new BorderLayout(10, 10));
        pncon1.setPreferredSize(new Dimension(1250, 600));
        
        pncon1.add(pnRecipeInfo, BorderLayout.EAST);
        pncon1.add(pnRecipeTable, BorderLayout.CENTER);
        
        add(pncon1);
    }
    
    
}
