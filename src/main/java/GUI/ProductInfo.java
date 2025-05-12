/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class ProductInfo extends JPanel {
    private JTextField jtfId;
    private JTextField jtfName;
    private JComboBox<String> jcbCategory;
    private JTextField jtfPrice;
    private JTextField jtfQuantity;
    private JTextField jtfSearch;
    private JPanel pnTable;
    private String imagePath;
    private JLabel imageLabel;
    private JButton btChooseImage;
    private JButton btUpdateFood;
    private JButton btAddFood;
    
    public JPanel getPnTable() {
        return pnTable;
    }

    public JButton getBtChooseImage() {
        return btChooseImage;
    }

    public JButton getBtUpdateFood() {
        return btUpdateFood;
    }

    public JButton getBtAddFood() {
        return btAddFood;
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
    
    public JTextField getJtfName() {
        return jtfName;
    }
    public void setJtfName(JTextField jtfName) {
        this.jtfName = jtfName;
    }
    public JComboBox<String> getJcbCategory() {
        return jcbCategory;
    }
    public void setJcbCategory(JComboBox<String> jcbCategory) {
        this.jcbCategory = jcbCategory;
    }
    public JTextField getJtfPrice() {
        return jtfPrice;
    }
    public void setJtfPrice(JTextField jtfPrice) {
        this.jtfPrice = jtfPrice;
    }
    public JTextField getJtfQuantity() {
        return jtfQuantity;
    }
    public void setJtfQuantity(JTextField jtfQuantity) {
        this.jtfQuantity = jtfQuantity;
    }
    public JTextField getJtfSearch() {
        return jtfSearch;
    }
    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        loadImage(); 
    }
    
    public ProductInfo(JPanel pnTable) {
        this.pnTable = pnTable;
        jtfId = new JTextField();
        jtfId.setEditable(false);
        jtfName = new JTextField();
        jtfName.setEditable(false);
        jcbCategory = new JComboBox<>(new BUS.CategoryBUS().getAllCategoryName());
        jcbCategory.setEnabled(false);
        jcbCategory.setSelectedItem(null);
        jtfPrice = new JTextField();
        jtfPrice.setEditable(false);
        jtfQuantity = new JTextField();
        jtfQuantity.setEditable(false);
        this.imagePath = imagePath;
        initUI();
    }
    
    private void initUI() {
        setPreferredSize(new Dimension(950, 320));
//        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

//===================================Panel chứa hình ảnh==========================
        JPanel pnImg = new JPanel();
        pnImg.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        pnImg.setPreferredSize(new Dimension(220, 220));
        pnImg.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 153), 2));
        imageLabel = new JLabel();
        pnImg.add(imageLabel);
        add(pnImg);

//=================================Panel chứa thông tin sản phẩm=====================
        JPanel pnInfo = new JPanel();
        pnInfo.setPreferredSize(new Dimension(730, 220));
        pnInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 30,10));
        pnInfo.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 153), 2));
//        pnInfo.setBackground(new Color(255, 204, 204));

        addLabel(pnInfo, "Mã món:");
        addTextField(pnInfo, jtfId);
        addLabel(pnInfo, "Tên món:");
        addTextField(pnInfo, jtfName);
        addLabel(pnInfo, "Giá:");
        addTextField(pnInfo, jtfPrice);
        addLabel(pnInfo, "Loại:");
        addComboBox(pnInfo, jcbCategory);
        addLabel(pnInfo, "Số lượng còn:");
        addTextField(pnInfo, jtfQuantity);
        
//=======================Thêm Panel chứa nút duyệt ảnh và lưu thay đổi==================
        JPanel pnBonus = new JPanel();
        pnBonus.setPreferredSize(new Dimension(590, 50));
        pnBonus.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        
        //===== nút DUYỆT ẢNH =========
        btChooseImage = new JButton("Chọn ảnh");
        btChooseImage.setVisible(false);
        btChooseImage.setPreferredSize(new Dimension(90, 30));
        btChooseImage.setBackground(new Color(30, 144, 255)); 
        btChooseImage.setForeground(Color.WHITE); 
        btChooseImage.setFocusPainted(false); 
        btChooseImage.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btChooseImage.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btChooseImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btChooseImage.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btChooseImage.setBackground(new Color(30, 144, 255));
            }
        });
        btChooseImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Thư mục đích lưu ảnh
                    String destFolder = System.getProperty("user.dir") + "/image/products/";
                    File destDir = new File(destFolder);
                    if (!destDir.exists()) {
                        destDir.mkdirs();
                    }

                    // Copy ảnh vào thư mục
                    String fileName = selectedFile.getName();
                    File destFile = new File(destDir, fileName);
                    Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Cập nhật path và load ảnh
                    String relativePath = "/image/products/" + fileName;
                    setImagePath(relativePath);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Không thể lưu ảnh: " + ex.getMessage());
                }
            }
        });
        
        btUpdateFood = new JButton("Lưu thay đổi");
        btUpdateFood.setBackground(new Color(30, 144, 255)); 
        btUpdateFood.setPreferredSize(new Dimension(110, 30));
        btUpdateFood.setForeground(Color.WHITE); 
        btUpdateFood.setFocusPainted(false); 
        btUpdateFood.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btUpdateFood.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btUpdateFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btUpdateFood.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btUpdateFood.setBackground(new Color(30, 144, 255));
            }
        });
        btUpdateFood.addActionListener(e -> handleLuuThayDoi());
        btUpdateFood.setVisible(false);
        
        
        btAddFood = new JButton("Thêm món ăn");
        btAddFood.setPreferredSize(new Dimension(110, 30));
        btAddFood.setBackground(new Color(30, 144, 255)); 
        btAddFood.setForeground(Color.WHITE); 
        btAddFood.setFocusPainted(false); 
        btAddFood.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btAddFood.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        btAddFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btAddFood.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btAddFood.setBackground(new Color(30, 144, 255));
            }
        });
        btAddFood.addActionListener(e -> handleThemMonAn());
        btAddFood.setVisible(false);
        
        pnBonus.add(btChooseImage);
        pnBonus.add(btUpdateFood);
        pnBonus.add(btAddFood);
        pnInfo.add(pnBonus);
        add(pnInfo);
        
//================================ Panel chứa các nút thao tác========================
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        pnButton.setPreferredSize(new Dimension(750, 60));

        //=========== tạo pn tìm kiếm =======
        JPanel jpnSearch = new JPanel();
        jpnSearch.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        jtfSearch = new JTextField();
        jtfSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jtfSearch.setColumns(15);
        jtfSearch.setBorder(new EmptyBorder(10, 10, 10, 10));
        jtfSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        jtfSearch.setOpaque(false);
        jpnSearch.add(jtfSearch);
        JButton jbtSearch = new JButton("Tìm kiếm");
        jbtSearch.setBorder(new EmptyBorder(7, 7, 7, 7));
        jbtSearch.setPreferredSize(new Dimension(100, 40));
//        jbtSearch.setOpaque(false);
        jbtSearch.setBackground(new Color(30, 144, 255)); 
        jbtSearch.setForeground(Color.WHITE); 
        jbtSearch.setFocusPainted(false); 
        jbtSearch.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        jbtSearch.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        jbtSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                String search = getJtfSearch().getText();
                reloadTable(search);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jbtSearch.setBackground(new Color(0, 120, 215));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                jbtSearch.setBackground(new Color(30, 144, 255));
            }
        });
     
        jpnSearch.add(jbtSearch);
        pnButton.add(jpnSearch);

        addButton(pnButton, "Thêm");
        addButton(pnButton, "Sửa");
        addButton(pnButton, "Xóa");
        add(pnButton);
    }

//================================== Thêm nút vào panel===============================
    private void addButton(JPanel pn, String text) {
        JButton bt = new JButton(text);
        bt.setPreferredSize(new Dimension(90, 40));
        bt.setActionCommand(text);
        bt.setBackground(new Color(30, 144, 255)); 
        bt.setForeground(Color.WHITE); 
        bt.setFocusPainted(false); 
        bt.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        bt.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1));
        bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt.setBackground(new Color(0, 120, 215));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt.setBackground(new Color(30, 144, 255));
            }
        });
        bt.addActionListener(e -> handleButtonAction(e.getActionCommand()));
        pn.add(bt);
    }
    
//============================= Thêm JTextField vào panel=====================
    private void addTextField(JPanel pn, JTextField tf) {
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        tf.setPreferredSize(new Dimension(200, 40));
        pn.add(tf);
    }
    
//================================= Thêm label vào panel========================
    private void addLabel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(95, 40));
        label.setOpaque(false);
        panel.add(label);
    }
    
    private void reloadTable(String search){
        if (pnTable instanceof ProductTable) {
            ((ProductTable) pnTable).reloadData(search);
        }
    }
    
    private void addComboBox(JPanel pn, JComboBox<String> comboBox) {
        comboBox.setPreferredSize(new Dimension(210, 40));
        pn.add(comboBox);
    }

    private void loadImage() {
        if (imagePath != null) {
            File imageFile = new File(System.getProperty("user.dir") + imagePath);
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
                Image image = icon.getImage();
                Image scaled = image.getScaledInstance(218, 218, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaled);
                imageLabel.setIcon(scaledIcon);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy ảnh: " + imagePath);
            }
        }
    }
    
//================================= xử lí nút Lưu thay đổi =============================
    private void handleLuuThayDoi(){
        System.out.println("Lưu thay đổi.");
        if (!checkAllInputField())      return;
        int idInput = Integer.parseInt(getJtfId().getText().trim());
        String pNameInput = getJtfName().getText().trim();
        String imagePathInput = getImagePath();
        String cNameInput = getJcbCategory().getSelectedItem().toString();
        
        Boolean resultUpdate = new BUS.ProductsBUS().updateProduct(idInput, pNameInput, imagePathInput, cNameInput);
        if (resultUpdate) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!");
            return;
        }
        setEditableAllField();
        reloadTable(getJtfSearch().getText());
    }
    
    
//==================================== xử lí nút Thêm món ăn =============================
    private void handleThemMonAn(){
        System.out.println("Thêm món ăn.");
        if (!checkAllInputField())      return;
        String pNameInputAdd = getJtfName().getText().trim();
        String imagePathInputAdd = getImagePath();
        String cNameInputAdd = getJcbCategory().getSelectedItem().toString();
        
        if (new BUS.ProductsBUS().isProductExists(pNameInputAdd)) {
        JOptionPane.showMessageDialog(this, "Tên món ăn đã tồn tại!");
            return;
        }
        Boolean resultAdd = new BUS.ProductsBUS().addProduct(pNameInputAdd, imagePathInputAdd, cNameInputAdd);
        if (resultAdd) {
            JOptionPane.showMessageDialog(this, "Thêm món ăn thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm món ăn thất bại!");
            return;
        }
        setEditableAllField();
        reloadTable(getJtfSearch().getText());
    }
    

//================================= Xử lý khi nhấn nút Thêm Sửa Xóa ======-================
    private void handleButtonAction(String action) {
        switch (action) {
            case "Thêm":
                System.out.println("Thêm");
                setEmptyAllField();
                setEditableAllField();
                int idDuKien = new BUS.ProductsBUS().getNextProductId();
                getJtfId().setText(String.valueOf(idDuKien));
                getBtChooseImage().setVisible(true);
                getBtAddFood().setVisible(true);
                getJtfName().setEditable(true);
                getJcbCategory().setEnabled(true);
                break;
            case "Sửa":
                System.out.println("Sửa");
                String idText = this.getJtfId().getText();
                if (idText == null || idText.trim().isEmpty() || this.getBtAddFood().isVisible()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn món cần sửa!");
                    return;
                }
                int idFood;
                try{
                    idFood = Integer.parseInt(idText.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "ID không hợp lệ!");
                    return;
                }
                getBtChooseImage().setVisible(true);
                getBtUpdateFood().setVisible(true);
                getJtfName().setEditable(true);
                getJcbCategory().setEnabled(true);
//                JOptionPane.showMessageDialog(this, "Bạn có thể sửa tên, loại và hình ảnh món ăn.");
                break;
            case "Xóa":
                System.out.println("Xóa");
                String idTextDel = this.getJtfId().getText();
                if (idTextDel == null || idTextDel.trim().isEmpty() || this.getBtAddFood().isVisible()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn món cần xóa!");
                    return;
                }
                int idFoodDel;
                try{
                    idFoodDel = Integer.parseInt(idTextDel.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "ID không hợp lệ!");
                    return;
                }
                int xacnhanxoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if ((xacnhanxoa == JOptionPane.YES_OPTION) && (new BUS.ProductsBUS().deleteProduct(idFoodDel))){
                    JOptionPane.showMessageDialog(this, "Đã xóa món ăn thành công.");
                    setEmptyAllField();
                    reloadTable(getJtfSearch().getText());
                } 
                else    JOptionPane.showMessageDialog(this, "Xóa món ăn thất bại!");
                
                break;
        }
    }
    
    public void setEmptyAllField(){
        this.getJtfId().setText("");
        this.getJtfName().setText("");
        this.getJcbCategory().setSelectedItem(null);
        this.getJtfPrice().setText("");
        this.getJtfQuantity().setText("");
        this.setImagePath("");
    }
    
    public void setEditableAllField() {
        if (getBtChooseImage().isVisible())     getBtChooseImage().setVisible(false);
        if (getBtUpdateFood().isVisible())      getBtUpdateFood().setVisible(false);
        if (getBtAddFood().isVisible())         getBtAddFood().setVisible(false);
        if (getJtfName().isEditable())          getJtfName().setEditable(false);
        if (getJcbCategory().isEnabled())       getJcbCategory().setEnabled(false);
    }
    
    public boolean checkAllInputField() {
        String name = this.getJtfName().getText().trim();
        Object selectedCategory = this.getJcbCategory().getSelectedItem();
        String pathImg = this.getImagePath().trim();
        String idText = getJtfId().getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID không được để trống!");
            return false;
        }

        if (!idText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "ID phải là số!");
            return false;
        }
    
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên món ăn không được để trống!");
            return false;
        }
        
        if (name.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Tên món ăn không được chỉ là số!");
            return false;
        }

        if (selectedCategory == null || selectedCategory.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Loại sản phẩm không được để trống!");
            return false;
        }

        if (pathImg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ảnh món ăn không được để trống!");
            return false;
        }
        return true;
    }
}

