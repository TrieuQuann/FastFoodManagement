/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CategoryDAO;
import DTO.Category;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class CategoryBUS {
    private DAO.CategoryDAO dao;
    private ArrayList<DTO.Category> list;

    public CategoryBUS(CategoryDAO dao, ArrayList<Category> list) {
        this.dao = dao;
        this.list = list;
    }

    public CategoryBUS() {
        dao = new DAO.CategoryDAO();
        list = dao.selectAll();
    }
    
    public ArrayList<DTO.Category> getAll(){
        return list;
    }
    
    public boolean addCategory(String name){
        if (name == null )
            return false;
        int result = dao.insert(name);
        if (result != 0 ){
            DTO.Category t = new Category(result, name);
            list.add(t);
            return true;
        }
        return false;
    }
    
    public boolean updateCategory(DTO.Category t){
        int result = dao.update(t);
        if (result != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCategoryId() == t.getCategoryId()) {
                    list.set(i, t); // thay thế phần tử tại vị trí i của lisst này bằng t
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean deleteCategory(int id) {
        
        int result = dao.delete(id); 
        if (result != 0) {
            list.removeIf(p -> p.getCategoryId() == id); 
            return true;
        }
        return false;
    }
    
    

    
    
    
}
