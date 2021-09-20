package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppOracleDAO implements ApplicationDAO {
	
	PreparedStatement ps;
	ResultSet rs;	


	@Override
	public void insertApplication(String id, String name, String address, String pNumber, int sNumber, String status) {
		
		try {
			
			ps = JDBC.getConnection().prepareStatement("INSERT INTO JBANK.APPLICATION(APPLICATION_NUMBER, NAME, ADDRESS, PHONE_NUMBER, SOCIAL_NUMBER, STATUS) VALUES(?,?,?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, pNumber);
			ps.setInt(5, sNumber);
			ps.setString(6, status);
		
			//Record will be inserted into table
			ps.executeUpdate();
			System.out.println("APPLICATION SENT SUCCESSFULLY!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
