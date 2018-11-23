import java.io.IOException;

/**
 * This class has only one responsibility: start the ATM program!
 */

public class Tester {

	/**
	 * Main method.
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws IOException {

		/*
		 * Rather than hard coding one or more BankAccount objects, you'll need to read them in
		 * from our very primitive database (i.e., a flat-file). After making changes, of course,
		 * you'll need to update the database accordingly.
		 */

		ATM atm = new ATM(null);
		while (atm.hasAccount()) {
			atm.menu();
			atm.loginScreen();
		}
	}
}
