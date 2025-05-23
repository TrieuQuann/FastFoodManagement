package DAO;

import DTO.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("eid"),
                    rs.getInt("position_id"),
                    rs.getString("employee_name"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("gender")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employee (position_id, employee_name, phone, address, gender) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp.getPosition_id());
            ps.setString(2, emp.getEmployee_name());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getGender());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET position_id=?, employee_name=?, phone=?, address=?, gender=? WHERE eid=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp.getPosition_id());
            ps.setString(2, emp.getEmployee_name());
            ps.setString(3, emp.getPhone());
            ps.setString(4, emp.getAddress());
            ps.setString(5, emp.getGender());
            ps.setInt(6, emp.getEmployee_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int eid) {
        String sql = "DELETE FROM employee WHERE eid=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, eid);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> searchEmployees(String column, String keyword) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee(
                        rs.getInt("eid"),
                        rs.getInt("position_id"),
                        rs.getString("employee_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("gender")
                    );
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee getEmployeeById(int employee_id) {
        String sql = "SELECT * FROM employee WHERE eid=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employee_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("eid"),
                        rs.getInt("position_id"),
                        rs.getString("employee_name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("gender")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}