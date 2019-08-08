/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.UserBO;
import db.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author dany
 */
public class UsersManager {
    
    final String SELECTALL = "SELECT * FROM usuarios";
    final String SELECTBYID = "SELECT * FROM usuarios WHERE id = ?";
    final String CREATE = "INSERT INTO usuarios (nombre, email, password) VALUES (?,?,?)";
    final String UPDATE = "UPDATE user SET nombre = ?, email = ?, password = ? WHERE id = ?";
    
    public int create(UserBO u){
        int rs = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MysqlConnection.open();
            ps = conn.prepareStatement(this.CREATE);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            rs = ps.executeUpdate();
           
        } catch (SQLException ex){
            System.out.println("Error SQL"+ex.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null) 
                    ps.close();
            } catch (SQLException ex) {
                System.out.println("Error Closing"+ex.getMessage());
            }
        }
        return rs;
    }
    
    public int update(UserBO u){
        return 0;
    }
    
    public int delete(UserBO u){
        return 0;
    }
    
    public UserBO get(int id){
        UserBO u = null;
        
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MysqlConnection.open();
            ps = conn.prepareStatement(this.SELECTBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                u = new UserBO();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex){
            System.out.println("Error SQL"+ex.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null) 
                    ps.close();
            } catch (SQLException ex) {
                System.out.println("Error Closing"+ex.getMessage());
            }
        }
        
        return u;
    }
    
    public List<UserBO> getAll(){
        List<UserBO> lu = new ArrayList<>();
        
        try {
            Connection conn = MysqlConnection.open();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(this.SELECTALL);
            while(rs.next()){
                UserBO u = new UserBO();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                lu.add(u);
            }
        } catch (SQLException ex){
            System.out.println("Error SQL"+ex.getMessage());
        }
        return lu;
    }
    
}
