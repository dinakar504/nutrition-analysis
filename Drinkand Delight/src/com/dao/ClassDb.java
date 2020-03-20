package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassDb {
	 static Connection conn;
	 public static Connection getConnection1() 
		
			  {

				 String driverName = "oracle.jdbc.driver.OracleDriver";
				  try {
					Class.forName(driverName);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				try {
					conn = DriverManager.getConnection(
					             "jdbc:oracle:thin:@localhost:1521:XE","dinakar","dinakar");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return conn; //14.
			  }

}
