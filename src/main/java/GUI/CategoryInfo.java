/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import BUS.CategoryBUS;
import DTO.Category;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class CategoryInfo extends JPanel {
    private JTextField jtfId;
    private JTextField jtfCategoryName;
    private JTextField jtfSearch;
    private JPanel pnTable;
    private JButton btAddCategory;
    private JButton btUpdateCategory;
    private JButton btDeleteCategory;

    private CategoryBUS categoryBUS;

    public JPanel getPnTable() {
        return pnTable;
    }

    public JButton getBtAddCategory() {
        return btAddCategory;
    }

    public JButton getBtUpdateCategory() {
        return btUpdateCategory;
    }

    public JButton getBtDeleteCategory() {
        return btDeleteCategory;
    }

    public void setPnTable(JPanel pnTable) {
        this.pnTable = pnTable;
    }

    public JTextField getJtfId() {
        return jtfId;
    }

    public void setJtfId(JTextField jtfId) {
        this.jtfId = jtfId;
    }

    public JTextField getJtfCategoryName() {
        return jtfCategoryName;
    }

    public void setJtfCategoryName(JTextField jtfCategoryName) {
        this.jtfCategoryName = jtfCategoryName;
    }

    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }

    public CategoryInfo(JPanel pnTable) {
        this.pnTable = pnTable;
        jtfId = new JTextField();
        jtfId.setEditable(false);
        jtfCategoryName = new JTextField();
        jtfSearch = new JTextField();
        categoryBUS = new CategoryBUS();
        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(480, 500));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Panel chứa thông tin danh mục
        JPanel pnInfo = new JPanel();
        pnInfo.setPreferredSize(new Dimension(450, 135));
        pnInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        pnInfo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        addLabel(pnInfo, "Mã danh mục:");
        addTextField(pnInfo, jtfId);
        addLabel(pnInfo, "Tên danh mục:");
        addTextField(pnInfo, jtfCategoryName);

        // Panel chứa các nút điều khiển
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        pnButton.setPreferredSize(new Dimension(450, 250));

        // Tìm kiếm
        JPanel jpnSearch = new JPanel();
        jpnSearch.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jtfSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jtfSearch.setColumns(15);
        jtfSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(5, 10, 5, 10)
        ));
        jpnSearch.add(jtfSearch);
        JButton jbtSearch = new JButton("Tìm kiếm");
        jbtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jbtSearch.addActionListener(e -> reloadTable(getJtfSearch().getText()));
        jpnSearch.add(jbtSearch);

        addButton(pnButton, "Thêm");
        addButton(pnButton, "Sửa");
        addButton(pnButton, "Xóa");
        pnButton.add(jpnSearch);

        add(pnInfo);
        add(pnButton);
    }

    // Thêm nút vào panel
    private void addButton(JPanel pn, String text) {
        JButton bt = new JButton(text);
        bt.setPreferredSize(new Dimension(90, 40));
        bt.setActionCommand(text);
        bt.addActionListener(e -> handleButtonAction(e.getActionCommand()));
        pn.add(bt);
    }

    // Thêm JTextField vào panel
    private void addTextField(JPanel pn, JTextField tf) {
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        tf.setPreferredSize(new Dimension(210, 40));
        pn.add(tf);
    }

    // Thêm label vào panel
    private void addLabel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(95, 40));
        label.setOpaque(false);
        panel.add(label);
    }

    // Reload bảng sau khi tìm kiếm
    private void reloadTable(String search) {
        if (pnTable instanceof CategoryTable) {
            ((CategoryTable) pnTable).reloadData(search);
        }
    }

    // Xử lý khi nhấn nút Thêm, Sửa, Xóa
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

    // Xử lý thêm danh mục mới
    private void handleAddCategory() {
        String categoryName = getJtfCategoryName().getText();
        if (categoryName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên danh mục.");
            return;
        }
        if (!checkCategoryName())        return;
        boolean isAdded = categoryBUS.addCategory(categoryName);
        if (isAdded) {
            JOptionPane.showMessageDialog(this, "Thêm danh mục thành công.");
            reloadTable(getJtfSearch().getText());
            setEmptyAllField();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm danh mục thất bại.");
        }
    }

    // Xử lý sửa danh mục
    private void handleUpdateCategory() {
        String categoryIdText = getJtfId().getText();
        String categoryName = getJtfCategoryName().getText();
        if (categoryIdText.isEmpty() || categoryName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        if (!checkCategoryName())        return;
        int categoryId = Integer.parseInt(categoryIdText);
        Category categoryToUpdate = new Category(categoryId, categoryName);
        boolean isUpdated = categoryBUS.updateCategory(categoryToUpdate);
        if (isUpdated) {
            JOptionPane.showMessageDialog(this, "Cập nhật danh mục thành công.");
            reloadTable(getJtfSearch().getText());
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật danh mục thất bại.");
        }
    }

    // Xử lý xóa danh mục
    private void handleDeleteCategory() {
        String categoryIdText = getJtfId().getText();
        if (categoryIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn danh mục cần xóa.");
            return;
        }
        int categoryId = Integer.parseInt(categoryIdText);
        if (new BUS.ProductsBUS().isCategoryUseInProduct(categoryId) ){
            JOptionPane.showMessageDialog(this, "Không thể xóa vì vẫn còn sản phẩm thuộc loại này!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa danh mục này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isDeleted = categoryBUS.deleteCategory(categoryId);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Xóa danh mục thành công.");
                reloadTable(getJtfSearch().getText());
                setEmptyAllField();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa danh mục thất bại.");
            }
        }
    }

    // Đặt các trường thông tin thành rỗng
    public void setEmptyAllField() {
        this.getJtfId().setText("");
        this.getJtfCategoryName().setText("");
    }

    // Đặt tất cả các trường không thể chỉnh sửa
    public void setEditableAllField() {
        getJtfCategoryName().setEditable(true);
    }
    
    public Boolean checkCategoryName(){
        String cName = this.getJtfCategoryName().getText().trim();
        if (cName.matches("\\d+")){ //|| !cName.matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(this, "Tên loại món ăn không hợp lệ!");
            return false;
        }
        if ((new BUS.CategoryBUS().isProductExists(cName))){
            JOptionPane.showMessageDialog(this, "Tên loại món ăn đã tồn tại!");
            return false;
        }
        
        return true;
    }
}





