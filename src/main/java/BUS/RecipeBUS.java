/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RecipeDAO;
import DTO.Recipe;
import java.util.ArrayList;

public class RecipeBUS {
    private RecipeDAO dao;
    private ArrayList<Recipe> list;

    public RecipeBUS() {
        this.dao = new RecipeDAO();
        this.list = dao.selectAll(); 
    }

    public ArrayList<Recipe> getAll() {
        return (list != null) ? list : new ArrayList<>();
    }

    public ArrayList<Recipe> getByFoodId(int pid) {
        return dao.selectByProductId(pid); 
    }
    
    public Double getTotalById(int product_id) {
        return dao.getTotalById(product_id);
    }
}
