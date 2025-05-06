/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import BUS.CategoryBUS;
import DTO.Category;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class CategoryTableModel extends AbstractTableModel {
    private String[] columnName = {"ID", "Tên danh mục"};
    private ArrayList<Category> data;
    private CategoryBUS categoryBUS;

    public CategoryTableModel() {
        this.categoryBUS = new CategoryBUS();
        this.data = categoryBUS.getAll();
    }

    public CategoryTableModel(String search) {
        this.categoryBUS = new CategoryBUS();
        this.data = categoryBUS.searchByName(search); 
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
        Category c = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getCategoryId();
            case 1: return c.getName();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    public Category getCategoryById(int id) {
        for (Category c : data) {
            if (c.getCategoryId() == id) {
                return c;
            }
        }
        return null;
    }
}
