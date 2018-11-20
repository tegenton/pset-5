import java.io.*;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {
	
	private File data;
	private BufferedReader br;
	private BufferedWriter fw;
	
	public Database(File file) throws IOException {
		this.data = file;
		br = new BufferedReader(new FileReader(this.data));
		fw = new BufferedWriter(new FileWriter(this.data.getAbsoluteFile(), true));
	}
	
	public void addAccount(BankAccount account) {
		fw.append(account.getString());
	}
	
	public BankAccount getAccount(long accountNumber, int pin) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			if (line.substring(0, 13).equals(accountNumber + "" + pin)) {
				return new BankAccount();
			}
		}
		return null;
	}
	
	protected void finalize() throws Throwable {
		br.close();
	}
}