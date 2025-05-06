/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.InventoryDAO;
import DTO.Inventory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class InventoryBUS {
    // private DAO.InventoryDAO dao;
    // private ArrayList<DTO.Inventory> list;

    // public InventoryBUS() {
    //     this.dao = new InventoryDAO();
    //     this.list = dao.selectAll(); 
    // }

    // public ArrayList<Inventory> getAll() {
    //     return (list != null) ? list : new ArrayList<>();
    // }
    
    // public String getNameById(int id){
    //     return dao.getNameById(id);
    // }
    
    // public int getIdByName(String name){
    //     for (int i = 0; i < list.size(); i++) {
    //         if (name.trim().equals(list.get(i).getName().trim())){
    //             return list.get(i).getInven_id();
    //         }
    //     }
    //     return -1;
    // }
    private InventoryDAO dao = new InventoryDAO();

    public List<Inventory> getAllInventory() {
        return dao.getAllInventory();
    }

    public boolean updateInventoryQuantity(int invenId, int quantity) {
        return dao.updateInventoryQuantity(invenId, quantity);
    }

    public boolean addInventory(Inventory inventory) {
        return dao.addInventory(inventory);
    }
    public boolean updateInventory(Inventory inventory) {
        return dao.updateInventory(inventory);
    }
}
