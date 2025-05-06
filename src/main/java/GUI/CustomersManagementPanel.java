package GUI;

import BUS.CustomersBUS;
import DTO.Customers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomersManagementPanel extends JPanel {
    private CustomersBUS bus = new CustomersBUS();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public CustomersManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ========== HEADER PANEL ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel title = new JLabel("QUẢN LÝ KHÁCH HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ========== CONTROL PANEL ==========
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        // Nút chức năng
        JButton btnAdd = new JButton("Thêm khách hàng");
        btnAdd.addActionListener(this::handleAddCustomer);
        controlPanel.add(btnAdd);

        JButton btnEdit = new JButton("Sửa thông tin");
        btnEdit.addActionListener(this::handleEditCustomer);
        controlPanel.add(btnEdit);

        JButton btnDelete = new JButton("Xóa khách hàng");
        btnDelete.addActionListener(this::handleDeleteCustomer);
        controlPanel.add(btnDelete);

        headerPanel.add(controlPanel);

        // ========== SEARCH PANEL ==========
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã KH", "Tên", "SĐT", "Email"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchCustomers());
        
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        
        headerPanel.add(searchPanel);
        add(headerPanel, BorderLayout.NORTH);

        // ========== DATA TABLE ==========
        model = new DefaultTableModel(
            new String[]{"Mã KH", "Tên", "SĐT", "Email"}, 
            0
        );
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void handleAddCustomer(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm khách hàng mới");
        dialog.setLayout(new BorderLayout());

        // ========== INPUT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));

        JTextField txtName = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtEmail = new JTextField();

        inputPanel.add(new JLabel("Tên (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT (*):"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);

        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(evt -> {
            try {
                // Validation
                if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin bắt buộc!", 
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Customers customer = new Customers(
                    0,
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText()
                );

                if (bus.addCustomer(customer)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thành công!", 
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void handleEditCustomer(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int customerId = (int) model.getValueAt(selectedRow, 0);
        Customers customer = bus.getCustomerById(customerId);
        if (customer == null) return;

        JDialog dialog = new JDialog();
        dialog.setTitle("Cập nhật thông tin");
        dialog.setLayout(new BorderLayout());

        // ========== EDIT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));

        JTextField txtName = new JTextField(customer.getCustomer_name());
        JTextField txtPhone = new JTextField(customer.getPhone());
        JTextField txtEmail = new JTextField(customer.getEmail());

        inputPanel.add(new JLabel("Tên (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT (*):"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);

        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Cập nhật");
        btnSave.addActionListener(evt -> {
            try {
                // Validation
                if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin bắt buộc!", 
                        "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                customer.setCustomer_name(txtName.getText());
                customer.setPhone(txtPhone.getText());
                customer.setEmail(txtEmail.getText());

                if (bus.updateCustomer(customer)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thành công!", 
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    dialog.dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Lỗi hệ thống!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void handleDeleteCustomer(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa khách hàng này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int customerId = (int) model.getValueAt(selectedRow, 0);
            if (bus.deleteCustomer(customerId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        List<Customers> customers = bus.getAllCustomers();
        for (Customers c : customers) {
            model.addRow(new Object[]{
                c.getCustomer_id(),
                c.getCustomer_name(),
                c.getPhone(),
                c.getEmail()
            });
        }
    }

    private void searchCustomers() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();
        
        String column = switch (searchType) {
            case "Mã KH" -> "cus_id";
            case "Tên" -> "cus_name";
            case "SĐT" -> "phone";
            case "Email" -> "email";
            default -> "";
        };

        model.setRowCount(0);
        List<Customers> result = bus.searchCustomers(column, keyword);
        for (Customers c : result) {
            model.addRow(new Object[]{
                c.getCustomer_id(),
                c.getCustomer_name(),
                c.getPhone(),
                c.getEmail()
            });
        }
        
        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}