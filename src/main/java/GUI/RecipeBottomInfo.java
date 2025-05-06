/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.Category;
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
    
    RecipeBottomInfo(){
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
                handleAddCategory();
                break;
            case "Sửa":
                handleUpdateCategory();
                break;
            case "Xóa":
                handleDeleteCategory();
                break;
        }
    }
    
    private void handleAddCategory() {
//        String categoryName = getJtfCategoryName().getText();
//        if (categoryName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên danh mục.");
//            return;
//        }
//        if (!checkCategoryName())        return;
//        boolean isAdded = categoryBUS.addCategory(categoryName);
//        if (isAdded) {
//            JOptionPane.showMessageDialog(this, "Thêm danh mục thành công.");
//            reloadTable(getJtfSearch().getText());
//            setEmptyAllField();
//        } else {
//            JOptionPane.showMessageDialog(this, "Thêm danh mục thất bại.");
//        }
    }

    // Xử lý sửa danh mục
    private void handleUpdateCategory() {
//        String categoryIdText = getJtfId().getText();
//        String categoryName = getJtfCategoryName().getText();
//        if (categoryIdText.isEmpty() || categoryName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
//            return;
//        }
//        if (!checkCategoryName())        return;
//        int categoryId = Integer.parseInt(categoryIdText);
//        Category categoryToUpdate = new Category(categoryId, categoryName);
//        boolean isUpdated = categoryBUS.updateCategory(categoryToUpdate);
//        if (isUpdated) {
//            JOptionPane.showMessageDialog(this, "Cập nhật danh mục thành công.");
//            reloadTable(getJtfSearch().getText());
//        } else {
//            JOptionPane.showMessageDialog(this, "Cập nhật danh mục thất bại.");
//        }
    }

    // Xử lý xóa danh mục
    private void handleDeleteCategory() {
//        String categoryIdText = getJtfId().getText();
//        if (categoryIdText.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn danh mục cần xóa.");
//            return;
//        }
//        int categoryId = Integer.parseInt(categoryIdText);
//        if (new BUS.ProductsBUS().isCategoryUseInProduct(categoryId) ){
//            JOptionPane.showMessageDialog(this, "Không thể xóa vì vẫn còn sản phẩm thuộc loại này!");
//            return;
//        }
//        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa danh mục này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            boolean isDeleted = categoryBUS.deleteCategory(categoryId);
//            if (isDeleted) {
//                JOptionPane.showMessageDialog(this, "Xóa danh mục thành công.");
//                reloadTable(getJtfSearch().getText());
//                setEmptyAllField();
//            } else {
//                JOptionPane.showMessageDialog(this, "Xóa danh mục thất bại.");
//            }
//        }
    }

    
}
