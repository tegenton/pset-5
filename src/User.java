/*
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.util.Scanner;

public class User {
	private int ssn;
	private String fname;
	private String lname;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String birthday;
	private int pin;

	User (String name, String phone, String address, String city, String zip, String state, String birthday, int pin) {
		int space = name.indexOf(',');
		this.lname = name.substring(space + 2, name.length() - 1).trim();
		this.fname = name.substring(0, space).trim();
		this.phone = phone.trim();
		this.address = address.trim();
		this.city = city.trim();
		this.state = state.trim();
		this.zip = zip;
		this.birthday = birthday;
		this.pin = pin;
	}
	User (Scanner in) {
		System.out.println("Enter your information to create an account");
		System.out.print("First Name: ");
		fname = in.nextLine();
		System.out.print("Last Name: ");
		lname = in.nextLine();
		System.out.print("SSN: ");
		ssn = in.nextInt();
		in.nextLine();
		System.out.print("Phone (##########): ");
		phone = in.nextLine();
		System.out.print("Address: ");
		address = in.nextLine();
		System.out.print("City: ");
		city = in.nextLine();
		System.out.print("State: ");
		state = in.nextLine();
		System.out.print("Area Code: ");
		zip = in.nextLine();
		System.out.print("Date of Birth (YYYYMMDD): ");
		birthday = in.nextLine();
		System.out.print("PIN: ");
		pin = in.nextInt();
		in.nextLine();
	}

	String getName() {
		return fname + ", " + lname;
	}

	int getSSN() {
		return ssn;
	}

	void setPhone(String phone) {
		this.phone = phone;
	}
	String getPhone() {
		return phone;
	}

	void setAddress(String address, String city, String state, String zip) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	String getAddress() {
		return address;
	}

	String getCity() {
		return city;
	}

	String getState() {
		return state;
	}

	String getZip() {
		return zip;
	}

	String getBirthday() {
		return birthday;
	}

	boolean checkPIN(int test) {
		return pin == test;
	}
	void setPIN(Scanner in) {
		System.out.println("What is your current PIN?");
		if (this.checkPIN(in.nextInt())) {
			System.out.println("Enter your new PIN");
			pin = in.nextInt();
			in.nextLine();
		}
		else {
			System.out.println("Incorrect pin, please try again");
			this.setPIN(in);
		}
	}
	public int getPIN() {
		return pin;
	}

	public void printInfo() {
		System.out.println("Name: " + this.getName());
		System.out.println("SSN: " + this.getSSN());
		System.out.println("Phone: " + this.getPhone());
		System.out.println("Address: " + this.getAddress());
		System.out.println("City: " + this.getCity());
		System.out.println("State: " + this.getState());
		System.out.println("Area Code: " + this.getZip());
		System.out.println("Date of Birth (YYYYMMDD): " + this.getBirthday());
	}

	public boolean updateInfo(Scanner in) {
		System.out.println("What do you want to update?\n" +
				"1) PIN\n" +
				"2) Phone Number\n" +
				"3) Address");
		switch (in.nextLine().toLowerCase().charAt(0)) {
		case '1':
			this.setPIN(in);
			return true;
		case '2':
			System.out.println("What is your new phone number? (##########)");
			this.setPhone(in.nextLine().trim());
			return true;
		case '3':
			System.out.println("What is your new address?");
			String addr = in.nextLine();
			System.out.println("What is your new city?");
			String cit = in.nextLine();
			System.out.println("What is your new state?");
			String stat = in.nextLine();
			System.out.println("What is your new area code?");
			String zi = in.nextLine();
			
			this.setAddress(addr, cit, stat, zi);
			return true;
		default:
			System.out.println("Invalid option");
			return false;
		}
	}
}
