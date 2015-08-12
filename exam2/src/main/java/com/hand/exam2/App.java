package com.hand.exam2;

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
        boolean isouput=true;
        
    	Scanner sc = new Scanner(System.in);
    	System.out.println("请输入要连接的本地数据库名称：");
    	String dbname = sc.next();
    	System.out.println("请输入连接数据库的用户号：");
    	String dbuser = sc.next();
    	System.out.println("请输入连接数据库的用户密码：");
    	String pwd = sc.next();
    	System.out.println("Customer ID:"); 
    	int customerid = sc.nextInt();
    	try {
    		
    		Class. forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname,dbuser,pwd);
        	String sql ="select fi.title,inv.film_id,re.rental_date,cus.first_name,cus.last_name from customer cus"+
        			 " join payment p on cus.customer_id = p.customer_id"+
        			 " join rental re on re.rental_id = p.rental_id"+
        			 " join inventory inv on re.inventory_id = inv.inventory_id"+
        			 " join film fi on fi.film_id = inv.film_id " +
        			 " where p.customer_id =" + customerid +
        			 " group by inv.film_id" +
        			 " order by inv.film_id desc";
			
        	st = conn.createStatement();
        	rs = st.executeQuery(sql);
        	
			while (rs.next()) {
				if(isouput){
				System.out.println(rs.getString("first_name") + rs.getString("last_name") + "租用的Film-­‐>");
				System.out.println("Film ID | Film名称 | 租用时间 ");
				isouput=false;
				}
				System.out.println(rs.getString("film_id") + "|" + rs.getString("title") + " | " + rs.getDate("rental_date"));
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
