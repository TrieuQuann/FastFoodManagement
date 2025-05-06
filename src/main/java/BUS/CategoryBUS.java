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

    public String[] getAllCategoryName(){
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getName();
        }
        return names;
    }
    
    
    public String getNameById(int id){
        return dao.getNameById(id);
    }
    
    public int getIdByName(String cname){
        for (int i = 0; i < list.size(); i++) {
            if (cname.equals(list.get(i).getName())){
                return list.get(i).getCategoryId();
            }
        }
        return -1;
    }
    
    public boolean addCategory(String name){
        if (name == null )
            return false;
        int result = dao.insert(name);
        System.out.println(result);
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
                    list.set(i, t); 
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
    public void Showdata(){
        CategoryBUS categoryBUS = new CategoryBUS();

        for (int id = 13; id <= 19; id++) {
            String name = categoryBUS.getNameById(id);
            System.out.println("ID: " + id + " | Name: " + (name != null ? name : "Không tìm thấy"));
        }
    }
    
    public ArrayList<Category> searchByName(String search) {
        return new CategoryDAO().searchByName(search); 
    }
    
    public boolean isProductExists(String name) {
        String searchName = name.trim().toLowerCase();
        return list.stream().anyMatch(p -> p.getName().trim().toLowerCase().equals(searchName));
    }
    
    
    public static void main(String[] args) {
//    CategoryBUS categoryBUS = new CategoryBUS();
//
//    for (int id = 13; id <= 19; id++) {
//        String name = categoryBUS.getNameById(id);
//        System.out.println("ID: " + id + " | Name: " + (name != null ? name : "Không tìm thấy"));
//    }
}

}


    
    
    
