/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author LENOVO
 */
public class ProductTableModel extends AbstractTableModel {
    private String[] columnName = {"ID", "Tên món", "Loại", "Giá", "Số lượng dự kiến"};
    private ArrayList<DTO.Product> data;
    private BUS.CategoryBUS categoryBUS;
    
    ProductTableModel(){
        this.data=new BUS.ProductsBUS().getAll();
        this.categoryBUS = new BUS.CategoryBUS();
    }
    ProductTableModel(String search){
        this.data=new BUS.ProductsBUS().searchByName(search);
        this.categoryBUS = new BUS.CategoryBUS();
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
        DTO.Product p = data.get(rowIndex);
//        System.out.println(categoryBUS.getNameById(p.getCategoryId()));
//        categoryBUS.Showdata();
    switch (columnIndex) {
        case 0: return p.getProductId();
        case 1: return p.getProductName();
        case 2: return new BUS.CategoryBUS().getNameById(p.getCategoryId());
        case 3: return formatCurrency(p.getPrice());
        case 4: return p.getExpectedQuantity();
        default: return null;
    }
}

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    public void addRow(DTO.Product row) {
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public DTO.Product getProductById(int id){
        for (DTO.Product p : data){
            if (p.getProductId() == id){
                return p;
            }
        }
        return null;
    }
    private String formatCurrency(Double amount) {
        if (amount == null) {
            return "0 VNĐ";
        }
        return String.format("%,.0f VNĐ", amount);
    }
    
}