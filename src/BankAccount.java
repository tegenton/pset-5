/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.security.InvalidParameterException;
import java.util.Scanner;

public class BankAccount {
    private double balance = 0;
    private long accountNumber;
    private User accountHolder;
    private static long accountNum = 100000000;
    
    BankAccount(User accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNum++;
    }
    public BankAccount(Scanner in) {
    	accountHolder = new User(in);
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
    void transfer(BankAccount reciever, double amount) {
    	if (reciever == null)
    		throw new InvalidParameterException("Recieving account does not exist");
    	this.withdraw(amount);
    	reciever.deposit(amount);
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
}
