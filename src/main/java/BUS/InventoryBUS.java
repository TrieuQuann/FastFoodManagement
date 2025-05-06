/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.InventoryDAO;
import DTO.Inventory;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class InventoryBUS {
    private DAO.InventoryDAO dao;
    private ArrayList<DTO.Inventory> list;

    public InventoryBUS() {
        this.dao = new InventoryDAO();
        this.list = dao.selectAll(); 
    }

    public ArrayList<Inventory> getAll() {
        return (list != null) ? list : new ArrayList<>();
    }
    
    public String getNameById(int id){
        return dao.getNameById(id);
    }
    
    public int getIdByName(String name){
        for (int i = 0; i < list.size(); i++) {
            if (name.trim().equals(list.get(i).getName().trim())){
                return list.get(i).getInven_id();
            }
        }
        return -1;
    }
    public String[] getAllInventoryNames() {
        if (list == null || list.isEmpty()) {
            return new String[0];
        }
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getName();
        }
        return names;
    }
    
    public Double getPriceById(int id) {
        for (Inventory inv : list) {
            if (inv.getInven_id() == id) {
                return inv.getPrice();
            }
        }
        return null; 
    }



}
