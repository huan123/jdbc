package com.imooc.db;

import java.beans.Statement;
import java.sql.*;

/**
 * Created by huan on 2015/5/6.
 */
public class DUBtil {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/imooc";
    private  static  final  String USER="root";
    private  static final String PASSWORD="123456";
    private static Connection conn = null;


   static  {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.获得数据库连接
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }

    public static void   main(String[] args) throws ClassNotFoundException, SQLException {

        //3.获得数据库的连接操作数据库，实现增删改查

        java.sql.Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select user_name,age from imooc_goddess");

        while (rs.next())
        {
            System.out.println(rs.getString("user_name")+","+rs.getString("age"));

        }



    }
}
