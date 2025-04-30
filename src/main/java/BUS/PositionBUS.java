/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PositionDAO;
import DTO.Position;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PositionBUS {
    private PositionDAO positionDAO = new PositionDAO();

    public List<Position> getAllPositions() {
        return positionDAO.getAllPositions();
    }
}
