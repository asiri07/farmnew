/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.msd.fixAssetRegister.reportManager;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author MsD
 */
@Component
public class DBFacade {
   // private static final Logger logger = Logger .getLogger(DBFacade.class);
    private  Connection c = null;

    Properties prop = new Properties();
    InputStream input = null;


    public DBFacade() {
     }

    public  Connection connect() {
        try {
            String fileName = "application.properties";
            input = getClass().getClassLoader().getResourceAsStream(fileName);
            prop.load(input);
            String host =prop.getProperty("db.host");
            String port =prop.getProperty("db.port");
            String username =prop.getProperty("spring.datasource.username");
            String password =prop.getProperty("spring.datasource.password");
            String database =prop.getProperty("db.database");

            if (c == null || c.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
                c = (Connection) DriverManager.getConnection(url, username, password);
            }
        } catch (CommunicationsException ex) {
           // logger.error("This is Bank-Key Error message", ex);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public  void save(String sql)throws Exception{
        if(c==null)connect();
        System.out.println(sql);
        try{
            c.createStatement().executeUpdate(sql);
        }
        catch(Exception ex){

        }
    }

    public ResultSet fetch(String sql) throws Exception{
        if(c==null)connect();
        System.out.println(sql);
        return c.createStatement().executeQuery(sql);
    }
    public PreparedStatement psmt(String sql) throws Exception{
        if(c==null)connect();
        System.out.println(sql);
        return (PreparedStatement)c.prepareStatement(sql);
    }

}
