import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class ATM {

	private BankAccount currentAccount = null;
	private Database database;
	private static Scanner in = new Scanner(System.in);

	ATM(File data) throws IOException {
		database = new Database(data);
		new BankAccount(database);
		while (!this.loginScreen());
	}

	public boolean loginScreen() {
		System.out.println("What would you like to do?\n" +
				"1) Log in to an existing account\n" +
				"2) Open a new account\n" +
                "3) Exit");
		switch (in.nextLine().charAt(0)) {
			case '1':
				this.login();
				break;
			case '2':
				try {
					this.createAccount();
				}
				catch (IOException e) {
					System.out.println("Error writing account to file");
				}
				break;
            case '3':
                return true;
			default:
				return false;
		}
		return true;
	}

	private void login() {
	    Long testNum;
	    Integer testPin;
		while (this.currentAccount == null) {
			System.out.println("Enter your account number:");
			testNum = null;
			try {
			    testNum = in.nextLong();
            }
			catch (InputMismatchException e) {
			    System.out.println("Invalid account number");
				in.nextLine();
			    continue;
            }
			System.out.println("Enter your PIN:");
			testPin = null;
			try {
			    testPin = in.nextInt();
            }
			catch (InputMismatchException e){
                System.out.println("Invalid pin");
				in.nextLine();
                continue;
            }
			in.nextLine();
			if (testNum != null && testPin != null)
			    this.currentAccount = this.database.getAccount(testNum, testPin);
			if (this.currentAccount == null)
				System.out.println("Incorrect information, please try again");
		}
	}

	private void createAccount() throws IOException {
		this.currentAccount = new BankAccount(in);
		database.createAccount(this.currentAccount);
	}

	public void menu() {
		while (!this.menu('a'));
	}

	private boolean menu(char a) {
		System.out.println("What would you like to do?\n" +
				"1) Deposit funds\n" +
				"2) Withdraw funds\n" +
				"3) Transfer funds\n" +
				"4) View balance\n" +
				"5) View personal information\n" +
				"6) Update personal information\n" +
				"7) Close account\n" +
				"8) Save Changes and Logout");
		switch (in.nextLine().charAt(0)) {
			case '1':
				System.out.println("How much would you like to deposit?");
				try {
					this.currentAccount.deposit(in.nextDouble());
				}
				catch (InvalidParameterException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid amount");
				}
				in.nextLine();
				break;
			case '2':
				System.out.println("How much would you like to withdraw?");
				try {
					this.currentAccount.withdraw(in.nextDouble());
				}
				catch (InvalidParameterException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid amount");
				}
				in.nextLine();
				break;
			case '3':
				long accountNum;
				try {
				    accountNum = in.nextLong();
	            }
				catch (InputMismatchException e) {
				    System.out.println("Invalid account number");
					in.nextLine();
				    break;
	            }
				System.out.println("Enter your PIN:");
				int pin;
				try {
				    pin = in.nextInt();
	            }
				catch (InputMismatchException e){
	                System.out.println("Invalid pin");
					in.nextLine();
					break;
	            }
				try {
					double amount = in.nextDouble();
					try {
						this.database.updateAccount(this.currentAccount.transfer(database.getAccount(accountNum, pin), amount));
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				catch (InputMismatchException e) {
					System.out.println("Not a valid amount");
				}
				in.nextLine();
				break;
			case '4':
			    System.out.println("Current balance is: $" + currentAccount.getBalance());
				break;
			case '5':
				this.currentAccount.getUser().printInfo();
				System.out.println("Press enter to continue");
				in.nextLine();
				break;
			case '6':
				while (!this.currentAccount.getUser().updateInfo(in));
				break;
			case '7':
				System.out.println("Closing account");
				this.currentAccount.close();
			case '8':
				System.out.println("Logging out");
                try {
                    this.database.updateAccount(this.currentAccount);
                }
                catch (Exception e) {
                    System.out.println("Error saving account\n");
                    return false;
                }
				this.currentAccount = null;
				return true;
			default:
				System.out.println("Invalid option");
				break;
		}
		return false;
	}

    public boolean hasAccount() {
	    return (this.currentAccount != null);
    }
}
