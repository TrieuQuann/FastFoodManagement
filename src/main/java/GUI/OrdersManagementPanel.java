package GUI;

import BUS.OrdersBUS;
import DTO.Orders;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
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
        initEventHandlers();
    }

    private void initUI() {
        // Tiêu đề
        JLabel title = new JLabel("QUẢN LÝ ĐƠN HÀNG", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Panel tìm kiếm
        JPanel searchPanel = new JPanel();
        cboSearchType = new JComboBox<>(new String[]{"Mã đơn", "Tên KH", "Tên NV", "Ngày đặt", "Tổng tiền"});
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        searchPanel.add(new JLabel("Tìm theo:"));
        searchPanel.add(cboSearchType);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Bảng dữ liệu
        model = new DefaultTableModel(new String[]{"Mã đơn", "Tên KH", "Tên NV", "Ngày đặt", "Tổng tiền"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm components vào panel chính
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        List<Orders> orders = bus.getAllOrders();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        for (Orders order : orders) {
            model.addRow(new Object[]{
                order.getOrder_id(),
                order.getCustomerName(), // Hiển thị tên KH
                order.getEmployeeName(), // Hiển thị tên NV
                sdf.format(order.getOrder_date()),
                String.format("%,.2f", order.getTotal_amount())
            });
        }
    }

    private void initEventHandlers() {
        // Xử lý sự kiện tìm kiếm
        JButton btnSearch = (JButton) ((JPanel) getComponent(1)).getComponent(3);
        btnSearch.addActionListener(e -> searchOrders());
    }

    private void searchOrders() {
        String searchType = cboSearchType.getSelectedItem().toString();
        String keyword = txtSearch.getText().trim();
        
        // Ánh xạ tên cột tìm kiếm sang tên cột trong SQL
        String column = switch (searchType) {
            case "Mã đơn" -> "o.order_id";
            case "Tên KH" -> "c.name"; // Tìm theo tên KH
            case "Tên NV" -> "e.name"; // Tìm theo tên NV
            case "Ngày đặt" -> "o.order_date";
            case "Tổng tiền" -> "o.total_amount";
            default -> "";
        };

        model.setRowCount(0);
        List<Orders> result = bus.searchOrders(column, keyword);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        for (Orders order : result) {
            model.addRow(new Object[]{
                order.getOrder_id(),
                order.getCustomerName(),
                order.getEmployeeName(),
                sdf.format(order.getOrder_date()),
                String.format("%,.2f", order.getTotal_amount())
            });
        }
    }
}