package crud;

public interface CustomerDAO {
	
	void insertCustomer(int id, String name, String address, String pNumber, int sNumber);
	void updateCustomer(int id, String name, String username, String password);
	void getAllCustomers();
	void getCustomerByID(int id);

}
