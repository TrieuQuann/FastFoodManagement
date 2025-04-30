package DAO;

import DTO.Position;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO {
    public List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();
        String sql = "SELECT * FROM position";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Position pos = new Position(
                    rs.getInt("position_id"),
                    rs.getString("name")
                );
                list.add(pos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}