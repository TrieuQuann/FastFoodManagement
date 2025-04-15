/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo
 */
public class ProductsBUS {
    private DAO.ProductDAO dao;
    private ArrayList<DTO.Product> list;
    
    public ProductsBUS(){
        dao = new DAO.ProductDAO();
        list = dao.selectAll();
    }
    
    public ArrayList<DTO.Product> getAll(){
        return list;
    }
    
    public void refreshList() {
        list = dao.selectAll();
    }
    
    public boolean addProduct(DTO.Product t){
        if (t.getProductName() == null || t.getProductName().trim().isEmpty() ){
            return false;
        }
        if (t.getPrice() < 0){
            return false;
        }
        
        int result = dao.insert(t);
        if (result!=0){
            list.add(t);
            return true;
        }
        return false;
    }
    
    public boolean updateProduct(DTO.Product t){
        int result = dao.update(t);
        if (result != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductId() == t.getProductId()) {
                    list.set(i, t); // thay thế phần tử tại vị trí i của lisst này bằng t
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean deleteProduct(int id) {
        int result = dao.delete(id); 
        if (result != 0) {
            list.removeIf(p -> p.getProductId() == id); 
            return true;
        }
        return false;
    }
    

    public DTO.Product getProductById(int id) {
        for (DTO.Product p : list) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }


    public ArrayList<DTO.Product> searchByName(String search) {
        ArrayList<DTO.Product> result = new ArrayList<>();

        if (search == null || search.trim().isEmpty()) {
            result.addAll(list);
            return result;
        }

        search = search.toLowerCase();

        for (DTO.Product p : list) {
            String productName = p.getProductName().toLowerCase();
            if (productName.contains(search)) {
                result.add(p);
            }
        }

        return result;
    }
    
    
    public ArrayList<DTO.Product> searchByCategory(String search) {
        ArrayList<DTO.Product> result = new ArrayList<>();

        if (search == null || search.trim().isEmpty()) {
            result.addAll(list);
            return result;
        }

        search = search.toLowerCase();

        for (DTO.Product p : list) {
            String productName = p.getProductName().toLowerCase();
            if (productName.contains(search)) {
                result.add(p);
            }
        }

        return result;
    }
    
    

}