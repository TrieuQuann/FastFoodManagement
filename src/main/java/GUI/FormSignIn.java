/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.Hasher;
import javax.swing.*;
//import java.awt.*;
import java.sql.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import DAO.ConnectionDB;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

/**
 *
 * @author LENOVO
 */
public class FormSignIn extends JFrame {
    private JLabel lblForm, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public FormSignIn() {
        setTitle("Đăng nhập");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // padding

        // Title
        lblForm = new JLabel("Sign In");
        lblForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblForm.setForeground(new Color(55,71,79));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblForm, gbc);

        // Username label
        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblUsername, gbc);

        // Username field
        txtUsername = new JTextField(15);
        styleTextField(txtUsername);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(txtUsername, gbc);

        // Password label
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblPassword, gbc);

        // Password field
        txtPassword = new JPasswordField(15);
        styleTextField(txtPassword);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(txtPassword, gbc);

        // Login button
        btnLogin = new JButton("Sign In");
        btnLogin.setBackground(new Color(55,71,79));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e -> login());
        btnLogin.addMouseListener(new MouseAdapter(){
            @Override
                public void mouseEntered(MouseEvent e){
                    btnLogin.setBackground(new Color(61,205,128));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    btnLogin.setBackground(new Color(55,71,79));
                }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnLogin, gbc);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void styleTextField(JTextField field) {
        field.setPreferredSize(new Dimension(180, 30));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String hashpassword = Hasher.hashPassword(password);
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND paswd = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashpassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Success");
                dispose();
                new MainFrame(rs.getInt("role_id"), rs.getString("username"));
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FormSignIn();
    }
}