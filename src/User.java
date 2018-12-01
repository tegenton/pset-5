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
	private String fname = "";
	private String lname = "";
	private String phone = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String zip = "";
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
		while (String.valueOf(phone).length() != 10 || String.valueOf(phone).charAt(0) == '0') {
			System.out.print("Phone (10 digits): ");
			phone = in.nextLine();
			if (String.valueOf(phone).length() != 10 || String.valueOf(phone).charAt(0) == '0')
				System.out.println("Invalid phone number");
		}
		System.out.print("Address: ");
		address = in.nextLine();
		System.out.print("City: ");
		city = in.nextLine();
		while (state.length() != 2) {
			System.out.print("State: ");
			state = in.nextLine();
			if (state.length() != 2)
				System.out.println("Invalid state");
		}
		while (zip.length() != 5) {
			System.out.print("Area code: ");
			zip = in.nextLine();
			if (zip.length() != 5)
				System.out.println("Invalid area code");
		}
		while (String.valueOf(birthday).length() != 8) {
			try {
				System.out.print("Date of Birth (YYYYMMDD): ");
				birthday = in.nextInt();
				if (String.valueOf(birthday).length() != 8)
					System.out.println("Invalid birth date");
			} catch (InputMismatchException e) {
				System.out.println("Invalid birth date");
			}
			finally {
				in.nextLine();
			}
		}
		while (String.valueOf(pin).length() != 4) {
			try {
				System.out.print("PIN (4 digits): ");
				pin = in.nextInt();
				if (String.valueOf(pin).length() != 4)
					System.out.println("Invalid PIN");
			} catch (InputMismatchException e) {
				System.out.println("Invalid PIN");
			}
			finally {
				in.nextLine();
			}
		}
	}

	String getName(boolean whitespace) {
		return (whitespace) ? (lname + "                    ").substring(0, 20) + (fname + "               ").substring(0, 15) : (lname + ", " + fname);
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
			this.pin = in.nextInt();
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
		System.out.println("Name: " + this.getName(false));
		System.out.println("Phone: " + this.getPhone().trim());
		System.out.println("Address: " + this.getAddress().trim());
		System.out.println("City: " + this.getCity().trim());
		System.out.println("State: " + this.getState().trim());
		System.out.println("Area Code: " + this.getZip().trim());
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
			String stat = null;
			while (stat.length() != 2) {
				System.out.println("What is your new state?");
				stat = in.nextLine();
				if (stat.length() != 2)
					System.out.println("Invalid state");
			}
			String zi = null;
			while (zi.length() != 5) {
				System.out.println("What is your new area code?");
				zi = in.nextLine();
				if (zi.length() != 5)
					System.out.println("Invalid zip code");
			}
			
			this.setAddress(addr, cit, stat, zi);
			return true;
		default:
			System.out.println("Invalid option");
			return false;
		}
	}
}
