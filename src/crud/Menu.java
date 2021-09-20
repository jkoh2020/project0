package crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;






public class Menu {
	
	
	public static void mainMenu(){
		Scanner scan = new Scanner(System.in);
		
		
		
		int choice = -1;
		
		System.out.println("******************** Welcome to JBank **************************");
		System.out.println(" 1. Apply for an account");
		System.out.println(" 2. Admin");
		System.out.println(" 3. Employee");
		System.out.println(" 4. Customer");
		System.out.println(" 0. Exit");
		System.out.println("****************************************************************");
		System.out.println("Enter the option: ");
		
		
	}
	
	public static void adminMenu() throws Exception {
		
		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		CustomerOracleDAO cus = new CustomerOracleDAO();
		
		Admin admin = new Admin();
		//Admin login menu
		System.out.println("****************** ADMIN LOGIN ****************");
		System.out.println("Enter the admin username: ");
		String userName = br.readLine();
		System.out.println("Enter the admin password: ");
		String password = br.readLine();
		
		if(userName.equals(admin.getAdminUserName()) && password.equals(admin.getAdminPassword())) {
			do {
				System.out.println("\n\n######################### Admin Only ########################");
				System.out.println(" 1) Add New Employee");
				System.out.println(" 2) Display All Employees");
				System.out.println(" 3) Search Employee by ID");
				System.out.println(" 4) Delete Employee");
				System.out.println(" 5) Update Employee");
				System.out.println(" 6) Sort by Employee Number");
				System.out.println(" 7) View Applications ");
				System.out.println(" 8) Approve Application");
				System.out.println(" 9) Open an Account");
				System.out.println("10) Display All Customers");
				System.out.println("11) Display Customer by ID");
				System.out.println(" 0) EXIT");
				System.out.println("##############################################################\n\n");
				System.out.println("Enter your choice(number only): ");
				choice = Integer.parseInt(br.readLine());
				
				switch(choice) {
				
				case 1: 
					System.out.println("Enter how many employees do you want to create: ");
					int n = Integer.parseInt(br.readLine());
					
					for(int i=0; i<n; i++) {
						System.out.print("Enter Employee No: ");
						int eNumber = Integer.parseInt(br.readLine());
						
						System.out.print("Enter Employee Name: ");
						String eName = br.readLine();
						
						System.out.print("Enter Employee User Name: ");
						String username = br.readLine();
						
						System.out.print("Enter Employee Password: ");
						String password1 = br.readLine();
						
						System.out.print("Enter Employee Salary:");
						int salary = Integer.parseInt(br.readLine());
						
						conn.insertEmployee(eNumber, eName, username, password1, salary);
					}
					break;
				
				case 2:
					conn.getAllEmployees();
					break;
					
				case 3:
					System.out.println("Enter Employee Number To Search: ");
					int number = Integer.parseInt(br.readLine());
					conn.getEmployeeByEmployeeID(number);
					break;
					
				case 4:
					System.out.println("Enter Employee Number To Delete: ");
					int number1 = Integer.parseInt(br.readLine());
					conn.deleteEmployee(number1);
					break;
					
				case 5:
					System.out.println("Enter employee number to update: ");
					int number2 = Integer.parseInt(br.readLine());
					
					System.out.println("Enter New Employee Name: ");
					String name =  br.readLine();
					
					System.out.println("Enter New Employee Password: ");
					String password1 = br.readLine();
					
					System.out.println("Enter New Salary: ");
					int salary = Integer.parseInt(br.readLine());
					
					conn.updateEmployee(number2, name, password1, salary);
					break;
					
				case 6:
					conn.sortByID();
					break;
					
				case 7:
					conn.viewApplication();
					break;
					
				case 8:
					conn.viewApplication();
					System.out.println("Enter Application Number To Approve: ");
					String aNumber = br.readLine();
					approveApplication(aNumber);
					
					break;
					
				case 9: 
					System.out.println("Enter Account Number: ");
					int accNumber = Integer.parseInt(br.readLine());
					PreparedStatement ps;
					ResultSet rs;	
					ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.CUSTOMER WHERE CUSTOMER_ID = ?");
					ps.setInt(1,accNumber);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						System.out.println("Enter Customer Name: ");
						String cName = br.readLine();
						
						System.out.println("Enter User Name: ");
						String username = br.readLine();
						
						System.out.println("Enter Password:");
						String accPassword = br.readLine();
						
						cus.updateCustomer(accNumber, cName, username, accPassword);
					}else {
						System.out.println("RECORD NOT FOUND!");
					}
					break;	
					
				case 10: 
					cus.getAllCustomers();
					break;
					
				case 11:
					System.out.println("Enter Customer Account Number: ");
					int acc = Integer.parseInt(br.readLine());
					cus.getCustomerByID(acc);
					
				}
				
			}while(choice != 0);
		}
	}
	
	public static void submit() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		AppOracleDAO conn = new AppOracleDAO();
		
		
		//It generates auto application number
		String aNumber = GenerateNumber.randomNumber(5);
		
		System.out.println("Enter your name: ");
		String name = br.readLine();
		
		System.out.println("Enter your address: ");
		String addresss = br.readLine();
		
		System.out.println("Enter your phone number: ");
		String pNumber = br.readLine();
		
		System.out.println("Enter your social number: ");
		int sNumber = Integer.parseInt(br.readLine());
		
		String status = "pending";
		
		conn.insertApplication(aNumber, name, addresss, pNumber, sNumber, status);
		
		
		
	}
	
	public static void approveApplication(String id) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PreparedStatement ps;
		ResultSet rs;	
		ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.APPLICATION WHERE APPLICATION_NUMBER = ?");
		ps.setString(1, id);
		rs = ps.executeQuery();
		
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		CustomerOracleDAO cus = new CustomerOracleDAO();
		
		
		
		if(rs.next())
		{
			int aID = Integer.parseInt(GenerateNumber.randomNumber(6));
			ps = JDBC.getConnection().prepareStatement("INSERT INTO JBANK.CUSTOMER(CUSTOMER_ID) VALUES(?)");
			
			ps.setInt(1,aID);
			
			ps.executeUpdate();
			
			
			ps = JDBC.getConnection().prepareStatement("DELETE FROM JBANK.APPLICATION WHERE APPLICATION_NUMBER = ?");
			ps.setString(1, id);
			ps.executeUpdate();
		}
		else {
			System.out.println("RECORD NOT FOUND!");
		}
		
		
	}
	
	public static void openAccount(int accNumber) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PreparedStatement ps;
		ResultSet rs;	
		ps = JDBC.getConnection().prepareStatement("SELECT * FROM JBANK.APPLICATION WHERE APPLICATION_NUMBER = ?");
		ps.setInt(1, accNumber);
		rs = ps.executeQuery();
		
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		CustomerOracleDAO cus = new CustomerOracleDAO();
		
		if(rs.next())
		{
			int aID = Integer.parseInt(GenerateNumber.randomNumber(6));
			ps = JDBC.getConnection().prepareStatement("INSERT INTO JBANK.CUSTOMER(CUSTOMER_ID) VALUES(?)");
			ps.setInt(1,aID);
			ps.executeUpdate();
			
		}
		else {
			System.out.println("RECORD NOT FOUND!");
		}
		
	}
	
	
	

}
