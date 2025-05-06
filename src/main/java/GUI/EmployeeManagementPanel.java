package GUI;

import BUS.EmployeeBUS;
import BUS.PositionBUS;
import DTO.Employee;
import DTO.Position;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmployeeManagementPanel extends JPanel {
    private EmployeeBUS bus = new EmployeeBUS();
    private PositionBUS positionBus = new PositionBUS();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public EmployeeManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ================== HEADER PANEL ==================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel title = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ================== CONTROL PANEL ==================
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        // Nút chức năng
        JButton btnAdd = new JButton("Thêm nhân viên");
        btnAdd.addActionListener(this::handleAddEmployee);
        controlPanel.add(btnAdd);

        JButton btnEdit = new JButton("Sửa thông tin");
        btnEdit.addActionListener(this::handleEditEmployee);
        controlPanel.add(btnEdit);

        JButton btnDelete = new JButton("Xóa nhân viên");
        btnDelete.addActionListener(this::handleDeleteEmployee);
        controlPanel.add(btnDelete);

        headerPanel.add(controlPanel);

        // ================== SEARCH PANEL ==================
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã NV", "Tên", "SĐT", "Địa chỉ"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchEmployees());
        
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        
        headerPanel.add(searchPanel);
        add(headerPanel, BorderLayout.NORTH);

        // ================== DATA TABLE ==================
        model = new DefaultTableModel(
            new String[]{"Mã NV", "Chức vụ", "Tên", "SĐT", "Địa chỉ", "Giới tính"}, 
            0
        );
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void handleAddEmployee(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm nhân viên mới");
        dialog.setLayout(new BorderLayout());

        // ========== INPUT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));
        // ComboBox chức vụ
        JComboBox<String> cboPosition = new JComboBox<>();
        List<Position> positions = positionBus.getAllPositions();
        for (Position pos : positions) {
            cboPosition.addItem(pos.getName());
        }

        JTextField txtName = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtAddress = new JTextField();
        JComboBox<String> cboGender = new JComboBox<>(new String[]{"Nam", "Nữ"});

        inputPanel.add(new JLabel("Chức vụ (*):"));
        inputPanel.add(cboPosition);
        inputPanel.add(new JLabel("Tên (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT (*):"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Giới tính (*):"));
        inputPanel.add(cboGender);

        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Lưu thông tin");
        btnSave.addActionListener(evt -> {
            try {
                // Validation
                if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin bắt buộc!");
                    return;
                }

                Employee emp = new Employee(
                    0,
                    positions.get(cboPosition.getSelectedIndex()).getPosition_id(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    cboGender.getSelectedItem().toString()
                );

                if (bus.addEmployee(emp)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
                    loadData();
                    dialog.dispose();
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

    private void handleEditEmployee(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!");
            return;
        }

        int employeeId = (int) model.getValueAt(selectedRow, 0);
        Employee emp = bus.getEmployeeById(employeeId);
        if (emp == null) return;

        JDialog dialog = new JDialog();
        dialog.setTitle("Cập nhật thông tin");
        dialog.setLayout(new BorderLayout());

        // ========== EDIT FORM ==========
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));
        // ComboBox chức vụ
        JComboBox<String> cboPosition = new JComboBox<>();
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

        inputPanel.add(new JLabel("Chức vụ (*):"));
        inputPanel.add(cboPosition);
        inputPanel.add(new JLabel("Tên (*):"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("SĐT (*):"));
        inputPanel.add(txtPhone);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(txtAddress);
        inputPanel.add(new JLabel("Giới tính (*):"));
        inputPanel.add(cboGender);

        // ========== SAVE BUTTON ==========
        JButton btnSave = new JButton("Cập nhật");
        btnSave.addActionListener(evt -> {
            try {
                emp.setPosition_id(positions.get(cboPosition.getSelectedIndex()).getPosition_id());
                emp.setEmployee_name(txtName.getText());
                emp.setPhone(txtPhone.getText());
                emp.setAddress(txtAddress.getText());
                emp.setGender(cboGender.getSelectedItem().toString());

                if (bus.updateEmployee(emp)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thành công!");
                    loadData();
                    dialog.dispose();
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

    private void handleDeleteEmployee(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa nhân viên này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int employeeId = (int) model.getValueAt(selectedRow, 0);
            if (bus.deleteEmployee(employeeId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        List<Employee> employees = bus.getAllEmployees();
        for (Employee emp : employees) {
            model.addRow(new Object[]{
                emp.getEmployee_id(),
                getPositionNameById(emp.getPosition_id()),
                emp.getEmployee_name(),
                emp.getPhone(),
                emp.getAddress(),
                emp.getGender()
            });
        }
    }

    private String getPositionNameById(int positionId) {
        return positionBus.getAllPositions().stream()
                .filter(p -> p.getPosition_id() == positionId)
                .findFirst()
                .map(Position::getName)
                .orElse("Không xác định");
    }

    private void searchEmployees() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();
        
        String column = switch (searchType) {
            case "Mã NV" -> "eid";
            case "Tên" -> "employee_name";
            case "SĐT" -> "phone";
            case "Địa chỉ" -> "address";
            default -> "";
        };

        model.setRowCount(0);
        List<Employee> result = bus.searchEmployees(column, keyword);
        for (Employee emp : result) {
            model.addRow(new Object[]{
                emp.getEmployee_id(),
                getPositionNameById(emp.getPosition_id()),
                emp.getEmployee_name(),
                emp.getPhone(),
                emp.getAddress(),
                emp.getGender()
            });
        }
    }
}