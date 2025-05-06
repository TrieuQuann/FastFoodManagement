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
    
    public boolean addProduct(String pname, String image, String cname){
        if (pname == null || pname.trim().isEmpty()) {
            System.err.println("Tên sản phẩm không được để trống.");
            return false;
        }
        if (cname == null || cname.trim().isEmpty()) {
            System.err.println("Loại món ăn không được để trống.");
            return false;
        }
        if (image == null || image.trim().isEmpty()) {
            System.err.println("Đường dẫn hình ảnh không hợp lệ.");
            return false;
        }
        int cid = selectCategoryIdByCategoryName(cname);
        if (cid == -1) {
            System.err.println("Không tìm thấy loại món ăn: " + cname);
            return false;
        }
        int result = dao.insert(pname,image,cid);
        if (result!=0){
            DTO.Product t = new DAO.ProductDAO().selectById(result);
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
    
    public boolean updateProduct(int id, String pname, String image, String cname){
        if (id <= 0) {
            System.err.println("ID sản phẩm không hợp lệ.");
            return false;
        }
        if (pname == null || pname.trim().isEmpty()) {
            System.err.println("Tên sản phẩm không được để trống.");
            return false;
        }
        if (cname == null || cname.trim().isEmpty()) {
            System.err.println("Loại món ăn không được để trống.");
            return false;
        }
        if (image == null || image.trim().isEmpty()) {
            System.err.println("Đường dẫn hình ảnh không hợp lệ.");
            return false;
        }
        int cid = selectCategoryIdByCategoryName(cname);
        if (cid == -1) {
            System.err.println("Không tìm thấy loại món ăn: " + cname);
            return false;
        }
        
        int result = dao.update(id,pname,image,cid);
        if (result != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductId() == id) {
                    list.get(i).setCategoryId(cid);
                    list.get(i).setImage(image);
                    list.get(i).setProductName(pname);
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
    
    
public String[] searchByCategoryName(String search) {
    if (search == null || search.trim().isEmpty()) {
        System.out.println("Lỗi! Tên danh mục trống!");
        return new String[0];
    }

    int cid = new BUS.CategoryBUS().getIdByName(search);
    if (cid == -1) {
        System.out.println("Lỗi! Không tìm thấy tên danh mục!");
        return new String[0];
    }

    ArrayList<String> resultList = new ArrayList<>();
    for (DTO.Product p : list) {
        if (p.getCategoryId() == cid) {
            resultList.add(p.getProductName());
        }
    }

    return resultList.toArray(new String[0]);
}


    public Boolean isCategoryUseInProduct(int cid){
        for (DTO.Product p : list) {
            int categoryId = p.getCategoryId();
            if (categoryId==cid) {
                return true;
            }
        }
        return false;
    }
    
    public int getNextProductId() {
        return dao.getNextProductIdByMax();
    }
    
    public Double getPriceById(int pid) {
        return getProductById(pid).getPrice();
    }
    
    public int selectCategoryIdByCategoryName(String name) {
        return dao.selectCategoryIdByName(name);
    }
    
    public boolean isProductExists(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return false;
        }
        String searchName = productName.trim().toLowerCase();
        return list.stream().anyMatch(p -> p.getProductName().trim().toLowerCase().equals(searchName));
    }
    
    public int getIdByName(String pname){
        for (int i = 0; i < list.size(); i++) {
            if (pname.equals(list.get(i).getProductName())){
                return list.get(i).getProductId();
            }
        }
        return -1;
    }
    
    
    
}