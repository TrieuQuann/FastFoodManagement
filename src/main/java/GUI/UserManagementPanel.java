/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import BUS.PermissionsBUS;
import BUS.RolePermissionsBUS;
import BUS.RolesBUS;
import BUS.UsersBUS;
import DTO.RolePermissions;
import DTO.Roles;
import DTO.Users;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo
 */
public class UserManagementPanel extends JPanel{
    private JTable tbuser;
    private JComboBox cbsearch,cbrole;
    private DefaultTableModel tbmodel;
    private JTextField txtsearch,txtid,txteid,txtname;
    private JPasswordField txtpass;
    private JButton btnadd,btndel,btnedit,btnsearch;
    private JLabel title,lblid, lbleid,lblusername,lblpassword,lblrole;
    private UsersBUS usersbus=new UsersBUS();
    private RolesBUS rolesbus=new RolesBUS();
    private RolePermissionsBUS rpbus=new RolePermissionsBUS();
    private PermissionsBUS permissionbus = new PermissionsBUS();
    public UserManagementPanel(int crid){
        this.setLayout(null);
        title = new JLabel("Quản lý người dùng");
        title.setBounds(550, 0, 200, 40);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        
        String[] columns={"ID","EID","Username","Password","Chức vụ"};
        tbmodel=new DefaultTableModel(columns,0);
        tbuser=new JTable(tbmodel);
        tbuser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbuser.getSelectedRow();

                if (selectedRow != -1) {
                    String id = tbuser.getValueAt(selectedRow, 0).toString();
                    String eid= tbuser.getValueAt(selectedRow, 1).toString();
                    String username = tbuser.getValueAt(selectedRow, 2).toString();
                    String password = tbuser.getValueAt(selectedRow, 3).toString();
                    String rolename = tbuser.getValueAt(selectedRow, 4).toString();
                    for (int i=0;i<cbrole.getItemCount();i++){
                        Roles role = (Roles) cbrole.getItemAt(i);
                        if(role.getRolename().equals(rolename)){
                            cbrole.setSelectedIndex(i);
                            break;
                        }
                    }

                    txtid.setText(id);
                    txteid.setText(eid);
                    txtname.setText(username);
                    txtpass.setText(password);
                }
            }
        });
        JScrollPane scrPn=new JScrollPane(tbuser);
        scrPn.setBounds(50, 200, 1200, 500);
        loadUsers();
        
        cbsearch=new JComboBox<>();
        for(int i=0;i<columns.length;i++){
            cbsearch.addItem(columns[i]);
        }
        cbsearch.setBounds(100, 60, 130, 30);
        
        txtsearch=new JTextField();
        txtsearch.setBounds(240, 60, 300, 30);
        
        btnsearch = new JButton("Tìm kiếm");
        btnsearch.setBounds(540, 60, 100, 30);
        btnsearch.addActionListener(e -> searchuser());
        
        btnadd= new JButton("Thêm");
        btnadd.setBounds(1050, 50, 100, 30);
        btnadd.addActionListener(e -> adduser());

        btndel=new JButton("Xóa");
        btndel.setBounds(1050, 100, 100, 30);
        btndel.addActionListener(e -> deleteuser());
                    
        btnedit=new JButton("Sửa");
        btnedit.setBounds(1050, 150, 100, 30);
        btnedit.addActionListener(e -> savechange());
                    
        btnedit=new JButton("Sửa");
        btnedit.setBounds(1050, 150, 100, 30);
        btnedit.addActionListener(e -> savechange());
        
        int perid= permissionbus.searchpermissionid("Quản lý tài khoản");
        ArrayList<RolePermissions> rps = rpbus.getbyID(crid);
        for (RolePermissions rp : rps){
            if(rp.getPermission_id()==perid)
            switch(rp.getProp()){
                case "create":
                    this.add(btnadd);
                    break;
                case "delete":
                    this.add(btndel);
                    break;
                case "update":
                    this.add(btnedit);
                    break;
            }
        }
        
        lblid=new JLabel("Id");
        lblid.setBounds(100, 100, 100, 30);
        
        txtid=new JTextField();
        txtid.setBounds(220, 100, 100, 30);
        txtid.setEditable(false);
        
        lblusername = new JLabel("Username");
        lblusername.setBounds(100, 150, 100, 30);
        
        txtname=new JTextField();
        txtname.setBounds(220, 150, 100, 30);
        
        lblpassword=new JLabel("Password");
        lblpassword.setBounds(370, 100, 100, 30);
        
        txtpass=new JPasswordField();
        txtpass.setBounds(490, 100, 100, 30);
        
        lblrole=new JLabel("Role");
        lblrole.setBounds(370, 150, 100, 30);
        
        cbrole=new JComboBox();
        cbrole.addItem(new Roles(0, "role"));
        loadroles(cbrole);
        cbrole.setBounds(490, 150, 100, 30);
        
        lbleid = new JLabel("EID");
        lbleid.setBounds(660, 100, 100, 30);
        
        txteid= new JTextField();
        txteid.setBounds(780, 100, 100, 30);
        
        this.add(title);
        this.add(scrPn);
        this.add(cbsearch);
        this.add(txtsearch);
        this.add(btnsearch);
        this.add(lblid);
        this.add(lblusername);
        this.add(lblpassword);
        this.add(lblrole);
        this.add(txtid);
        this.add(txtname);
        this.add(txtpass);
        this.add(cbrole);
        this.add(lbleid);
        this.add(txteid);
        this.setBackground(Color.LIGHT_GRAY);
    }
    private void loadUsers(){
        ArrayList<Users> users= usersbus.getALL();
        ArrayList<Roles> roles=rolesbus.getAll();
        tbmodel.setRowCount(0);
        for(Users user:users){
            String rolename = rolesbus.searchrolename(user.getRole_id());
            tbmodel.addRow(new Object[]{user.getEmployee_id(),user.getEmployee_id(),user.getUsername(),user.getPassword(),rolename});
        }
    }
    private void adduser() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Thêm tài khoản", true);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.setLocationRelativeTo(this);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.insets = new Insets(10, 10, 5, 10);
        JLabel lb1=new JLabel("Username");
        dialog.add(lb1,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JLabel lb2=new JLabel("EID");
        dialog.add(lb2,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JLabel lb3=new JLabel("Password");
        dialog.add(lb3,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JLabel lb4=new JLabel("Confirm password");
        dialog.add(lb4,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JLabel lb5=new JLabel("Role");
        dialog.add(lb5,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JTextField txt1=new JTextField();
        dialog.add(txt1,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JTextField txt2=new JTextField();
        dialog.add(txt2,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JPasswordField txt3= new JPasswordField();
        dialog.add(txt3,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JPasswordField txt4=new JPasswordField();
        dialog.add(txt4,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JComboBox comboadd=new JComboBox<>();
        loadroles(comboadd);
        dialog.add(comboadd,gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.insets=new Insets(10, 10, 5, 10);
        JButton btn1=new JButton("Confirm");
        btn1.addActionListener(e -> {
            String username = txt1.getText().trim();
            String eid = txt2.getText().trim();
            String password = new String(txt3.getPassword());
            String cfpassword = new String(txt4.getPassword());
            Roles slectedrole =(Roles) comboadd.getSelectedItem();
            if (username.isEmpty() || eid.isEmpty() || password.isEmpty() || cfpassword.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int eidInt;
            try {
                eidInt = Integer.parseInt(eid);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "EID phải là số nguyên.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!password.equals(cfpassword)){
                JOptionPane.showMessageDialog(dialog, "Mật khẩu xác nhận không khớp.", "Lỗi mật khẩu", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(dialog, "Tài khoản đã được tạo!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            Users user = new Users(-1, slectedrole.getRole_id(), Integer.parseInt(eid), username, password);
            usersbus.addUser(user);
            loadUsers();
            dialog.dispose();
        });
        dialog.add(btn1,gbc);
        
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.insets=new Insets(10, 10, 5, 10);
        JButton btn2 = new JButton("Cancel");
        btn2.addActionListener(e -> {
            dialog.dispose();
        });
        dialog.add(btn2,gbc);
        
        dialog.pack();
        dialog.setVisible(true);
    }
    
    private void deleteuser(){
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this),"Xóa người dùng",true);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets=new Insets(10, 10, 5, 10);
        JLabel lb1 = new JLabel("Nhập ID người dùng muốn xóa:");
        dialog.add(lb1,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets=new Insets(10, 10, 5, 10);
        JTextField txt1 = new JTextField();
        dialog.add(txt1,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JButton btn1 = new JButton("Confirm");
        dialog.add(btn1,gbc);
        btn1.addActionListener(e -> {
            String eid = txt1.getText().trim();
            if(eid.isEmpty()){
                JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập EID", "Thiếu thông tin", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int eidInt;
            try {
                eidInt = Integer.parseInt(eid);
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "EID phải là số nguyên.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(dialog, "Tài khoản đã được xóa!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
            usersbus.deleteUser(eidInt);
            loadUsers();
        });
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(10, 10, 5, 10);
        JButton btn2 = new JButton("Cancel");
        dialog.add(btn2,gbc);
        btn2.addActionListener(e -> {
            dialog.dispose();
        });
        
        
        dialog.pack();
        dialog.setVisible(true);
    }

    private void loadroles(JComboBox cb) {
        ArrayList<Roles> roles= rolesbus.getAll();
        for(Roles role : roles){
            cb.addItem(role);
        }    
    }
    private void savechange(){
        String newid = txtid.getText().trim();
        String neweid = txteid.getText().trim();
        String newpassword = new String(txtpass.getPassword());
        String newusername = txtname.getText().trim();
        Roles newrole =(Roles) cbrole.getSelectedItem();
        if(newid.isEmpty()||neweid.isEmpty()||newpassword.isEmpty()||newusername.isEmpty()||cbrole.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Thông tin không đầy đủ không thể lưu");
            return;
        }
        int eidInt,idInt;
        try {
            eidInt = Integer.parseInt(neweid);
            idInt = Integer.parseInt(newid);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "EID phải là số nguyên.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Users user=new Users(idInt, newrole.getRole_id(), eidInt , newusername, newpassword);
        usersbus.updateUser(user);
        JOptionPane.showMessageDialog(null, "Sửa thành công");
        loadUsers();
    }
    private void searchuser(){
        String selectedfield = cbsearch.getSelectedItem().toString();
        String keyword = txtsearch.getText().trim();
        if(keyword.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui long nhập từ khóa tìm kiếm");
            return;
        }
        
        ArrayList<Users> users = usersbus.getALL();
        ArrayList<Roles> roles = rolesbus.getAll();
        
        tbmodel.setRowCount(0);
        for(Users user : users){
            String rolename = rolesbus.searchrolename(user.getRole_id());
            boolean matched = false;
            switch (selectedfield) {
                case "ID":
                    matched = String.valueOf(user.getUser_id()).toLowerCase().contains(keyword);
                    break;
                case "EID":
                    matched = String.valueOf(user.getEmployee_id()).toLowerCase().contains(keyword);
                    break;
                case "Username":
                    matched = user.getUsername().toLowerCase().contains(keyword);
                    break;
                case "Password":
                    matched = user.getPassword().toLowerCase().contains(keyword);
                    break;
                case "Chức vụ":
                    matched = rolename.toLowerCase().contains(keyword);
                    break;
            }

            if (matched) {
                tbmodel.addRow(new Object[]{
                    user.getUser_id(),
                    user.getEmployee_id(),
                    user.getUsername(),
                    user.getPassword(),
                    rolename
                });
            }
        }
    }
}
