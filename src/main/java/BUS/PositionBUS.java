package BUS;

import DAO.PositionDAO;
import DTO.Position;
import java.util.List;

public class PositionBUS {
    private PositionDAO positionDAO = new PositionDAO();

    public List<Position> getAllPositions() {
        return positionDAO.getAllPositions();
    }
}