/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DTO.Position;

/**
 *
 * @author Lenovo
 */
public class PositionDAO {
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM position";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Position pos = new Position(
                    rs.getInt("position_id"),
                    rs.getString("position_name")
                );
                positions.add(pos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }
    // Thêm các phương thức thêm, sửa, xóa, tìm kiếm nếu cần
}
