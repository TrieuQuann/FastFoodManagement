/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PermissionsBUS;
import BUS.RolesBUS;
import DAO.RolePermissionsDAO;
import DTO.Permissions;
import DTO.RolePermissions;
import DTO.Roles;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class DecentralizedManagementPanel extends JPanel{
    private JLabel lbltitle;
    private JComboBox<Roles> cbrole;
    private JTable tbdc;
    private JScrollPane tbdcscroll;
    private DefaultTableModel tbdcmodel;
    private JButton btnsave,btnaddnewper,btndelper,btnaddrole,btndelrole;
    private PermissionsBUS permissionbus=new PermissionsBUS();
    private RolesBUS rolesbus=new RolesBUS();
    private int currentroleID = -1;
    public DecentralizedManagementPanel(){
        lbltitle=new JLabel("Quản lý phân quyền");
        lbltitle.setBounds(250,0,200,40);
        lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        String[] columns={"Chức năng","Thêm","Sửa","Xóa","Xem"};
        tbdcmodel=new DefaultTableModel(columns,0){
            @Override
            public Class<?> getColumnClass(int column){
                return column==0 ? String.class : Boolean.class;
            }
            public boolean isCellEditabel(int row,int column){
                return column > 0;
            }
        };
        tbdc=new JTable(tbdcmodel);
        tbdcscroll=new JScrollPane(tbdc);
        tbdcscroll.setBounds(10, 100, 670, 400);
        
        cbrole = new JComboBox<>();
        cbrole.addActionListener(e->{
            Roles selected=(Roles) cbrole.getSelectedItem();
            if(selected!=null){
                currentroleID=selected.getRole_id();
                loadPermission();
            }
        });
        cbrole.setBounds(30, 50, 150, 30);
        
        btnsave=new JButton("Lưu phân quyền");
        btnsave.addActionListener(e->savePermission());
        btnsave.setBounds(300, 30, 100, 30);
        btnsave.setBackground(Color.blue);
        
        btnaddnewper=new JButton("Thêm quyền");
        btnaddnewper.addActionListener(e->addpermission());
        btnaddnewper.setBounds(400, 30, 100, 30);
        btnaddnewper.setBackground(Color.CYAN);
        
        btndelper=new JButton("Xóa quyền");
        btndelper.addActionListener(e -> delpermission());
        btndelper.setBounds(400, 60, 100, 30);
        btndelper.setBackground(Color.CYAN);
        
        btnaddrole=new JButton("Thêm nhóm quuyền");
        btnaddrole.addActionListener(e->addrole());
        btnaddrole.setBounds(500, 30, 100, 30);
        btnaddrole.setBackground(Color.DARK_GRAY);
        
        btndelrole=new JButton("Xóa nhóm quuyền");
        btndelrole.addActionListener(e->delrole());
        btndelrole.setBounds(500, 60, 100, 30);
        btndelrole.setBackground(Color.DARK_GRAY);
        
        loadRole();
        
        //this.setBackground(Color.blue);
        this.setSize(700, 500);
        this.add(lbltitle);
        this.add(cbrole);
        this.add(tbdcscroll);
        this.add(btnsave);
        this.add(btnaddnewper);
        this.add(btndelper);
        this.add(btnaddrole);
        this.add(btndelrole);
        this.setLayout(null);
    }
    public static void main(String[] args){
        JFrame f=new JFrame();
        DecentralizedManagementPanel d=new DecentralizedManagementPanel();
        f.add(d);
        f.setVisible(true);
        f.setSize(700, 500);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void savePermission() {
        if (currentroleID==-1)
            return;
        RolePermissionsDAO.deleteAllbyRoleId(currentroleID);
        for(int i = 0; i< tbdcmodel.getRowCount(); i++){
            int permissionId = permissionbus.getAll().get(i).getPermission_id();
            if((boolean) tbdcmodel.getValueAt(i, 1))
                RolePermissionsDAO.save(currentroleID, permissionId, "create");
            if((boolean) tbdcmodel.getValueAt(i, 2))
                RolePermissionsDAO.save(currentroleID, permissionId, "update");
            if((boolean) tbdcmodel.getValueAt(i, 3))
                RolePermissionsDAO.save(currentroleID, permissionId, "delete");
            if((boolean) tbdcmodel.getValueAt(i, 4))
                RolePermissionsDAO.save(currentroleID, permissionId, "read");
        }
        loadPermission();
        JOptionPane.showMessageDialog(this, "Saved");
    }

    private void loadPermission() {
        ArrayList<Permissions> permissions=permissionbus.getAll();
        ArrayList<RolePermissions> rolepermissions=RolePermissionsDAO.getRoleById(currentroleID);
        tbdcmodel.setRowCount(0);
        for(Permissions p : permissions){
            boolean[] rights = new boolean[4];
            
            for(RolePermissions rp : rolepermissions){
                if(rp.getPermission_id() == p.getPermission_id()){
                    switch(rp.getProp()){
                        case "create": rights[0] = true; break;
                        case "update": rights[1] = true; break;
                        case "delete": rights[2] = true; break;
                        case "read": rights[3] = true; break;
                    }
                }
            }
            tbdcmodel.addRow(new Object[]{p.getName(), rights[0],rights[1],rights[2],rights[3]});
        }
    }

    private void loadRole() {
        ArrayList<Roles> roles= rolesbus.getAll();
        cbrole.removeAllItems();
        for(Roles role : roles){
            cbrole.addItem(role);
        }
        if(!roles.isEmpty()){
            currentroleID=roles.get(0).getRole_id();
            loadPermission();
        }
    }

    private void addpermission() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Thêm quyền", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.setLocationRelativeTo(this);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.insets = new Insets(10, 10, 5, 10);
        JLabel label=new JLabel("Enter new permission name:");
        dialog.add(label,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.insets = new Insets(5, 10, 5, 10);
        JTextField txtfi=new JTextField(20);
        dialog.add(txtfi,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton confirm=new JButton("Confirm");
        confirm.addActionListener(e -> {
        String permissionName = txtfi.getText();
        if (!permissionName.isEmpty()) {
            Permissions per=new Permissions(-1, permissionName);
            permissionbus.addPermission(per);
            loadPermission();
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, "Please enter a permission name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
        dialog.add(confirm,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            dialog.dispose();
        });
        dialog.add(cancel,gbc);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void addrole() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Thêm nhóm quyền", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.setLocationRelativeTo(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.insets = new Insets(10, 10, 5, 10);
        dialog.add(new JLabel("Enter new role name:"),gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.insets = new Insets(5, 10, 5, 10);
        JTextField txtfi=new JTextField();
        dialog.add(txtfi,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton confirm=new JButton("Confirm");
        confirm.addActionListener(e -> {
        String roleName = txtfi.getText();
        if (!roleName.isEmpty()) {
            Roles role=new Roles(-1, roleName);
            rolesbus.addRole(role);
            loadRole();
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, "Please enter a role name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
        dialog.add(confirm,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton cancel= new JButton("Cancel");
        cancel.addActionListener(e -> {
            dialog.dispose();
        });
        dialog.add(cancel,gbc);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void delrole() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Xóa nhóm quyền", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.setLocationRelativeTo(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.insets = new Insets(10, 10, 5, 10);
        dialog.add(new JLabel("Enter role name to delete:"),gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.insets = new Insets(5, 10, 5, 10);
        JTextField txtfi=new JTextField();
        dialog.add(txtfi,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton confirm=new JButton("Confirm");
        confirm.addActionListener(e -> {
        String roleName = txtfi.getText();
        if (!roleName.isEmpty()) {
            int roid=rolesbus.searchroleid(roleName);
            if(roid>0){
            rolesbus.deleteRole(roid);
            loadRole();
            dialog.dispose();
            }
            else{
                JOptionPane.showMessageDialog(dialog, "This role name does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(dialog, "Please enter a role name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
        dialog.add(confirm,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            dialog.dispose();
        });
        dialog.add(cancel,gbc);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void delpermission() {
        System.out.println("xoa quyen");
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Xóa quyền", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        dialog.setLocationRelativeTo(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.insets = new Insets(10, 10, 5, 10);
        dialog.add(new JLabel("Enter permission name to delete:"),gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.insets = new Insets(5, 10, 5, 10);
        JTextField txtfi=new JTextField();
        dialog.add(txtfi,gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton confirm=new JButton("Confirm");
        confirm.addActionListener(e -> {
        String permissionName = txtfi.getText();
        if (!permissionName.isEmpty()) {
            int perid=permissionbus.searchpermissionid(permissionName);
            if(perid>0){
            permissionbus.deletePermission(perid);
            loadPermission();
            dialog.dispose();
            }
            else{
                JOptionPane.showMessageDialog(dialog, "This permission name does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(dialog, "Please enter a permission name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });
        dialog.add(confirm,gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(5, 15, 10, 15);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            dialog.dispose();
        });
        dialog.add(cancel,gbc);
        dialog.pack();
        dialog.setVisible(true);
    }
    
}
