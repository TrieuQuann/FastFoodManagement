package BUS;

import DAO.OrdersDAO;
import DTO.Orders;
import java.util.List;

public class OrdersBUS {
    private OrdersDAO ordersDAO = new OrdersDAO();

    public List<Orders> getAllOrders() {
        return ordersDAO.getAllOrders();
    }

    public boolean addOrder(Orders order) {
        return ordersDAO.addOrder(order);
    }

    public boolean updateOrder(Orders order) {
        return ordersDAO.updateOrder(order);
    }

    public boolean deleteOrder(int orderId) {
        return ordersDAO.deleteOrder(orderId);
    }

    public List<Orders> searchOrders(String keyword) {
        return ordersDAO.searchOrders(keyword);
    }
}
