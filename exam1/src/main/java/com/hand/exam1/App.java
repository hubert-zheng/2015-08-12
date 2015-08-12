package com.hand.exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        
    	Scanner sc = new Scanner(System.in);
    	System.out.println("请输入要连接的本地数据库名称：");
    	String dbname = sc.next();
    	System.out.println("请输入连接数据库的用户号：");
    	String dbuser = sc.next();
    	System.out.println("请输入连接数据库的用户密码：");
    	String pwd = sc.next();
    	System.out.println("请输入Country ID:"); 
    	int countryid = sc.nextInt();
    	try {
    		
    		Class. forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname,dbuser,pwd);
        	String sql ="select cou.country,ci.city_id,ci.city from country cou left join city ci on ci.country_id = cou.country_id where cou.country_id = " + countryid;
			
        	st = conn.createStatement();
        	rs = st.executeQuery(sql);
			
			
			System.out.println("城市ID |城市名称");
			
			while (rs.next()) {
				System.out.println("Country " + rs.getString("country") + "的城市 ->");
				System.out.println(rs.getInt("city_id") + "|" + rs.getString("city"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				st.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			
		}
    
    }
}
