/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class OrderDetailsDAO {
    
    public int insert(int oid, int pid, int quantity, double price, double total_price) {
        int result = 0;
        String sql = "INSERT INTO orderdetails (order_id, product_id , quantity, price, total_price) " +
                "VALUES (?,?,?,?,?)";

        try (
                Connection con = DAO.ConnectionDB.getConnection();
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, oid);
            pst.setInt(2, pid);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);
            pst.setDouble(5, total_price);

            result = pst.executeUpdate();
            if (result != 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        result = generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public ArrayList<OrderDetails> selectAll() {
        ArrayList<DTO.OrderDetails> result = new ArrayList<DTO.OrderDetails>();
        String sql = "SELECT * FROM orderdetails WHERE status = 1";

        try (
                Connection con = ConnectionDB.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                int oid = rs.getInt("order_id");
                int pid = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");                
                double price = rs.getDouble("price");
                double total_price = rs.getDouble("total_price");

                DTO.OrderDetails sp = new DTO.OrderDetails(oid, pid, quantity, price, total_price);
                result.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public int getMaxOrderId() {
        int maxOrderId = 0;  
        String sql = "SELECT MAX(order_id) AS max_order_id FROM `orders`";

        try (
            Connection con = ConnectionDB.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
        ) {
            if (rs.next()) {
                maxOrderId = rs.getInt("max_order_id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxOrderId;  
    }


}



