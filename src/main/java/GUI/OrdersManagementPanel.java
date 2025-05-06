package GUI;

import BUS.OrdersBUS;
import DTO.Orders;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersManagementPanel extends JPanel {
    private OrdersBUS bus = new OrdersBUS();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;
    private JComboBox<String> cboSearchType;

    public OrdersManagementPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ========== PANEL HEADER ==========
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel title = new JLabel("QUẢN LÝ ĐƠN HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(title);

        // ========== PANEL CHỨC NĂNG ==========
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        // Nút thêm
        JButton btnAdd = new JButton("Tạo hóa đơn");
        btnAdd.addActionListener(this::handleAddOrder);
        controlPanel.add(btnAdd);

        // Nút xóa
        JButton btnDelete = new JButton("Xóa hóa đơn");
        btnDelete.addActionListener(this::handleDeleteOrder);
        controlPanel.add(btnDelete);

        headerPanel.add(controlPanel);

        // ========== PANEL TÌM KIẾM ==========
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã đơn", "Tên KH", "Tên NV", "Ngày đặt", "Tổng tiền"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchOrders());
        
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        
        headerPanel.add(searchPanel);

        add(headerPanel, BorderLayout.NORTH);

        // ========== BẢNG DỮ LIỆU ==========
        model = new DefaultTableModel(
            new String[]{"Mã đơn", "Tên KH", "Tên NV", "Ngày đặt", "Tổng tiền"}, 
            0
        );
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Chiều cao bảng
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void handleAddOrder(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Tạo hóa đơn mới");
        dialog.setLayout(new BorderLayout());

        // ========== FORM NHẬP LIỆU ==========
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(400, 200));
        
        JTextField txtCusId = new JTextField();
        JTextField txtEid = new JTextField();
        JDateChooser dateChooser = new JDateChooser();
        JTextField txtTotalAmount = new JTextField();

        inputPanel.add(new JLabel("Mã KH (*):"));
        inputPanel.add(txtCusId);
        inputPanel.add(new JLabel("Mã NV (*):"));
        inputPanel.add(txtEid);
        inputPanel.add(new JLabel("Ngày đặt (*):"));
        inputPanel.add(dateChooser);
        inputPanel.add(new JLabel("Tổng tiền (*):"));
        inputPanel.add(txtTotalAmount);

        // ========== NÚT LƯU ==========
        JButton btnSave = new JButton("Lưu hóa đơn");
        btnSave.addActionListener(evt -> {
            try {
                // Validate dữ liệu
                if (txtCusId.getText().isEmpty() || txtEid.getText().isEmpty() 
                    || dateChooser.getDate() == null || txtTotalAmount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                // Tạo đối tượng Order
                Orders newOrder = new Orders(
                    0,
                    Integer.parseInt(txtCusId.getText()),
                    Integer.parseInt(txtEid.getText()),
                    new Date(dateChooser.getDate().getTime()),
                    Double.parseDouble(txtTotalAmount.getText()),
                    "", 
                    "", 
                    0
                );

                // Gọi BUS để thêm
                if (bus.addOrder(newOrder)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thành công!");
                    loadData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Dữ liệu số không hợp lệ!");
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

    private void handleDeleteOrder(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa hóa đơn này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int orderId = (int) model.getValueAt(selectedRow, 0);
            if (bus.softDeleteOrder(orderId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    }

    private void loadData() {
        model.setRowCount(0);
        List<Orders> orders = bus.getAllOrders();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Orders order : orders) {
            model.addRow(new Object[]{
                order.getOrder_id(),
                order.getCustomerName(),
                order.getEmployeeName(),
                sdf.format(order.getOrder_date()),
                String.format("%,.2f VND", order.getTotal_amount())
            });
        }
    }

    private void searchOrders() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();
        
        String column = switch (searchType) {
            case "Mã đơn" -> "o.order_id";
            case "Tên KH" -> "c.cus_name";
            case "Tên NV" -> "e.employee_name";
            case "Ngày đặt" -> "o.order_date";
            case "Tổng tiền" -> "o.total_amount";
            default -> "";
        };

        model.setRowCount(0);
        List<Orders> result = bus.searchOrders(column, keyword);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Orders order : result) {
            model.addRow(new Object[]{
                order.getOrder_id(),
                order.getCustomerName(),
                order.getEmployeeName(),
                sdf.format(order.getOrder_date()),
                String.format("%,.2f VND", order.getTotal_amount())
            });
        }
        
    }
}