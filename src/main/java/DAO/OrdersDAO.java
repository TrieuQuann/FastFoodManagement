package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.Orders;

public class OrdersDAO {
    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("order_id"),
                    rs.getInt("customers_id"),
                    rs.getInt("employee_id"),
                    rs.getString("order_date"),
                    rs.getDouble("total_amount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Thêm hóa đơn
    public boolean addOrder(Orders order) {
        String sql = "INSERT INTO orders (customers_id, employee_id, order_date, total_amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCustomers_id());
            ps.setInt(2, order.getEmployee_id());
            ps.setString(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa hóa đơn
    public boolean updateOrder(Orders order) {
        String sql = "UPDATE orders SET customers_id=?, employee_id=?, order_date=?, total_amount=? WHERE order_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCustomers_id());
            ps.setInt(2, order.getEmployee_id());
            ps.setString(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.setInt(5, order.getOrder_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa hóa đơn
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm hóa đơn theo mã hóa đơn hoặc mã khách hàng
    public List<Orders> searchOrders(String keyword) {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_id LIKE ? OR customers_id LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders(
                        rs.getInt("order_id"),
                        rs.getInt("customers_id"),
                        rs.getInt("employee_id"),
                        rs.getString("order_date"),
                        rs.getDouble("total_amount")
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}