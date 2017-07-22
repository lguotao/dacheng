package com.dacheng.mes.sfdcs.test;

import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  

  
public class PostgreSQLDao {  
    // JDBC方式连接GreenPlum  
  
    public static void main(String[] args) {  
        Connection con = null; 
        try {  
        	String item = "sds2&232&";
    		System.out.println(item);
    		String aitem = item.replaceAll("&", "\n");
    		System.out.println(aitem);
            	con = DBUtil.getConnection();  
                String sql = "insert into sfds_config(sfds_id , line_name)" + "  values ('cdctest_"+ aitem+ "' ,"+" 'cdctest_"+aitem+"');" ;
                System.out.println(sql);
              PreparedStatement pstmt = con.prepareStatement(sql);  
           //   ResultSet rs = pstmt.executeQuery();  
             /* while (rs.next()) {  
                  System.out.println("total:" + rs.getInt("sfds_key"));  
              }  */
           //   rs.close();  
              pstmt.close();  
            con.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }   
    }  
}  