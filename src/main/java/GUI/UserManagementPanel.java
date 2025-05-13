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
    private JLabel title, lblsearch, lblid, lbleid,lblusername,lblpassword,lblrole;
    private UsersBUS usersbus=new UsersBUS();
    private RolesBUS rolesbus=new RolesBUS();
    private RolePermissionsBUS rpbus=new RolePermissionsBUS();
    private PermissionsBUS permissionbus = new PermissionsBUS();
    public UserManagementPanel(int crid){
        this.setLayout(null);
        title = new JLabel("Quản lý người dùng");
        title.setBounds(500,30,400,50);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBackground(new Color(55,71,79));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setOpaque(true);
        
        String[] columns={"ID","EID","Username","Password","Chức vụ"};
        tbmodel=new DefaultTableModel(columns,0);
        tbuser=new JTable(tbmodel);
        tbuser.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        tbuser.setFont(new Font("Arial", Font.PLAIN, 18));
        tbuser.setRowHeight(30);
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
        scrPn.setBounds(100, 370, 1300, 500);
        loadUsers();
        
        lblsearch = new JLabel("Tìm kiếm theo:");
        lblsearch.setBounds(70, 100, 200, 50);
        lblsearch.setFont(new Font("Arial", Font.PLAIN, 20));
        
        cbsearch=new JComboBox<>();
        for(int i=0;i<columns.length;i++){
            if(!"Password".equals(columns[i])) {
                cbsearch.addItem(columns[i]);
            }
        }
        cbsearch.setBounds(250, 100, 150, 50);
        cbsearch.setFont(new Font("Arial", Font.PLAIN, 18));
        
        txtsearch=new JTextField();
        txtsearch.setBounds(450, 100, 300, 50);
        txtsearch.setFont(new Font("Arial", Font.PLAIN, 16));
        
        btnsearch = new JButton("Tìm kiếm");
        btnsearch.setBounds(800, 100, 100, 50);
        btnsearch.addActionListener(e -> searchuser());
        btnsearch.setFont(new Font("Arial", Font.PLAIN, 16));
        btnsearch.setBackground(new Color(55,71,79));
        btnsearch.setForeground(Color.white);
        
        btnadd= new JButton("Thêm");
        btnadd.setBounds(1150, 100, 100, 40);
        btnadd.addActionListener(e -> adduser());
        btnadd.setFont(new Font("Arial", Font.PLAIN, 16));
        btnadd.setBackground(new Color(55,71,79));
        btnadd.setForeground(Color.white);

        btndel=new JButton("Xóa");
        btndel.setBounds(1150, 160, 100, 40);
        btndel.addActionListener(e -> deleteuser());
        btndel.setFont(new Font("Arial", Font.PLAIN, 16));
        btndel.setBackground(new Color(55,71,79));
        btndel.setForeground(Color.white);
                    
        btnedit=new JButton("Sửa");
        btnedit.setBounds(1150, 220, 100, 40);
        btnedit.addActionListener(e -> savechange());
        btnedit.setFont(new Font("Arial", Font.PLAIN, 16));
        btnedit.setBackground(new Color(55,71,79));
        btnedit.setForeground(Color.white);
        
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
        lblid.setBounds(100, 200, 100, 40);
        lblid.setFont(new Font("Arial", Font.PLAIN, 18));
        
        txtid=new JTextField();
        txtid.setBounds(220, 200, 150, 40);
        txtid.setEditable(false);
        txtid.setFont(new Font("Arial", Font.PLAIN, 18));
        
        lblusername = new JLabel("Username");
        lblusername.setBounds(100, 280, 100, 40);
        lblusername.setFont(new Font("Arial", Font.PLAIN, 18));
        
        txtname=new JTextField();
        txtname.setBounds(220, 280, 150, 40);
        txtname.setFont(new Font("Arial", Font.PLAIN, 18));
        
        lblpassword=new JLabel("Password");
        lblpassword.setBounds(450, 200, 100, 40);
        lblpassword.setFont(new Font("Arial", Font.PLAIN, 18));
        
        txtpass=new JPasswordField();
        txtpass.setBounds(570, 200, 150, 40);
        txtpass.setFont(new Font("Arial", Font.PLAIN, 18));
        
        lblrole=new JLabel("Role");
        lblrole.setBounds(450, 280, 100, 40);
        lblrole.setFont(new Font("Arial", Font.PLAIN, 18));
        
        cbrole=new JComboBox();
        cbrole.addItem(new Roles(0, "role"));
        loadroles(cbrole);
        cbrole.setBounds(570, 280, 150, 40);
        cbrole.setFont(new Font("Arial", Font.PLAIN, 18));
        
        lbleid = new JLabel("EID");
        lbleid.setBounds(800, 200, 100, 40);
        lbleid.setFont(new Font("Arial", Font.PLAIN, 18));
        
        txteid= new JTextField();
        txteid.setBounds(920, 200, 150, 40);
        txteid.setFont(new Font("Arial", Font.PLAIN, 18));
        
        this.add(title);
        this.add(scrPn);
        this.add(lblsearch);
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
        this.setBackground(Color.white);
    }
    private void loadUsers(){
        ArrayList<Users> users= usersbus.getALL();
        ArrayList<Roles> roles=rolesbus.getAll();
        tbmodel.setRowCount(0);
        for(Users user:users){
            String rolename = rolesbus.searchrolename(user.getRole_id());
            tbmodel.addRow(new Object[]{user.getUser_id(),user.getEmployee_id(),user.getUsername(),user.getPassword(),rolename});
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
        gbc.insets = new Insets(15, 20, 15, 20);
        JLabel lb1=new JLabel("Username");
        lb1.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb1,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JLabel lb2=new JLabel("EID");
        lb2.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb2,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JLabel lb3=new JLabel("Password");
        lb3.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb3,gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JLabel lb4=new JLabel("Confirm password");
        lb4.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb4,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JLabel lb5=new JLabel("Role");
        lb5.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb5,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JTextField txt1=new JTextField();
        txt1.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(txt1,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JTextField txt2=new JTextField();
        txt2.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(txt2,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JPasswordField txt3= new JPasswordField();
        txt3.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(txt3,gbc);
        
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JPasswordField txt4=new JPasswordField();
        txt4.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(txt4,gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridwidth=1;
        JComboBox comboadd=new JComboBox<>();
        comboadd.setFont(new Font("Arial", Font.PLAIN, 18));
        loadroles(comboadd);
        dialog.add(comboadd,gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.insets=new Insets(15, 20, 15, 20);
        JButton btn1=new JButton("Confirm");
        btn1.setBackground(new Color(61,205,128));
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Arial", Font.PLAIN, 18));
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
        gbc.insets=new Insets(15, 20, 15, 20);
        JButton btn2 = new JButton("Cancel");
        btn2.setBackground(new Color(244,67,54));
        btn2.setFont(new Font("Arial", Font.PLAIN, 18));
        btn2.setForeground(Color.white);
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
        gbc.insets=new Insets(15, 20, 15, 20);
        JLabel lb1 = new JLabel("Nhập ID người dùng muốn xóa:");
        lb1.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(lb1,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets=new Insets(15, 20, 15, 20);
        JTextField txt1 = new JTextField();
        txt1.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(txt1,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JButton btn1 = new JButton("Confirm");
        btn1.setBackground(new Color(61,205,128));
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Arial", Font.PLAIN, 18));
        dialog.add(btn1,gbc);
        btn1.addActionListener(e -> {
            String id = txt1.getText().trim();
            if(id.isEmpty()){
                JOptionPane.showMessageDialog(dialog, "Bạn chưa nhập ID", "Thiếu thông tin", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int idInt;
            try {
                idInt = Integer.parseInt(id);
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "ID phải là số nguyên.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(dialog, "Tài khoản đã được xóa!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
            usersbus.deleteUser(idInt);
            loadUsers();
        });
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.insets=new Insets(15, 20, 15, 20);
        JButton btn2 = new JButton("Cancel");
        btn2.setFont(new Font("Arial", Font.PLAIN, 18));
        btn2.setBackground(new Color(244,67,54));
        btn2.setForeground(Color.white);
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
