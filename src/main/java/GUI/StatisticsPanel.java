/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import DAO.ConnectionDB;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author LENOVO
 */
public class StatisticsPanel extends JPanel {

    private JTextField fromDateField, toDateField;
    private JLabel revenueLabel, orderCountLabel, bestSellingLabel, frequentCustomerLabel;
    private JTable topProductTable, topInvoiceTable, dailyRevenueTable;
    private JScrollPane topProductScrollPane, dailyRevenueScrollPane, topInvoiceScrollPane;

    public StatisticsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fromDateField = new JTextField(LocalDate.now().withDayOfMonth(1).format(formatter), 10);
        toDateField = new JTextField(LocalDate.now().format(formatter), 10);
        JButton statButton = new JButton("Thống kê");

        headerPanel.add(new JLabel("Từ ngày:"));
        headerPanel.add(fromDateField);
        headerPanel.add(new JLabel("Đến ngày:"));
        headerPanel.add(toDateField);
        headerPanel.add(statButton);
        headerPanel.add(new JLabel(" * Định dạng: yyyy-MM-dd")).setForeground(Color.GRAY);

        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 15);

        revenueLabel = new JLabel("Tổng doanh thu: ");
        revenueLabel.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(revenueLabel, gbc);

        orderCountLabel = new JLabel("Tổng số đơn hàng: ");
        orderCountLabel.setFont(labelFont);
        gbc.gridy = 1;
        centerPanel.add(orderCountLabel, gbc);

        bestSellingLabel = new JLabel("Món ăn bán chạy nhất: ");
        bestSellingLabel.setFont(labelFont);
        gbc.gridy = 2;
        centerPanel.add(bestSellingLabel, gbc);

        frequentCustomerLabel = new JLabel("Khách hàng thường xuyên nhất: ");
        frequentCustomerLabel.setFont(labelFont);
        gbc.gridy = 3;
        centerPanel.add(frequentCustomerLabel, gbc);

        // Bảng thống kê cố định bên dưới
        JPanel tablePanel = new JPanel(new GridLayout(1, 3, 15, 15));
        tablePanel.setBackground(Color.WHITE);

        topProductTable = new JTable();
        topProductScrollPane = new JScrollPane(topProductTable);
        tablePanel.add(createTitledPanel("Top 5 Sản phẩm", topProductScrollPane));

        topInvoiceTable = new JTable();
        topInvoiceScrollPane = new JScrollPane(topInvoiceTable);
        tablePanel.add(createTitledPanel("Top 5 Hóa đơn", topInvoiceScrollPane));

        dailyRevenueTable = new JTable();
        dailyRevenueScrollPane = new JScrollPane(dailyRevenueTable);
        tablePanel.add(createTitledPanel("Doanh thu theo ngày", dailyRevenueScrollPane));

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(tablePanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        statButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromDateField.getText();
                String to = toDateField.getText();
                if (isValidDate(from) && isValidDate(to)) {
                    fetchStatistics(from, to);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày: yyyy-MM-dd");
                }
            }
        });
    }

    private JPanel createTitledPanel(String title, JScrollPane content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setBackground(Color.WHITE);
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void fetchStatistics(String fromDate, String toDate) {
        try (Connection conn = ConnectionDB.getConnection()) {

            // Doanh thu
            PreparedStatement st1 = conn.prepareStatement("SELECT SUM(total_amount) FROM orders WHERE deleted = 0 AND order_date BETWEEN ? AND ?");
            st1.setString(1, fromDate);
            st1.setString(2, toDate);
            ResultSet rs1 = st1.executeQuery();
            if (rs1.next()) revenueLabel.setText("Tổng doanh thu: " + rs1.getDouble(1) + " VND");

            // Tổng đơn hàng
            PreparedStatement st2 = conn.prepareStatement("SELECT COUNT(*) FROM orders WHERE deleted = 0 AND order_date BETWEEN ? AND ?");
            st2.setString(1, fromDate);
            st2.setString(2, toDate);
            ResultSet rs2 = st2.executeQuery();
            if (rs2.next()) orderCountLabel.setText("Tổng số đơn hàng: " + rs2.getInt(1));

            // Món ăn bán chạy nhất
            String sql3 = """
                SELECT p.name, SUM(od.quantity) AS total
                FROM orderdetails od
                JOIN products p ON od.product_id = p.product_id
                JOIN orders o ON od.order_id = o.order_id
                WHERE o.deleted = 0 AND o.order_date BETWEEN ? AND ?
                GROUP BY p.name ORDER BY total DESC LIMIT 1
            """;
            PreparedStatement st3 = conn.prepareStatement(sql3);
            st3.setString(1, fromDate);
            st3.setString(2, toDate);
            ResultSet rs3 = st3.executeQuery();
            if (rs3.next())
                bestSellingLabel.setText("Món ăn bán chạy nhất: " + rs3.getString("name") + " (" + rs3.getInt("total") + " sản phẩm)");

            // Khách hàng thường xuyên
            String sql4 = """
                SELECT c.cus_name, COUNT(*) AS order_count
                FROM customers c JOIN orders o ON c.cus_id = o.cus_id
                WHERE o.order_date BETWEEN ? AND ?
                GROUP BY c.cus_name ORDER BY order_count DESC LIMIT 1
            """;
            PreparedStatement st4 = conn.prepareStatement(sql4);
            st4.setString(1, fromDate);
            st4.setString(2, toDate);
            ResultSet rs4 = st4.executeQuery();
            if (rs4.next())
                frequentCustomerLabel.setText("Khách hàng thường xuyên nhất: " + rs4.getString("cus_name") + " (" + rs4.getInt("order_count") + " đơn)");

            // Top 5 sản phẩm
            String sql5 = """
                SELECT p.name, SUM(od.quantity) AS total
                FROM orderdetails od
                JOIN products p ON od.product_id = p.product_id
                JOIN orders o ON od.order_id = o.order_id
                WHERE o.order_date BETWEEN ? AND ? AND o.deleted = 0
                GROUP BY p.name ORDER BY total DESC LIMIT 5
            """;
            PreparedStatement st5 = conn.prepareStatement(sql5);
            st5.setString(1, fromDate);
            st5.setString(2, toDate);
            ResultSet rs5 = st5.executeQuery();
            updateTable(topProductTable, rs5, new String[]{"Tên sản phẩm", "Số lượng bán"});

            // Doanh thu theo ngày
            String sql6 = """
                SELECT order_date, SUM(total_amount) AS daily_total
                FROM orders
                WHERE deleted = 0 AND order_date BETWEEN ? AND ?
                GROUP BY order_date ORDER BY order_date ASC
            """;
            PreparedStatement st6 = conn.prepareStatement(sql6);
            st6.setString(1, fromDate);
            st6.setString(2, toDate);
            ResultSet rs6 = st6.executeQuery();
            updateTable(dailyRevenueTable, rs6, new String[]{"Ngày", "Doanh thu"});

            // Top hóa đơn
            String sql7 = """
                SELECT order_id, cus_id, total_amount
                FROM orders
                WHERE deleted = 0 AND order_date BETWEEN ? AND ?
                ORDER BY total_amount DESC LIMIT 5
            """;
            PreparedStatement st7 = conn.prepareStatement(sql7);
            st7.setString(1, fromDate);
            st7.setString(2, toDate);
            ResultSet rs7 = st7.executeQuery();
            updateTable(topInvoiceTable, rs7, new String[]{"Mã hóa đơn", "Mã KH", "Tổng tiền"});

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable(JTable table, ResultSet rs, String[] columns) throws SQLException {
        ArrayList<Object[]> data = new ArrayList<>();
        while (rs.next()) {
            Object[] row = new Object[columns.length];
            for (int i = 0; i < columns.length; i++) {
                row[i] = rs.getObject(i + 1);
            }
            data.add(row);
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data.toArray(new Object[0][]), columns));
    }
}