package DAO;

import DTO.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    // Lấy tất cả đơn hàng
    // public List<Orders> getAllOrders() {
    // List<Orders> list = new ArrayList<>();
    // String sql = "SELECT * FROM orders";
    // try (Connection conn = ConnectionDB.getConnection();
    // Statement stmt = conn.createStatement();
    // ResultSet rs = stmt.executeQuery(sql)) {
    // while (rs.next()) {
    // Orders order = new Orders(
    // rs.getInt("order_id"),
    // rs.getInt("cus_id"),
    // rs.getInt("eid"),
    // rs.getDate("order_date"),
    // rs.getDouble("total_amount")
    // );
    // list.add(order);
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return list;
    // }
    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        // JOIN 3 bảng: orders, customers, employee
        // String sql = "SELECT o.order_id, o.cus_id, o.eid, o.order_date,
        // o.total_amount, "
        // + "c.name AS cus_name, e.name AS employee_name "
        // + "FROM orders o "
        // + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
        // + "LEFT JOIN employee e ON o.eid = e.eid";

        String sql = "SELECT o.order_id, o.cus_id, o.eid, o.order_date, o.total_amount, "
                + "c.cus_name AS customer_name, e.employee_name AS employee_name "
                + "FROM orders o "
                + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
                + "LEFT JOIN employee e ON o.eid = e.eid";

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
                        rs.getString("employee_name") // Lấy tên nhân viên
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
        String sql = "SELECT * FROM orders WHERE order_id=?";
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
                            rs.getDouble("total_amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Thêm đơn hàng mới
    public boolean addOrder(Orders order) {
        String sql = "INSERT INTO orders (cus_id, eid, order_date, total_amount) VALUES (?, ?, ?, ?)";
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

    // Cập nhật đơn hàng
    public boolean updateOrder(Orders order) {
        String sql = "UPDATE orders SET cus_id=?, eid=?, order_date=?, total_amount=? WHERE order_id=?";
        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getCus_id());
            ps.setInt(2, order.getEmployee_id());
            ps.setDate(3, new java.sql.Date(order.getOrder_date().getTime()));
            ps.setDouble(4, order.getTotal_amount());
            ps.setInt(5, order.getOrder_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa đơn hàng
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id=?";
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
        String sql = "SELECT o.*, c.name AS cus_name, e.name AS employee_name "
                + "FROM orders o "
                + "LEFT JOIN customers c ON o.cus_id = c.cus_id "
                + "LEFT JOIN employee e ON o.eid = e.eid "
                + "WHERE " + column + " LIKE ?";

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
                            rs.getString("employee_name") // Thêm tên NV
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