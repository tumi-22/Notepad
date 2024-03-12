/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.ui;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class DBHandler extends Thread{
    private String message;
    private Connection connect;
    
    public DBHandler(String message,String url,String username,String pass) throws SQLException {
        this.message = message;
        this.connect=getConnection(url, username, pass);
        start();
    }
    private Connection getConnection(String url,String username,String pass) throws SQLException{
        Connection con=DriverManager.getConnection(url, username, pass);
        return con;
    }
    @Override
    public void run() {
        Random rand=new Random();
        try {
            String sql="INSERT INTO NOTESTB VALUES(?,?)";
            PreparedStatement ps=connect.prepareStatement(sql);
            int id=rand.nextInt(0, 10);
            ps.setInt(1, id);
            ps.setString(2, message);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
