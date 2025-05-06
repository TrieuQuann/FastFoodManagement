package DAO;

import DTO.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT o.order_id, o.cus_id, o.eid, o.order_date, o.total_amount, c.cus_name AS customer_name, e.employee_name AS employee_name, o.deleted "
                + "FROM orders o "
                + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
                + "LEFT JOIN employee e ON o.eid = e.eid"
                + " WHERE o.deleted = 0"; // Chỉ lấy các đơn hàng chưa bị xóa

        try (Connection conn = ConnectionDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("order_id"),
                        rs.getInt("cus_id"),
                        rs.getInt("eid"),
                        rs.getDate("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("customer_name"), // Lấy tên khách hàng
                        rs.getString("employee_name"), // Lấy tên nhân viên
                        rs.getInt("deleted") // Lấy trạng thái xóa
                        
                );
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Orders getOrderById(int orderId) {
        Orders order = null;
        // String sql = "SELECT * FROM orders WHERE order_id=?";
        String sql = "SELECT o.order_id, o.cus_id, o.eid, o.order_date, o.total_amount, c.cus_name AS customer_name, e.employee_name AS employee_name, o.deleted"
                   + "FROM orders o "
                   + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
                   + "LEFT JOIN employee e ON o.eid = e.eid "
                   + "WHERE o.order_id = ? AND o.deleted = 0";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order = new Orders(
                            rs.getInt("order_id"),
                            rs.getInt("cus_id"),
                            rs.getInt("eid"),
                            rs.getDate("order_date"),
                            rs.getDouble("total_amount"),
                            rs.getInt("deleted")); // Lấy trạng thái xóa;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Thêm đơn hàng mới
    public boolean addOrder(Orders order) {
        String sql = "INSERT INTO orders (cus_id, eid, order_date, total_amount, deleted) VALUES (?, ?, ?, ?, 0)";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCus_id());
            ps.setInt(2, order.getEmployee_id());
            ps.setDate(3, new java.sql.Date(order.getOrder_date().getTime()));
            ps.setDouble(4, order.getTotal_amount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa mềm đơn hàng (cập nhật trường deleted)
    public boolean softDeleteOrder(int orderId) {
        String sql = "UPDATE orders SET deleted = 1 WHERE order_id=?";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Orders> searchOrders(String column, String keyword) {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT o.order_id, o.cus_id, o.eid, o.order_date, o.total_amount, c.cus_name AS customer_name, e.employee_name AS employee_name, o.deleted "
                + "FROM orders o "
                + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
                + "LEFT JOIN employee e ON o.eid = e.eid "
                + "WHERE o.deleted = 0 AND " + column + " LIKE ?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders(
                            rs.getInt("order_id"),
                            rs.getInt("cus_id"),
                            rs.getInt("eid"),
                            rs.getDate("order_date"),
                            rs.getDouble("total_amount"),
                            rs.getString("customer_name"), // Thêm tên KH
                            rs.getString("employee_name"), // Thêm tên NV
                            rs.getInt("deleted") // Lấy trạng thái xóa
                    );
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}