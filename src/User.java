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

import java.util.*;

public class User {
	private String fname;
	private String lname;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	private int birthday;
	private int pin;

	User (String name, String phone, String address, String city, String zip, String state, int birthday, int pin) {
		this.lname = name.substring(0, 20).trim();
		this.fname = name.substring(20, 35).trim();
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
		while (true) {
			try {
				System.out.print("Date of Birth (YYYYMMDD): ");
				birthday = in.nextInt();
				if (String.valueOf(birthday).length() != 8)
					throw new InputMismatchException("Not properly formatted");
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid birth date");
				in.nextLine();
			}
		}
		while (true) {
			try {
				System.out.print("PIN (4 digits): ");
				pin = in.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid PIN");
				in.nextLine();
			}
		}
		in.nextLine();
	}

	String getName() {
		return (lname + "                    ").substring(0, 20) + (fname + "               ").substring(0, 15);
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

	int getBirthday() {
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
