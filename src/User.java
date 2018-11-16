/**
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

    User (String fname, String lname, int ssn, String phone, String address, String city, String state, String birthday, int pin) {
        this.fname = fname;
        this.lname = lname;
        this.ssn = ssn;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
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
        System.out.print("Phone: ");
        phone = in.nextLine();
        System.out.print("Address: ");
        address = in.nextLine();
        System.out.print("City: ");
        city = in.nextLine();
        System.out.print("State: ");
        state = in.nextLine();
        System.out.print("Area Code: ");
        zip = in.nextLine();
        System.out.print("Date of Birth: ");
        birthday = in.nextLine();
        System.out.print("PIN: ");
        pin = in.nextInt();
        in.nextLine();
    }
    
    String getName() {
        return fname + " " + lname;
    }
    
    void setSSN(int ssn) {
    	this.ssn = ssn;
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
        return address + ", " +  city + ", " + state + ", " + zip;
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
}
