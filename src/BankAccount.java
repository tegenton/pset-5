/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transferring funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my GitHub repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.security.InvalidParameterException;
import java.util.Scanner;

public class BankAccount {
	private double balance = 0;
	private long accountNumber;
	private User accountHolder;
	private static long accountNum = 100000010;
	private char status = 'Y';

	BankAccount(User accountHolder) {
		this.accountHolder = accountHolder;
		this.accountNumber = accountNum++;
	}
	BankAccount(User accountHolder, long accountNumber, double balance) {
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	BankAccount(String info) {
		int index = 0;
		
		//System.out.println(info.substring(index, index += 9));
		
		this.accountNumber = Long.parseLong(info.substring(index, index += 9));
		int pin = Integer.parseInt(info.substring(index, index += 4));
		this.balance = Double.parseDouble(info.substring(index, index += 13));
		String name = info.substring(index, index += 32);
		String birthday = info.substring(index, index += 8);
		String phone = info.substring(index, index += 10);
		String address = info.substring(index, index += 30);
		String city = info.substring(index, index += 30);
		String state = info.substring(index, index += 2);
		String zip = info.substring(index, index += 5);
		this.status = info.charAt(index);
		this.accountHolder = new User(name, birthday, phone, address, city, state, zip, pin);
	}
	BankAccount(Scanner in) {
		this.accountHolder = new User(in);
		this.accountNumber = accountNum++;
	}
	void deposit(double amount) {
		if (amount <= 0)
			throw new InvalidParameterException("Deposit amount is too low");
		balance += amount;
	}
	void withdraw(double amount) {
		if (balance >= amount && amount > 0)
			balance -= amount;
		else if (0 > amount)
			throw new InvalidParameterException("Withdraw amount is too low");
		else
			throw new InvalidParameterException("Withdraw amount is too high");
	}
	void transfer(BankAccount receiver, double amount) {
		if (receiver == null)
			throw new InvalidParameterException("Receiving account does not exist");
		this.withdraw(amount);
		receiver.deposit(amount);
	}

	double getBalance() {
		return balance;
	}

	long getAccountNumber() {
		return accountNumber;
	}

	User getUser() {
		return accountHolder;
	}

	void close() {
		this.status = 'N';
	}

	public String getString() {
		String temp = String.format("%9o", accountNumber);
		temp += String.format("%4f", accountHolder.getPIN());
		temp += String.format("%10.2f", balance);
		temp += accountHolder.getName().substring(0,32);
		temp += accountHolder.getBirthday().substring(0,8);
		temp += accountHolder.getPhone().substring(0,10);
		temp += accountHolder.getAddress().substring(0,30);
		temp += accountHolder.getCity().substring(0,30);
		temp += accountHolder.getState().substring(0,2);
		temp += accountHolder.getZip().substring(0,5);
		temp += status;
		return temp;
	}
	public void setUser(User user) {
		this.accountHolder = user;
	}
}
