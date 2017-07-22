package com.dacheng.mes.sfdcs.test;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
public class DBUtil {  
    private static final String URL = "jdbc:postgresql://172.26.100.12:5432/mes_dev1"; //JDBC连接URL  
    private static final String USR = "mdcs"; //用户名  
    private static final String PWD = "mdcs"; //密码  
      
    static {  
        try {  
            //加载驱动  
            Class.forName("org.postgresql.Driver");  
        } catch (ClassNotFoundException e) {  
            System.err.println("驱动加载出错！");  
            e.printStackTrace();  
        }  
    }  
      
    public static Connection getConnection() {  
        Connection con = null;  
          
        try {  
            con = DriverManager.getConnection(URL , USR , PWD);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        };  
          
        return con;  
    }  
}  