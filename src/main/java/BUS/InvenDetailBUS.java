/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BUS;

import DAO.InvenDetailDAO;
import DTO.InvenDetail;

public class InvenDetailBUS {
    private InvenDetailDAO dao = new InvenDetailDAO();

    public boolean addInvendetail(InvenDetail detail) {
        return dao.addInvendetail(detail);
    }
}
