import java.io.*;
import java.util.Scanner;

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

	ATM(File data) {
		database = new Database(data);
	}

	public static void main(String[] args) throws IOException {
		ATM atm = new ATM(new File("")); // TODO: path to file
		while (atm.currentAccount == null) {
			System.out.println("Enter your account number:");
			long testNum = Long.getLong(in.nextLine());
			System.out.println("Enter your PIN:");
			int testPin = Integer.getInteger(in.nextLine());
			atm.currentAccount = atm.database.getAccount(testNum, testPin);
		}
	}
}
