package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;





public class CustomerMenu {
	
	
	public static void cMenu() throws Exception {
		PreparedStatement ps;
		ResultSet rs;	
		
		String choice = "-1";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		CustomerOracleDAO cus = new CustomerOracleDAO();
		Customer cust = new Customer();
	
		
		System.out.println("\n\n****************** CUSTOMER LOGIN ****************");
		System.out.println("Enter the customer username: ");
		String userName = br.readLine();
		System.out.println("Enter the customer password: ");
		System.out.println("***************************************************\n\n\n");
		String password = br.readLine();
		
		
		int customerID = login(userName, password);
		if(customerID >= 0) {
			
			
			do {
				System.out.println("\n\n###################### CUSTOMER ACCESS #####################");
				System.out.println(" 1) Deposit");
				System.out.println(" 2) Withdraw");
				System.out.println(" 3) Transfer Money");
				System.out.println(" 0) Go back to main menu");
				System.out.println("############################################################\n\n");
				
				choice = br.readLine();
				
				switch(choice) {
				
				case "1":
					int amount;
					int id;
					System.out.println("Enter Account Number: ");
					id = Integer.parseInt(br.readLine());
					
					System.out.println("Enter the amount to deposit: ");
					amount = Integer.parseInt(br.readLine());
					deposit(id, amount);
					break;
					
				case "2":
					System.out.println("Enter Account Number: ");
					id = Integer.parseInt(br.readLine());
					System.out.println("Enter the amount you want to withdraw: ");
					amount = Integer.parseInt(br.readLine());
					withdraw(id, amount);
					break;
					
				case "3":
					System.out.println("Enter Account Number Transfer From: ");
					int transferFrom = Integer.parseInt(br.readLine());
					
					System.out.println("Enter Account Number Transfer To: ");
					int transferTo = Integer.parseInt(br.readLine());
					
					System.out.println("Enter the amount you want to transfer to: ");
					int amt = Integer.parseInt(br.readLine());
					
					transferFrom(transferFrom, amt);
					transferTo(transferTo, amt);
					System.out.println("Successfully Transferred to Account #: " + transferTo);
					break;
					
//				case "4":
//					Menu.mainMenu();
//					break;
					
				default:
					System.out.println("INVALID INPUT!");
				}
					
				
			}while(choice != "0");
			 
			
		}else {
			System.out.println("INVALID USERNAME OR PASSWORD");
		}
	}
	
		
	public static int login(String username, String password) {
		PreparedStatement ps;
		ResultSet rs;
		
		int customerID = -1;
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT CUSTOMER_ID, NAME, USERNAME, BALANCE, PASSWORD FROM JBANK.CUSTOMER WHERE USERNAME = ? AND PASSWORD = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
				while(rs.next()) {
					System.out.println("*********************************************************");
					System.out.println("      <Welcome to JBANK> " + rs.getString("NAME"));
					System.out.println("*********************************************************\n");
					System.out.println("ACCOUNT NUMBER: " + rs.getInt("CUSTOMER_ID"));
					System.out.println("BALANCE: $" + rs.getString("BALANCE") + "\n\n\n");
					customerID = rs.getInt("CUSTOMER_ID");
				}
				return customerID;
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return customerID;
		
		
	}
	

	public static void deposit(int id, int amount) {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PreparedStatement ps;
		ResultSet rs;	
//		CustomerOracleDAO  cod = new CustomerOracleDAO();
//		Customer cus = new Customer();	
			
			if(amount < 0) {
				System.out.println("Amount cannot be less than 0.");
				
			} else if(amount > 10000) {
				System.out.println("You cannot exceed more than $10,000 a day.");
				
			} else if(amount >= 0 && amount <= 10000) {
				
				try {
					ps = JDBC.getConnection().prepareStatement("SELECT BALANCE FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						int b = rs.getInt("BALANCE"); 
						b = b + amount;
						ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET BALANCE = ? WHERE CUSTOMER_ID = ?");
						ps.setInt(2, id);
						ps.setInt(1, b);
						ps.executeUpdate();
						System.out.println("Your Current Balance: $" + b);
						
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}else {
				System.out.println("INVALID INPUT");
			}	
		
	}
	
	public static void withdraw(int id, int amount) {
		PreparedStatement ps;
		ResultSet rs;	
		
		
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT BALANCE FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery(); 
			
			if(rs.next()) {
				int balance = rs.getInt("BALANCE");
				
				if(amount < 0) {
					System.out.println("Amount cannot be less than 0.");
					
				} else if(amount > balance) {
					System.out.println("Insufficient Balance!");
					
				} else if(amount >= 0 && amount <= balance) {
				
					balance = balance - amount;
					ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET BALANCE = ? WHERE CUSTOMER_ID = ?");
					ps.setInt(2, id);
					ps.setInt(1, balance);
					ps.executeUpdate();
					System.out.println("Your Current Balance: $" + balance);
				
			} else {
				System.out.println("INVALID INPUT");
			}
				
		}
			
			 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void transferFrom(int id, int amount) {
		PreparedStatement ps;
		ResultSet rs;	
		
			
		
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT BALANCE FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery(); 
						
			if(rs.next()) {
				int balance = rs.getInt("BALANCE"); 
				
				if(amount < 0) {
					System.out.println("Amount cannot be less than 0.");
					
				} else if(amount > balance) {
					System.out.println("Insufficient Balance!");
					
				} else if(amount >= 0 && amount <= balance) {
				
					balance = balance - amount;
					ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET BALANCE = ? WHERE CUSTOMER_ID = ?");
					ps.setInt(2, id);
					ps.setInt(1, balance);
					ps.executeUpdate();

					System.out.println("Your Current Balance: $" + balance);	
			} 	
		} else {
			System.out.println("INVALID INPUT");
		}
			
				  
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void transferTo(int id, int amount) {
		PreparedStatement ps;
		ResultSet rs;				
		
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT BALANCE FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery(); 
						
			if(rs.next()) {
				int balance = rs.getInt("BALANCE"); 
				balance = balance + amount;
				ps = JDBC.getConnection().prepareStatement("UPDATE JBANK.CUSTOMER SET BALANCE = ? WHERE CUSTOMER_ID = ?");
				ps.setInt(2, id);
				ps.setInt(1, balance);
				ps.executeUpdate();

						
			} 	
					  
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	

}
