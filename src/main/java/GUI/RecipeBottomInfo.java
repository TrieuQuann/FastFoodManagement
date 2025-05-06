/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.Category;
import DTO.Recipe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class RecipeBottomInfo extends JPanel{
    private JComboBox<String> jcbInven;
    private JTextField jtfQuantity;
    private JPanel pnTop;

    public JComboBox<String> getJcbInven() {
        return jcbInven;
    }

    public void setJcbInven(JComboBox<String> jcbInven) {
        this.jcbInven = jcbInven;
    }

    public JTextField getJtfQuantity() {
        return jtfQuantity;
    }

    public void setJtfQuantity(JTextField jtfQuantity) {
        this.jtfQuantity = jtfQuantity;
    }
    
    RecipeBottomInfo(JPanel pnTop){
        this.pnTop = pnTop;
        initRecipeBottomInfo();
    }
    
    private void initRecipeBottomInfo(){
        setPreferredSize(new Dimension(450, 245));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        setLayout(new FlowLayout(FlowLayout.LEFT,30,30));
        
        JLabel jlbInven = new JLabel("Chọn nguyên liệu:");
        jlbInven.setPreferredSize(new Dimension(115, 40));
        jlbInven.setOpaque(false);
        JLabel jlbQuantity = new JLabel("Nhập số lượng:");
        jlbQuantity.setPreferredSize(new Dimension(115, 40));
        jlbQuantity.setOpaque(false);
        
        jcbInven = new JComboBox<String> ();
        jcbInven = new JComboBox<>(new BUS.InventoryBUS().getAllInventoryNames());
        jcbInven.setPreferredSize(new Dimension(200, 40));
        
        jtfQuantity =new JTextField();
        jtfQuantity.setPreferredSize(new Dimension(200, 40));
        
        
        
        add(jlbInven);
        add(jcbInven);
        add(jlbQuantity);
        add(jtfQuantity);
        addButton(this, "Thêm");
        addButton(this, "Sửa");
        addButton(this, "Xóa");

    }
    
    private void addButton(JPanel pn, String text) {
        JButton bt = new JButton(text);
        bt.setPreferredSize(new Dimension(90, 40));
        bt.setActionCommand(text);
        bt.addActionListener(e -> handleButtonAction(e.getActionCommand()));
        pn.add(bt);
    }
    
    private void handleButtonAction(String action) {
        switch (action) {
            case "Thêm":
                handleAddRecipe();
                break;
            case "Sửa":
                handleUpdateRecipe();
                break;
            case "Xóa":
                handleDeleteRecipe();
                break;
        }
    }
    
    
    //=======================THÊM=================
    private void handleAddRecipe() {
        if (pnTop instanceof RecipeTopInfo) {
            RecipeTopInfo topInfo = (RecipeTopInfo) pnTop;

            String pName = topInfo.getJcbFood().getSelectedItem().toString();
            if (pName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải chọn món ăn để thêm!");
                return;
            }
            int pId = new BUS.ProductsBUS().getIdByName(pName);

            String iName = getJcbInven().getSelectedItem().toString();
            if (iName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải chọn nguyên liệu để thêm!");
                return;
            }
            int iId = new BUS.InventoryBUS().getIdByName(iName);

            if (new BUS.RecipeBUS().hasInven(pId, iId)) {
                JOptionPane.showMessageDialog(this, "Nguyên liệu này đã tồn tại trong công thức!");
                return;
            }

            String quantityStr = jtfQuantity.getText().trim();
            if (quantityStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
                return;
            }

            float quantity;
            try {
                quantity = Float.parseFloat(quantityStr);
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là một số hợp lệ.");
                return;
            }

            Double unitPrice = new BUS.InventoryBUS().getPriceById(iId);
            double totalPrice = unitPrice * quantity;

            Recipe recipe = new Recipe(pId, iId, totalPrice, quantity);
            if (new BUS.RecipeBUS().addRecipe(recipe)) {
                JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thành công.");
                topInfo.getPnTable().reloadData(pId);
                jcbInven.setSelectedIndex(0);
                jtfQuantity.setText("");
                topInfo.handleChoose2();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nguyên liệu thất bại!");
        }

        } else {
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể xác định panel trên.");
        }
    }


    
    
    // ============================Xử lý SỬA danh mục======================
    private void handleUpdateRecipe() {
        if (pnTop instanceof RecipeTopInfo) {
            RecipeTopInfo topInfo = (RecipeTopInfo) pnTop;
            String pName = topInfo.getJcbFood().getSelectedItem().toString();
            if (pName.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải chọn món ăn để sửa!");
                return;
            }
            int pId = new BUS.ProductsBUS().getIdByName(pName);
            String iName = getJcbInven().getSelectedItem().toString();
            if (iName.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải chọn nguyên liệu để sửa!");
                return;
            }
            int iId = new BUS.InventoryBUS().getIdByName(iName);
            
            if (!(new BUS.RecipeBUS().hasInven(pId, iId))){
                JOptionPane.showMessageDialog(this, "Nguyên liệu này chưa có trong công thức!");
                return;
            }
            else{
                if (jtfQuantity.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
                    return;
                }
                try {
                    float quantity = Float.parseFloat(jtfQuantity.getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là một số hợp lệ.");
                    return;
                }
                
                Float soluong = Float.parseFloat(jtfQuantity.getText());
                Double totalPrice = new BUS.InventoryBUS().getPriceById(iId);
                DTO.Recipe r = new Recipe(pId, iId, totalPrice, soluong);
                if (new BUS.RecipeBUS().updateRecipe(r)){
                    JOptionPane.showMessageDialog(this, "Sửa nguyên liệu thành công.");
                    topInfo.getPnTable().reloadData(pId);
                    jcbInven = new JComboBox<>(new BUS.InventoryBUS().getAllInventoryNames());
                    jcbInven.setSelectedIndex(0);
                    getJtfQuantity().setText("");
                    topInfo.handleChoose2();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Sửa nguyên liệu thất bại!");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Lỗi!");
            return;
        }
    }

    private void handleDeleteRecipe() {
        if (pnTop instanceof RecipeTopInfo) {
            RecipeTopInfo topInfo = (RecipeTopInfo) pnTop;
            String pName = topInfo.getJcbFood().getSelectedItem().toString();
            if (pName.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải chọn món ăn để xóa!");
                return;
            }
            int pId = new BUS.ProductsBUS().getIdByName(pName);
            String iName = getJcbInven().getSelectedItem().toString();
            if (iName.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải chọn nguyên liệu để xóa!");
                return;
            }
            int iId = new BUS.InventoryBUS().getIdByName(iName);
            
            if (!(new BUS.RecipeBUS().hasInven(pId, iId))){
                JOptionPane.showMessageDialog(this, "Nguyên liệu này chưa có trong công thức!");
                return;
            }
            else{
                if (new BUS.RecipeBUS().deleteRecipe(pId, iId)){
                    JOptionPane.showMessageDialog(this, "Xóa nguyên liệu thành công.");
                    topInfo.getPnTable().reloadData(pId);
                    jcbInven = new JComboBox<>(new BUS.InventoryBUS().getAllInventoryNames());
                    jcbInven.setSelectedIndex(0);
                    getJtfQuantity().setText("");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Xóa nguyên liệu thất bại!");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Lỗi!");
            return;
        }
    }

    
}
