package GUI;

import BUS.SuppliersBUS;
import DTO.Suppliers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SupplierManagementPanel extends JPanel {
    private SuppliersBUS bus = new SuppliersBUS();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public SupplierManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ========== PANEL HEADER ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("QUẢN LÝ NHÀ CUNG CẤP", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ========== CONTROL PANEL ==========
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        
        JButton btnAdd = new JButton("Thêm nhà cung cấp");
        btnAdd.addActionListener(this::handleAddSupplier);
        controlPanel.add(btnAdd);

        JButton btnEdit = new JButton("Sửa nhà cung cấp");
        btnEdit.addActionListener(this::handleEditSupplier);
        controlPanel.add(btnEdit);

        
        JButton btnDelete = new JButton("Xóa nhà cung cấp");
        btnDelete.addActionListener(this::handleDeleteSupplier);
        controlPanel.add(btnDelete);

        headerPanel.add(controlPanel);

        // ========== SEARCH PANEL ==========
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã NCC", "Tên NCC", "Email", "Địa chỉ", "Số điện thoại"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchSuppliers());

        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        headerPanel.add(searchPanel);

        add(headerPanel, BorderLayout.NORTH);

        // ========== TABLE ==========
        model = new DefaultTableModel(
            new String[]{"Mã NCC", "Tên NCC", "Email", "Địa chỉ", "Số điện thoại"},
            0
        );
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void handleAddSupplier(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm nhà cung cấp mới");
        dialog.setLayout(new BorderLayout());

        // ========== INPUT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));

        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtPhone = new JTextField();

        inputPanel.add(new JLabel("Tên NCC (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Email (*):"));
        inputPanel.add(txtEmail);
        inputPanel.add(new JLabel("Địa chỉ (*):"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Số điện thoại (*):"));
        inputPanel.add(txtPhone);

        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Lưu nhà cung cấp");
        btnSave.addActionListener(evt -> {
            try {
                // Validate input
                if (txtName.getText().isEmpty() || txtEmail.getText().isEmpty()
                        || txtAddress.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                // Create Supplier object
                Suppliers newSupplier = new Suppliers(
                    0,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPhone.getText()
                );

                // Call BUS to add supplier
                if (bus.addSupplier(newSupplier)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
                    loadData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!");
                ex.printStackTrace();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

        private void handleEditSupplier(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa!");
            return;
        }
    
        // Get the selected supplier's data
        int supplierId = (int) model.getValueAt(selectedRow, 0);
        String currentName = (String) model.getValueAt(selectedRow, 1);
        String currentEmail = (String) model.getValueAt(selectedRow, 2);
        String currentAddress = (String) model.getValueAt(selectedRow, 3);
        String currentPhone = (String) model.getValueAt(selectedRow, 4);
    
        // Create a dialog for editing
        JDialog dialog = new JDialog();
        dialog.setTitle("Sửa thông tin nhà cung cấp");
        dialog.setLayout(new BorderLayout());
    
        // ========== INPUT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));
    
        JTextField txtName = new JTextField(currentName);
        JTextField txtEmail = new JTextField(currentEmail);
        JTextField txtAddress = new JTextField(currentAddress);
        JTextField txtPhone = new JTextField(currentPhone);
    
        inputPanel.add(new JLabel("Tên NCC (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Email (*):"));
        inputPanel.add(txtEmail);
        inputPanel.add(new JLabel("Địa chỉ (*):"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Số điện thoại (*):"));
        inputPanel.add(txtPhone);
    
        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Lưu thay đổi");
        btnSave.addActionListener(evt -> {
            try {
                // Validate input
                if (txtName.getText().isEmpty() || txtEmail.getText().isEmpty()
                        || txtAddress.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }
    
                // Create updated Supplier object
                Suppliers updatedSupplier = new Suppliers(
                    supplierId,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    txtPhone.getText()
                );
    
                // Call BUS to update supplier
                if (bus.updateSupplier(updatedSupplier)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thành công!");
                    loadData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!");
                ex.printStackTrace();
            }
        });
    
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void handleDeleteSupplier(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa nhà cung cấp này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int supplierId = (int) model.getValueAt(selectedRow, 0);
            if (bus.deleteSupplier(supplierId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        List<Suppliers> suppliers = bus.getAllSuppliers();

        for (Suppliers supplier : suppliers) {
            model.addRow(new Object[]{
                supplier.getSupplier_id(),
                supplier.getSupplier_name(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getPhone()
            });
        }
    }

    private void searchSuppliers() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();

        String column = switch (searchType) {
            case "Mã NCC" -> "supplier_id";
            case "Tên NCC" -> "supplier_name";
            case "Email" -> "email";
            case "Địa chỉ" -> "address";
            case "Số điện thoại" -> "phone";
            default -> "";
        };

        model.setRowCount(0);
        List<Suppliers> result = bus.searchSuppliers(column, keyword);

        for (Suppliers supplier : result) {
            model.addRow(new Object[]{
                supplier.getSupplier_id(),
                supplier.getSupplier_name(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getPhone()
            });
        }
    }
}
//hihi