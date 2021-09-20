package crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;


public class Main {
	
	
	
	public static void main(String[] args) throws Exception {


		int choice = -1;
		Scanner scan = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmployeeOracleDAO conn = new EmployeeOracleDAO();
		
		do {
			Menu.mainMenu();
			choice = Integer.parseInt(br.readLine());
			
			switch(choice) {
				
				case 1: 
					Menu.submit();
					break;
					
				case 2: 
					Menu.adminMenu();
					break;
					
				case 3:
					EmployeeMenu.eMenu();
					break;
					
				case 4:
					CustomerMenu.cMenu();
					break;
					
				
				
				
			
		}
		
		}while(choice !=0);
		
		
		
	}
	
	

}
	
