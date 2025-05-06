/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.CategoryBUS;
import DTO.Category;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */

public class RecipeTableModel extends AbstractTableModel {
    private String[] columnName = {"Món ăn", "Nguyên liệu", "Số lượng", "Thành tiền"};
    private ArrayList<DTO.Recipe> data;
    private BUS.RecipeBUS recipeBUS;

    public RecipeTableModel() {
        this.recipeBUS = new BUS.RecipeBUS();
//        this.data = recipeBUS.getAll(); 
        this.data = new ArrayList<>();
    }

    public RecipeTableModel(int pid) {
        this.recipeBUS = new BUS.RecipeBUS();
        this.data = recipeBUS.getByFoodId(pid); 
    }

    @Override
    public int getRowCount() {
        return data.size(); 
    }

    @Override
    public int getColumnCount() {
        return columnName.length; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= data.size() || rowIndex < 0) return null;
        BUS.ProductsBUS pBUS = new BUS.ProductsBUS();
        BUS.InventoryBUS invBUS = new BUS.InventoryBUS();
        DTO.Recipe r = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return pBUS.getProductById(r.getProductId()).getProductName(); 
            case 1: return invBUS.getNameById(r.getInventoryId()); 
            case 2: return r.getAmount(); 
            case 3: return formatCurrency(r.getTotal_price()); 
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column]; 
    }

    public void reloadData() {
        this.data = recipeBUS.getAll(); 
        fireTableDataChanged(); 
    }
    
    private String formatCurrency(Double amount) {
        if (amount == null) {
            return "0 VNĐ";
        }
        return String.format("%,.0f VNĐ", amount);
    }
    
}
