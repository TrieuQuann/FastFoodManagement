package GUI;

import BUS.CustomersBUS;
import DTO.Customers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.util.List;

public class CustomersManagementPanel extends JPanel {
    private CustomersBUS bus = new CustomersBUS();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch;

    public CustomersManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
        initEventHandlers();
    }

    private void initUI() {
        // Title
        JLabel title = new JLabel("QUẢN LÝ KHÁCH HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã KH", "Tên", "SĐT", "Email"});
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Table
        model = new DefaultTableModel(new String[]{"Mã KH", "Tên", "SĐT", "Email"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // Add components to main panel
        add(searchPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Customers> list = bus.getAllCustomers();
        for (Customers c : list) {
            model.addRow(new Object[]{c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail()});
        }
    }

    private void initEventHandlers() {
        // Thêm
        btnAdd.addActionListener(e -> showAddDialog());

        // Sửa
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
                return;
            }
            int customerId = (int) model.getValueAt(selectedRow, 0);
            showEditDialog(customerId);
        });

        // Xóa
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!");
                return;
            }
            int customerId = (int) model.getValueAt(selectedRow, 0);
            deleteCustomer(customerId);
        });

        // Tìm kiếm
        btnSearch.addActionListener(e -> searchCustomers());
    }

    private void showAddDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm khách hàng");
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField txtName = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtEmail = new JTextField();

        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT:"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            Customers customer = new Customers(0, txtName.getText(), txtPhone.getText(), txtEmail.getText());
            if (bus.addCustomer(customer)) {
                JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
                loadData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Thêm thất bại!");
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showEditDialog(int customerId) {
        Customers customer = bus.getCustomerById(customerId);
        if (customer == null) return;

        JDialog dialog = new JDialog();
        dialog.setTitle("Sửa thông tin");
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField txtName = new JTextField(customer.getName());
        JTextField txtPhone = new JTextField(customer.getPhone());
        JTextField txtEmail = new JTextField(customer.getEmail());

        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT:"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            customer.setName(txtName.getText());
            customer.setPhone(txtPhone.getText());
            customer.setEmail(txtEmail.getText());
            if (bus.updateCustomer(customer)) {
                JOptionPane.showMessageDialog(dialog, "Cập nhật thành công!");
                loadData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Cập nhật thất bại!");
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void deleteCustomer(int customerId) {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (bus.deleteCustomer(customerId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
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
            model.addRow(new Object[]{c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail()});
        }
    }
}