package com.generation.projectwork114.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name="db.type",havingValue = "mysql")
public class Database implements IDatabase{

    @Value("${db.mysql.username}")
    private String username;

    @Value("${db.mysql.password}")
    private String password;
    @Value("${db.mysql.path}")
    private String path;
    @Value("${db.mysql.timezone}")
    private String timezone;
    @Value("${db.mysql.schema}")
    private String nomeDb;

    private Connection connection;

    public Database(){}

    @PostConstruct
    public void openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path+ nomeDb+timezone,username,password);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
    
    public void closeCommand(PreparedStatement ps, ResultSet rs) {
        try {
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int executeUpdate(String query, String... params) {
        openConnection();
        int row = 0; 
       PreparedStatement ps = null;
       ResultSet rs = null;
       try{
        String[] colums = {"id"};
        ps = connection.prepareStatement(query, colums);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i+1, params[i]);
        }

        row = ps.executeUpdate();
        rs = ps.getGeneratedKeys(); 
        if(rs.next()){
            row = rs.getInt(1);
        }
            return row;
        }catch (SQLException e) {
              e.printStackTrace();     
       }catch (Exception e) {
        e.printStackTrace();
       }finally{
           closeCommand(ps, rs);
           closeConnection();
       }
        return row;
    }

    
    @Override
    public List<Map<String, String>> executeQuery(String query, String... params) {
        openConnection();
        List<Map<String, String>> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> map = null;
        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setString(i+1, params[i]);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                map = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    String column = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(column);
                    System.out.println(column + " " + value);
                    map.put(column, value);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeCommand(ps, rs);
            closeConnection();
        }
        return result;
    }


    
}
