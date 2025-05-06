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
    
    public int getQuantityRecipe(int pId, int iId) {
        return dao.getQuantityRecipe(pId,iId);
    }
    
    public Boolean hasInven(int pId, int iId) {
        for (Recipe r : list) {
            if (r.getProductId() == pId && r.getInventoryId() == iId) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteRecipe(int pId, int iId) {
        boolean deleted = dao.deleteRecipe(pId, iId);
        if (deleted) {
            this.list = dao.selectAll();
            return true;
        }
        return false;
    }
    
    public boolean updateRecipe(Recipe updated) {
    boolean result = dao.update(updated);
    if (result) {
        for (int i = 0; i < list.size(); i++) {
            Recipe r = list.get(i);
            if (r.getProductId() == updated.getProductId() && r.getInventoryId() == updated.getInventoryId()) {
                list.set(i, updated);
                break;
            }
        }
    }
    return result;
    }

    public boolean addRecipe(Recipe recipe) {
        return dao.insert(recipe);
    }

}
