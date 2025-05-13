/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DTO.OrderDetails;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class OrderDetailsBUS {
    private DAO.OrderDetailsDAO dao;
    private ArrayList<DTO.OrderDetails> list;
    
    public OrderDetailsBUS(){
        dao = new DAO.OrderDetailsDAO();
        list = dao.selectAll();
    }
    
    public ArrayList<DTO.OrderDetails> getAll(){
        return list;
    }
    
    public void refreshList() {
        list = dao.selectAll();
    }
    
    public int addOrderDetail(int orderId, int productId, int quantity, double price, double totalPrice) {
        return dao.insert(orderId, productId, quantity, price, totalPrice);
    }
    
    public int getMaxOrderId(){
        return dao.getMaxOrderId();
    }
    
    public ArrayList<DTO.OrderDetails> getOrderDetailByOrderId(int oId){
        return dao.getOrderDetailByOrderId(oId);
    }
}
