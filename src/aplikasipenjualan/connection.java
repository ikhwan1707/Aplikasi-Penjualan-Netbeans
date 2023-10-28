/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasipenjualan;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author utama digitall2
 */
public class connection {
    public static Connection connection;
    
    public static Connection getKoneksi()
    {
        // cek apakah koneksi null 
        if (connection == null)
        {
            try {
                String url = "jdbc:mysql://localhost:3306/db_penjualan_barang_pas?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
                String user = "root";
                String password = ""; 
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); 
                connection = DriverManager.getConnection(url, user, password);
                System.out.print("Berhasil Membuat Koneksi");
            } catch (SQLException t) { 
                System.out.println("Error Membuat Koneksi"+ t.getMessage());
            }
        }
        return connection;
    } 
}
