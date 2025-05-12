/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author LENOVO
 */
public class Hasher {
    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // có thể dùng SHA-512 hoặc BCrypt nếu cần mạnh hơn
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b)); // chuyển từng byte thành hex
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không hỗ trợ thuật toán hash", e);
        }
    }
}
