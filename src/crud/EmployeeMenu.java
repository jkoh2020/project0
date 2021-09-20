package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeMenu {
	
	public static void eMenu() throws Exception {
		String choice = "-1";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		EmployeeOracleDAO emp = new EmployeeOracleDAO();
		CustomerOracleDAO cus = new CustomerOracleDAO();
		Customer cust = new Customer();
		
		System.out.println("\n\n****************** EMPLOYEE LOGIN ****************");
		System.out.println("Enter the employee username: ");
		String username = br.readLine();
		System.out.println("Enter the employee password: ");
		String password = br.readLine();
		
		int employeeID = employeeLogin(username, password);
		
		if(employeeID >= 0) {
			
			do {
				System.out.println("\n\n###################### EMPLOYEE ACCESS #####################");
				System.out.println(" 1) View Application");
				System.out.println(" 2) Open Account");
				System.out.println(" 3) Display All Customers");
				System.out.println(" 4) Display Customer Account by ID");
				System.out.println(" 0) Go back to main menu");
				System.out.println("############################################################");
				System.out.println("Enter the option: ");
				
				choice = br.readLine();
				switch(choice ) {
				case "1": 
					conn.viewApplication();
					break;
					
				case "2":
					System.out.println("Enter Account Number: ");
					int accNumber = Integer.parseInt(br.readLine());
					Menu.openAccount(accNumber);
					break;
					
				case "3":
					cus.getAllCustomers();
					break;
					
				case "4":
					System.out.println("Enter Customer Account Number: ");
					int acc = Integer.parseInt(br.readLine());
					cus.getCustomerByID(acc);
					break;
					
				case "5":
					Main.main(null);
					break;
					
				default:
					System.out.println("INVALID INPUT!");
				
				}
				
			} while(choice != "0");
			
		}
		
		
	}
	
	public static int employeeLogin(String username, String password) {
		
		PreparedStatement ps;
		ResultSet rs;
		
		int employeeID = -1;
		
		try {
			ps = JDBC.getConnection().prepareStatement("SELECT EMPLOYEE_ID, NAME, USERNAME, PASSWORD FROM JBANK.EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("*********************************************************");
				System.out.println("      Hello   " + rs.getString("NAME")                    );
				System.out.println("*********************************************************");
				System.out.println("EMPLOYEE ID: " + rs.getInt("EMPLOYEE_ID"));
				employeeID = rs.getInt("EMPLOYEE_ID");
			}
			return employeeID;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return employeeID;
			
	}
	
	

}
