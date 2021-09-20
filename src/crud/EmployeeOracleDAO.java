package crud;

import java.sql.*;

public class EmployeeOracleDAO implements EmployeeDAO{
	
	

	PreparedStatement ps;
	ResultSet rs;	
	

	@Override
	public void insertEmployee(int id, String name, String username, String password, int salary) {
		
		try {
								
			ps = JDBC.getConnection().prepareStatement("INSERT INTO JBANK.EMPLOYEE(EMPLOYEE_ID, NAME, USER_NAME, PASSWORD, SALARY) VALUES(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, salary);
			//Record will be inserted into table
			ps.executeUpdate();
			System.out.println("EMPLOYEE ADDED SUCCESSFULLY!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}



	@Override
	public void updateEmployee(int id, String name, String password, int salary) {
		
		try {
			ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.EMPLOYEE SET NAME = ?, PASSWORD = ?, SALARY = ? WHERE EMPLOYEE_ID = ?");
			ps.setInt(4, id);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, salary);
			ps.executeUpdate();
			System.out.println("UPDATED SUCCESSFULLY!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


	
	@Override
	public void deleteEmployee(int id) {
		
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.EMPLOYEE WHERE EMPLOYEE_ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) 
			{
				ps = JDBC.getConnection().prepareStatement("DELETE FROM JBANK.EMPLOYEE WHERE EMPLOYEE_ID = ?");
				ps.setInt(1, id);
				ps.executeUpdate();
				System.out.println("DELETED SUCCESSFULLY!");
			}
			else {
				System.out.println("RECORD NOT FOUND!");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}

	@Override
	public void getAllEmployees() {
		
		try {
			String s = "SELECT * FROM JBANK.EMPLOYEE";
			ps = JDBC.getConnection().prepareStatement(s);
			rs = ps.executeQuery();
			
			System.out.printf("%-15s%-15s%15s%15s%15s\n", "ID", "Name", "UserName", "Password", "Salary");
			System.out.println("==============================================================================");
			
			
			while(rs.next()) {
				System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}

	@Override
	public void getEmployeeByEmployeeID(int id) {
		
		try {
			
			ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.EMPLOYEE WHERE EMPLOYEE_ID = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) 
			{
				System.out.printf("%-15s%-15s%15s%15s%15s\n", "ID", "Name", "UserName", "Password", "Salary");
				System.out.println("==============================================================================");
			
					System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
					while(rs.next()) {
						System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
					}
				
			}
			else {
				System.out.println("RECORD NOT FOUND!");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
	public void sortByID() {
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.EMPLOYEE ORDER BY EMPLOYEE_ID");
			rs = ps.executeQuery();
			System.out.printf("%-15s%-15s%15s%15s%15s\n", "ID", "Name", "UserName", "Password", "Salary");
			System.out.println("==============================================================================");
			
			while(rs.next()) {
				System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void viewApplication() {
		try {
			String s = "SELECT * FROM JBANK.APPLICATION";
			ps = JDBC.getConnection().prepareStatement(s);
			rs = ps.executeQuery();
			
			System.out.printf("%-10s%-15s%-40s%-20s%-20s%-20s\n", "ID", "Name", "Address", "Phone No.", "Social No.", "Status");
			System.out.println("=======================================================================================================================");
			
			
			while(rs.next()) {
				System.out.printf("%-10s%-15s%-40s%-20s%-20s%-20s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			System.out.println("=======================================================================================================================\n\n\n");
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	

}
