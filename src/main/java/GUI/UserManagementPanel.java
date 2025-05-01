/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.RolesBUS;
import BUS.UsersBUS;
import DTO.Roles;
import DTO.Users;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo
 */
public class UserManagementPanel extends JPanel{
    private JTable tbuser;
    private DefaultTableModel tbmodel;
    private JButton btnadd,btndel,btnedit;
    private UsersBUS usersbus=new UsersBUS();
    private RolesBUS rolesbus=new RolesBUS();
    public UserManagementPanel(){
        String[] columns={"ID","Username","Password","Vai trò"};
        tbmodel=new DefaultTableModel(columns,0);
        tbuser=new JTable(tbmodel);
        JScrollPane scrPn=new JScrollPane(tbuser);
        loadUsers();
        this.add(scrPn);
        this.setSize(700, 500);
        this.setBackground(Color.red);
    }
    private void loadUsers(){
        ArrayList<Users> users= usersbus.getALL();
        ArrayList<Roles> roles=rolesbus.getAll();
        tbmodel.setRowCount(0);
        for(Users user:users){
            String rolename = rolesbus.searchrolename(user.getRole_id());
            tbmodel.addRow(new Object[]{user.getEmployee_id(),user.getUsername(),user.getPassword(),rolename});
        }
    }
    public static void main(String[] args){
        JFrame f=new JFrame();
        
        UserManagementPanel u=new UserManagementPanel();
        f.add(u);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
    }
}
