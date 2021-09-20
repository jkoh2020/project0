package crud;

import java.sql.*;

public class CustomerOracleDAO implements CustomerDAO {
	
	PreparedStatement ps;
	ResultSet rs;	

	@Override
	public void insertCustomer(int id, String name, String address, String pNumber, int sNumber) {
		try {
			ps = JDBC.getConnection().prepareStatement("INSERT INTO JBANK.CUSTOMER(CUSTOMER_ID, NAME, ADDRESS, PHONE_NUMBER, SOCIAL_NUMBER) VALUES(?,?,?,?,?)");
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, pNumber);
			ps.setInt(5, sNumber);
			//Record will be inserted into table
			ps.executeUpdate();
			System.out.println("CUSTOMER ADDED SUCCESSFULLY!");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCustomer(int id, String name, String username, String password) {
			try {
				ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET NAME = ?, USERNAME = ?, PASSWORD = ? WHERE CUSTOMER_ID = ?" );
				ps.setInt(4, id);
				ps.setString(1, name);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.executeUpdate();
				System.out.println("\nCONGRATULATIONS! YOUR ACCOUNT IS ACTIVATED SUCCESSFULLY.");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}

	@Override
	public void getAllCustomers() {
		String s = "SELECT * FROM JBANK.CUSTOMER";
		try {
			ps = JDBC.getConnection().prepareStatement(s);
			rs = ps.executeQuery();
			
			System.out.printf("%-15s%-15s%15s%15s%15s\n", "ID", "NAME", "USERNAME", "PASSWORD", "BALANCE");
			System.out.println("==================================================================================================================================");
			
			while(rs.next()) {
				System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void getCustomerByID(int id) {
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.printf("%-15s%-15s%15s%15s%15s\n", "ID", "NAME", "USERNAME", "PASSWORD", "BALANCE");
				System.out.println("==================================================================================================================================");
				
				System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				while(rs.next()) {
					System.out.printf("%-15s%-15s%15s%15s%15s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	public int calcDeposit(int amount) {
//		Customer cust = new Customer();
//		cust.balance = cust.balance + amount;
//		return cust.balance;
//	}
	
	public float calcWithdraw(int amount) {
		Customer cust = new Customer();
		cust.balance = cust.balance - amount;
		return cust.balance;
	}
	
	public void updateBalance(int id, int balance) {
		try {
			ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET BALANCE = ? WHERE CUSTOMER_ID = ?");
			ps.setInt(1, id);
			ps.setInt(2, balance );
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	
}
