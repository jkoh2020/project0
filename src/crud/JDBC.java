package crud;

import java.sql.*;

public class JDBC {
	static Connection conn;
	static final String user="admin";
	static final String password="admin123";
	static final String db_url="jdbc:oracle:thin:@project-0.cd0yx7jbomwj.us-east-2.rds.amazonaws.com:1521:ORCL";
	                                              
	public static Connection getConnection(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(db_url, user, password);
			
		}catch(Exception e) {}
		return conn;	
	}
	
	public void close() throws Exception{
		conn.close();
		System.out.println("Connection is Closed");
	}
	
}
