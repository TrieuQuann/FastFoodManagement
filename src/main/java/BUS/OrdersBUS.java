package BUS;

import DAO.OrdersDAO;
import DTO.Orders;
import java.util.List;

public class OrdersBUS {
    private OrdersDAO ordersDAO = new OrdersDAO();

    // Lấy danh sách tất cả đơn hàng
    public List<Orders> getAllOrders() {
        return ordersDAO.getAllOrders();
    }

    // Thêm đơn hàng mới
    public boolean addOrder(Orders order) {
        // Có thể thêm logic kiểm tra nghiệp vụ ở đây (ví dụ: validate cus_id/eid)
        return ordersDAO.addOrder(order);
    }

    // Cập nhật đơn hàng
    public boolean updateOrder(Orders order) {
        // Kiểm tra tồn tại đơn hàng trước khi cập nhật
        if (ordersDAO.getOrderById(order.getOrder_id()) != null) {
            return ordersDAO.updateOrder(order);
        }
        return false;
    }

    // Xóa đơn hàng
    public boolean deleteOrder(int orderId) {
        return ordersDAO.deleteOrder(orderId);
    }

    // Tìm kiếm đơn hàng theo cột và từ khóa
    public List<Orders> searchOrders(String column, String keyword) {
        return ordersDAO.searchOrders(column, keyword);
    }

    // Lấy đơn hàng theo ID (giả định DAO đã có phương thức này)
    public Orders getOrderById(int orderId) {
        return ordersDAO.getOrderById(orderId);
    }
}