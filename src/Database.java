import com.sun.istack.internal.NotNull;

import java.io.*;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {

	private File data;

	Database(@NotNull File file) {
		this.data = file;
	}

	void addAccount(BankAccount account) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.data.getAbsoluteFile(), true))){
			bw.append(account.getString());
		}
	}

	BankAccount getAccount(long accountNumber, int pin) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(this.data))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.substring(0, 13).equals(accountNumber + "" + pin)) {
					return new BankAccount(line);
				}
			}
			return null;
		}
	}

	void updateAccount(BankAccount account) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.data.getAbsoluteFile(), false))){
			// TODO
		}
	}

	void deleteAccount(BankAccount account) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.data.getAbsoluteFile(), false))){
			// TODO
		}
	}
}
