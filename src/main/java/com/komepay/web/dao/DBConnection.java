package com.komepay.web.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public  static Connection conn = null;


    public static Connection getConnection(){

        if(conn==null){
            conn = createConnection();
        }
        return conn;
    }

    public static Connection createConnection(){
        Connection connect = null;
        String url = "jdbc:mysql://127.0.0.1:3306/vertx";
        String user = "root";
        String password = "";

        try{
            try{

                Class.forName("com.mysql.jdbc.Driver");

            }catch(ClassNotFoundException e){

                e.printStackTrace();
            }
            connect = DriverManager.getConnection(url,user,password);



        }catch(Exception e){
            e.printStackTrace();
        }

        return connect;

    }
}
