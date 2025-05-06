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
/**
 *
 * @author LENOVO
 */
public class StatisticsPanel extends JPanel {

    private JTextField fromDateField, toDateField;
    private JLabel revenueLabel, orderCountLabel, bestSellingLabel;

    public StatisticsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // --- Panel nhập ngày ---
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        inputPanel.setBackground(Color.WHITE);

        fromDateField = new JTextField(LocalDate.now().withDayOfMonth(1).format(formatter), 10);
        toDateField = new JTextField(LocalDate.now().format(formatter), 10);
        JButton statButton = new JButton("Thống kê");

        inputPanel.add(new JLabel("Từ ngày:"));
        inputPanel.add(fromDateField);
        inputPanel.add(new JLabel("Đến ngày:"));
        inputPanel.add(toDateField);
        inputPanel.add(statButton);

        JLabel note = new JLabel("* Định dạng: yyyy-MM-dd");
        note.setFont(new Font("Arial", Font.ITALIC, 12));
        note.setForeground(Color.GRAY);
        note.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(inputPanel);
        add(note);

        // --- Tiêu đề phân cách ---
        JLabel sectionTitle = new JLabel("KẾT QUẢ THỐNG KÊ", SwingConstants.LEFT);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 18));
        sectionTitle.setForeground(Color.DARK_GRAY);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        //add(Box.createVerticalStrut(20));
        add(sectionTitle);
        //add(Box.createVerticalStrut(5));
        add(new JSeparator());

        // --- Kết quả thống kê ---
        revenueLabel = new JLabel("Tổng doanh thu: ");
        orderCountLabel = new JLabel("Tổng số đơn hàng: ");
        bestSellingLabel = new JLabel("Món ăn bán chạy nhất: ");

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        revenueLabel.setFont(labelFont);
        orderCountLabel.setFont(labelFont);
        bestSellingLabel.setFont(labelFont);

        //add(Box.createVerticalStrut(20));
        add(revenueLabel);
        //add(Box.createVerticalStrut(10));
        add(orderCountLabel);
        //add(Box.createVerticalStrut(10));
        add(bestSellingLabel);

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
    
    private void fetchStatistics(String fromDate, String toDate) {
        try (Connection conn = DAO.ConnectionDB.getConnection()) {
            // Tổng doanh thu
            String revenueQuery = "SELECT SUM(total_amount) FROM orders WHERE deleted = 0 AND order_date BETWEEN ? AND ?";
            PreparedStatement revenueStmt = conn.prepareStatement(revenueQuery);
            revenueStmt.setString(1, fromDate);
            revenueStmt.setString(2, toDate);
            ResultSet rs = revenueStmt.executeQuery();
            if (rs.next()) {
                revenueLabel.setText("Tổng doanh thu: " + rs.getDouble(1) + " VND");
            }

            // Tổng số đơn hàng
            String orderCountQuery = "SELECT COUNT(*) FROM orders WHERE deleted = 0 AND order_date BETWEEN ? AND ?";
            PreparedStatement orderCountStmt = conn.prepareStatement(orderCountQuery);
            orderCountStmt.setString(1, fromDate);
            orderCountStmt.setString(2, toDate);
            rs = orderCountStmt.executeQuery();
            if (rs.next()) {
                orderCountLabel.setText("Tổng số đơn hàng: " + rs.getInt(1));
            }

            // Món ăn bán chạy nhất
            String bestSellingQuery = "SELECT p.name, SUM(od.quantity) AS total_sold " +
                                      "FROM orderdetails od " +
                                      "JOIN products p ON od.product_id = p.product_id " +
                                      "JOIN orders o ON od.order_id = o.order_id " +
                                      "WHERE o.deleted = 0 AND o.order_date BETWEEN ? AND ? " +
                                      "GROUP BY p.name ORDER BY total_sold DESC LIMIT 1";
            PreparedStatement bestSellingStmt = conn.prepareStatement(bestSellingQuery);
            bestSellingStmt.setString(1, fromDate);
            bestSellingStmt.setString(2, toDate);
            rs = bestSellingStmt.executeQuery();
            if (rs.next()) {
                bestSellingLabel.setText("Món ăn bán chạy nhất: " + rs.getString("name") + " (" + rs.getInt("total_sold") + " sản phẩm)");
            } else {
                bestSellingLabel.setText("Món ăn bán chạy nhất: Không có dữ liệu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void showRevenueAndOrderCount(String from, String to) {
        String query = """
            SELECT COUNT(*) AS total_orders, SUM(total_amount) AS total_revenue
            FROM orders
            WHERE order_date BETWEEN ? AND ? AND deleted = 0
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, from);
            stmt.setString(2, to);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int totalOrders = rs.getInt("total_orders");
                double totalRevenue = rs.getDouble("total_revenue");

                orderCountLabel.setText("Tổng số đơn hàng: " + totalOrders);
                revenueLabel.setText("Tổng doanh thu: " + String.format("%.0f VND", totalRevenue));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showBestSellingProduct(String from, String to) {
        String query = """
            SELECT p.name, SUM(od.quantity) AS total_quantity
            FROM orderdetails od
            JOIN orders o ON od.order_id = o.order_id
            JOIN products p ON od.product_id = p.product_id
            WHERE o.order_date BETWEEN ? AND ? AND o.deleted = 0
            GROUP BY od.product_id
            ORDER BY total_quantity DESC
            LIMIT 1
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, from);
            stmt.setString(2, to);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String productName = rs.getString("name");
                int totalSold = rs.getInt("total_quantity");
                bestSellingLabel.setText("Món ăn bán chạy nhất: " + productName + " (" + totalSold + " sản phẩm)");
            } else {
                bestSellingLabel.setText("Món ăn bán chạy nhất: Không có dữ liệu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}