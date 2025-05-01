/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
//import java.awt.*;
import java.sql.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import DAO.ConnectionDB;
import java.awt.*;
import java.sql.PreparedStatement;

/**
 *
 * @author LENOVO
 */
public class FormSignIn extends JFrame{
    private JLabel lblForm,lblUsername,lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    public FormSignIn(){
        lblForm= new JLabel("Sign In");
        lblForm.setBounds(150,0,100,30);
        lblForm.setHorizontalAlignment(SwingConstants.CENTER);
        lblForm.setFont(new Font("Calibri",Font.PLAIN,20));
        lblUsername=new JLabel("Username");
        lblUsername.setBounds(100,40,100,30);
        lblPassword=new JLabel("Password");
        lblPassword.setBounds(100,120,100,30);
        txtUsername=new JTextField();
        txtUsername.setBounds(100,80,200,30);
        txtPassword=new JPasswordField();
        txtPassword.setBounds(100,160,200,30);
        btnLogin=new JButton("Sign in");
        btnLogin.setBounds(150,200,100,30);
        btnLogin.addActionListener(e->login());
        this.add(lblForm);
        this.add(lblUsername);
        this.add(txtUsername);
        this.add(lblPassword);
        this.add(txtPassword);
        this.add(btnLogin);
        this.setTitle("Đăng nhập");
        this.setSize(400,300);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void login(){
        String username = txtUsername.getText();
        String password= new String(txtPassword.getPassword());
        try(Connection conn=ConnectionDB.getConnection()){
            String sql="SELECT * FROM users WHERE username = ? AND paswd = ?";
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2, password);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                System.out.println("Success");
                dispose();
                new MainFrame(rs.getInt("role_id"));
            }
            else {
                JOptionPane.showMessageDialog(this, "Wrong username or password");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        FormSignIn form =new FormSignIn();
    }
}
