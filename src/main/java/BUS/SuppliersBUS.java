package BUS;

import DAO.SuppliersDAO;
import DTO.Suppliers;
import java.util.List;

public class SuppliersBUS {
    private SuppliersDAO suppliersDAO;

    public SuppliersBUS() {
        this.suppliersDAO = new SuppliersDAO();
    }

    // lấy toàn bộ nhà cung cấp
    public List<Suppliers> getAllSuppliers() {
        return suppliersDAO.getAllSuppliers();
    }

    // thêm mới nhà cung cấp
    public boolean addSupplier(Suppliers supplier) {
        return suppliersDAO.addSuppliers(supplier);
    }

    // sửa thông tin nhà cung cấp
    public boolean updateSupplier(Suppliers supplier) {
        return suppliersDAO.updateSuppliers(supplier);
    }

    // xóa nhà cung cấp bằng id
    public boolean deleteSupplier(int supplierId) {
        return suppliersDAO.deleteSuppliers(supplierId);
    }

    // tìm kiếm nhà cung cấp theo tên, email, địa chỉ, số điện thoại
    public List<Suppliers> searchSuppliers(String column, String keyword) {
        // Validate column name to prevent SQL injection
        if (!column.equals("supplier_id") && !column.equals("supplier_name") &&
            !column.equals("email") && !column.equals("address") && !column.equals("phone")) {
            throw new IllegalArgumentException("Invalid column name: " + column);
        }
        return suppliersDAO.searchSuppliers(column, keyword);
    }

    // lấy nhà cung cấp theo id
    public Suppliers getSupplierById(int supplierId) {
        return suppliersDAO.getSuppliersById(supplierId);
    }
}
