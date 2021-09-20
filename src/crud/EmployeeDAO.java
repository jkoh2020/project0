package crud;

public interface EmployeeDAO {
	
	void insertEmployee(int id, String name, String username, String password, int salary);
	void updateEmployee(int id, String name, String password, int salary);
	void deleteEmployee(int id);
	
	void getAllEmployees();
	
	void getEmployeeByEmployeeID(int id);

}
