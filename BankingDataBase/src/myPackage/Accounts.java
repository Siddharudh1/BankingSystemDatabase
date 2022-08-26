package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts {
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	private static String url = "jdbc:mysql://localhost:3307/customerdatabase";
	private static String userName = "root";
	private static String passWord = "Siddu@123";
	
	public static void main(String[] args) {
		int ch = 0;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		try {
			conn = DriverManager.getConnection(url,userName,passWord);
			stmt = conn.createStatement();
			
			while(true) {
				System.out.println("-----------------------------------------");
				System.out.println("press 1 to create customer Account");
				System.out.println("press 2 AddMoney");
				System.out.println("press 3 WithdrawMoney");
				System.out.println("press 4 to closeAccount");
				System.out.println("Enter your choice");
				
				try {
					ch = sc1.nextInt();
				}
				catch(Exception e) {
					e.printStackTrace();
					main(args);
				}
				switch(ch) {
				case 1 :System.out.println("***Creating a customer account***");
						System.out.println("Enter custometId");
						String customerid= sc2.nextLine();
						System.out.println("Enter the FirstName");
						String firstName = sc2.nextLine();
						System.out.println("Enter username");	
						String username = sc2.nextLine();
						System.out.println("Enter lastname");
						String lastName = sc2.nextLine();
						System.out.println("Enter the Customer age");
						int age = sc1.nextInt();
						System.out.println("Enter the address");
						String address = sc2.nextLine();
						System.out.println("Enter mobile number");
						long mobileNumber = sc1.nextLong();
						System.out.println("Enter emialId");
						String eMailID = sc2.nextLine();
						System.out.println("Enter the date of birth");
						String dateofbirth = sc2.nextLine();
						System.out.println("Enter the Initial balance");
						int balance = sc1.nextInt();
						
						AccountOperations.createCustomerAccount(customerid,firstName, username, lastName, age, address, mobileNumber,eMailID, dateofbirth, balance);
						break;
				
				case 2 :System.out.println("Enter customer id");
						String customerid1 = sc2.nextLine();
						System.out.println("Enter username");
						String username1 = sc2.nextLine();
						System.out.println("Enter amount");
						int amount = sc1.nextInt();
						AccountOperations.addMoney(customerid1,username1, amount);
								
						break;
					
				case 3 :System.out.println("enter Customer id");
						String id2 = sc2.nextLine();
						System.out.println("Enter username");
						String username2 = sc2.nextLine();
						System.out.println("Enter tha amount to withdraw");
						int amount1 = sc1.nextInt();
						AccountOperations.withDrawMoney(id2,username2, amount1);								
						break;
					 
				case 4 :System.out.println("Enter customer Id");
						String customerid2 = sc2.nextLine();
						AccountOperations.closeAccount(customerid2);
								
						break;
					
						default : System.out.println("INVALID INPUT");
						
					}
				}
				
				
				
		}
		catch(Exception e) {
			e.printStackTrace();
			//main(args);
		}
	}
}
