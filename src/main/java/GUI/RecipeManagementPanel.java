


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
    private JPanel pnRecipeInfo;
    private JPanel pnRecipeTable;

    public JPanel getPnRecipeInfo() {
        return pnRecipeInfo;
    }

    public void setPnRecipeInfo(JPanel pnRecipeInfo) {
        this.pnRecipeInfo = pnRecipeInfo;
    }

    public JPanel getPnRecipeTable() {
        return pnRecipeTable;
    }

    public void setPnRecipeTable(JPanel pnRecipeTable) {
        this.pnRecipeTable = pnRecipeTable;
    }

    public RecipeManagementPanel() {
        setPreferredSize(new Dimension(1000, 800));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        setLayout(new BorderLayout(10, 10));

        JPanel jpnTitle = new JPanel();
        jpnTitle.setPreferredSize(new Dimension(900, 70));
        jpnTitle.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        jpnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel jlbTitle = new JLabel("Thông tin Công thức");
        jlbTitle.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        jlbTitle.setBorder(new EmptyBorder(10, 20, 0, 10));
        jlbTitle.setOpaque(false);
        
        jpnTitle.add(jlbTitle);
        add(jpnTitle, BorderLayout.NORTH);

        pnRecipeInfo = new RecipeInfo();
        pnRecipeTable = new RecipeTable();
//        pnRecipeInfo.setPnTable(pnRecipeTable); 

        add(pnRecipeInfo, BorderLayout.EAST);
        add(pnRecipeTable, BorderLayout.CENTER);
    }
    
    
}
