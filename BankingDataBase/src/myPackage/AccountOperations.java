package myPackage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AccountOperations{
	public static ResultSet rs = null;
	public static void createCustomerAccount(String customerid, String firstName, String username, String lastName, int age, String address,long mobileNumber, String eMailId, String dateofbirth, int balance) {
		try {
			String sql ="insert into customer(customerid,first_name,user_name,last_name,age,address,mobile_number,email_id,date_of_birth,balance) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = Accounts.conn.prepareStatement(sql);
			stmt.setString(1, customerid);
			stmt.setString(2, firstName);
			stmt.setString(3, username);
			stmt.setString(4, lastName);
			stmt.setLong(5, age);
			stmt.setString(6, address);
			stmt.setLong(7, mobileNumber);
			stmt.setString(8, eMailId);
			stmt.setString(9,dateofbirth);
			stmt.setLong(10, balance);
			int rows = stmt.executeUpdate();
			if(rows>0) {
				System.out.println("Records inserted successfully.....");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addMoney(String customerid,String username,int amt) {
		try {
		
		PreparedStatement ps = Accounts.conn.prepareStatement("select balance from customer where customerid = ?");
		ps.setString(1, customerid);
		rs = ps.executeQuery();
		rs.next();
			
		String sql ="update customer set balance = ? where customerid = ?";
		PreparedStatement stmt = Accounts.conn.prepareStatement(sql);
		stmt.setInt(1,(amt+rs.getInt(1)));
		stmt.setString(2, customerid);
		int rows = stmt.executeUpdate();
		if(rows > 0) {
			System.out.println("Money added successfully....The total balance is"+(amt+rs.getInt(1)));
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void withDrawMoney(String customerid,String username,int amt) {
		try {

		PreparedStatement ps = Accounts.conn.prepareStatement("select balance from customer where customerid = ?");
		ps.setString(1, customerid);
		rs = ps.executeQuery();
		rs.next();
		
		String sql ="update customer set balance = ? where customerid = ?";
		PreparedStatement stmt = Accounts.conn.prepareStatement(sql);
		stmt.setInt(1,(rs.getInt(1)-amt));
		stmt.setString(2, customerid);
		int rows = stmt.executeUpdate();
		if(rows > 0 && amt < rs.getInt(1)) {
			System.out.println("Money withdraw successfully....."+(rs.getInt(1)-amt)));
		}
		else {
			System.out.println("balance is more than the current balance");
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeAccount(String customerid) {
		try {
			String sql ="delete from customer where customerid =?";
			PreparedStatement stmt = Accounts.conn.prepareStatement(sql);
			stmt.setString(1, customerid);
			int rows = stmt.executeUpdate();
			if(rows > 0) {
				System.out.println("Account closed successfully.....");
			}
			else {
				System.out.println("The customerid "+customerid+" is not present...Enter the valid customerid");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
