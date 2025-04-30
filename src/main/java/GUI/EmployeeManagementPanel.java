package GUI;

import BUS.EmployeeBUS;
import BUS.PositionBUS;
import DTO.Employee;
import DTO.Position;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementPanel extends JPanel {
    private EmployeeBUS bus = new EmployeeBUS();
    private PositionBUS positionBus = new PositionBUS();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType, cboPosition;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch;

    public EmployeeManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
        initEventHandlers();
    }

    private void initUI() {
        // Title
        JLabel title = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã NV", "Tên", "SĐT", "Địa chỉ"});
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Table
        model = new DefaultTableModel(new String[]{"Mã NV", "Chức vụ", "Tên", "SĐT", "Địa chỉ", "Giới tính"}, 0);
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
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        List<Employee> list = bus.getAllEmployees();
        for (Employee emp : list) {
            String positionName = getPositionNameById(emp.getPosition_id());
            model.addRow(new Object[]{
                emp.getEmployee_id(),
                positionName,
                emp.getEmployee_name(),
                emp.getPhone(),
                emp.getAddress(),
                emp.getGender()
            });
        }
    }

    private String getPositionNameById(int positionId) {
        List<Position> positions = positionBus.getAllPositions();
        for (Position pos : positions) {
            if (pos.getPosition_id() == positionId) {
                return pos.getName();
            }
        }
        return "Không xác định";
    }

    private void initEventHandlers() {
        btnAdd.addActionListener(e -> showAddDialog());
        btnEdit.addActionListener(e -> showEditDialog());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnSearch.addActionListener(e -> searchEmployees());
    }

    private void showAddDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm nhân viên");
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        cboPosition = new JComboBox<>();
        List<Position> positions = positionBus.getAllPositions();
        for (Position pos : positions) {
            cboPosition.addItem(pos.getName());
        }

        JTextField txtName = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtAddress = new JTextField();
        JComboBox<String> cboGender = new JComboBox<>(new String[]{"Nam", "Nữ"});

        inputPanel.add(new JLabel("Chức vụ:"));
        inputPanel.add(cboPosition);
        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT:"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Giới tính:"));
        inputPanel.add(cboGender);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            int positionId = positions.get(cboPosition.getSelectedIndex()).getPosition_id();
            Employee emp = new Employee(
                0,
                positionId,
                txtName.getText(),
                txtPhone.getText(),
                txtAddress.getText(),
                cboGender.getSelectedItem().toString()
            );
            if (bus.addEmployee(emp)) {
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

    private void showEditDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!");
            return;
        }

        int employee_id = (int) model.getValueAt(selectedRow, 0);
        Employee emp = bus.getEmployeeById(employee_id);
        if (emp == null) return;

        JDialog dialog = new JDialog();
        dialog.setTitle("Sửa thông tin");
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        cboPosition = new JComboBox<>();
        List<Position> positions = positionBus.getAllPositions();
        for (Position pos : positions) {
            cboPosition.addItem(pos.getName());
        }
        cboPosition.setSelectedItem(getPositionNameById(emp.getPosition_id()));

        JTextField txtName = new JTextField(emp.getEmployee_name());
        JTextField txtPhone = new JTextField(emp.getPhone());
        JTextField txtAddress = new JTextField(emp.getAddress());
        JComboBox<String> cboGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboGender.setSelectedItem(emp.getGender());

        inputPanel.add(new JLabel("Chức vụ:"));
        inputPanel.add(cboPosition);
        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT:"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Giới tính:"));
        inputPanel.add(cboGender);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            int positionId = positions.get(cboPosition.getSelectedIndex()).getPosition_id();
            emp.setPosition_id(positionId);
            emp.setEmployee_name(txtName.getText());
            emp.setPhone(txtPhone.getText());
            emp.setAddress(txtAddress.getText());
            emp.setGender(cboGender.getSelectedItem().toString());
            if (bus.updateEmployee(emp)) {
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

    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int employee_id = (int) model.getValueAt(selectedRow, 0);
            if (bus.deleteEmployee(employee_id)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void searchEmployees() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();
        String column = switch (searchType) {
            case "Mã NV" -> "eid";
            case "Tên" -> "name";
            case "SĐT" -> "phone";
            case "Địa chỉ" -> "address";
            default -> "";
        };

        model.setRowCount(0);
        List<Employee> result = bus.searchEmployees(column, keyword);
        for (Employee emp : result) {
            String positionName = getPositionNameById(emp.getPosition_id());
            model.addRow(new Object[]{
                emp.getEmployee_id(),
                positionName,
                emp.getEmployee_name(),
                emp.getPhone(),
                emp.getAddress(),
                emp.getGender()
            });
        }
    }
}