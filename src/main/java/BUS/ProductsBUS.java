/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

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
        return result!=0;
    }

    
    
}



//
//    public boolean updateProduct(Product p) {
//        boolean kq = dao.update(p);
//        if (kq) {
//            for (int i = 0; i < dsProduct.size(); i++) {
//                if (dsProduct.get(i).getId() == p.getId()) {
//                    dsProduct.set(i, p);
//                    break;
//                }
//            }
//        }
//        return kq;
//    }
//
//    // Xóa sản phẩm
//    public boolean deleteProduct(int id) {
//        boolean kq = dao.delete(id);
//        if (kq) {
//            dsProduct.removeIf(p -> p.getId() == id);
//        }
//        return kq;
//    }
//
//    // Tìm kiếm sản phẩm theo tên
//    public ArrayList<Product> searchByName(String keyword) {
//        ArrayList<Product> result = new ArrayList<>();
//        for (Product p : dsProduct) {
//            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
//                result.add(p);
//            }
//        }
//        return result;
//    }
//}