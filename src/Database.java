import java.io.*;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {

	private File data;
	private File tempFile;

	Database(File file) throws IOException {
		if (file == null) {
			throw new IOException("File not found");
		}
		this.data = file;
	}

	void createAccount(BankAccount account) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.data.getAbsoluteFile(), true))){
			bw.newLine();
			bw.append(account.getString());
		}
	}

	BankAccount getAccount(long accountNumber, int pin) {
		try (BufferedReader br = new BufferedReader(new FileReader(this.data))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.substring(0, 13).equals(accountNumber + "" + pin) && !(line.charAt(line.length() - 1) == 'N')) {
					return new BankAccount(line);
				}
			}
			return null;
		}
		catch (IOException e) {
			System.out.println("Error reading account from file");
			return null;
		}
	}

	void updateAccount(BankAccount account) {
		tempFile = new File(data.getAbsolutePath() + ".tmp");
		try {
			tempFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Could not update account (Unable to create temporary storage file)");
		}
		try (BufferedReader br = new BufferedReader(new FileReader(this.data));
				BufferedWriter bw = new BufferedWriter(new FileWriter(this.tempFile, false))) {
			String line = br.readLine();
			if (line != null) do {
				if (!line.substring(0, 9).equals(account.getAccountNumber() + "")) {
					bw.write(line);
					if ((line = br.readLine()) != null)
						bw.newLine();
				}
				else {
					bw.write(account.getString());
					if ((line = br.readLine()) != null)
						bw.newLine();
				}
			} while (line != null);
		}
		catch (IOException e) {
			System.out.println("Could not update account (Error writing to file)");
		}
		data.delete();
		if (!tempFile.renameTo(data))
			System.out.println("Could not update database (Error renaming file)");
	}

	void deleteAccount(BankAccount account) throws IOException {
		tempFile = new File(data.getAbsolutePath() + ".tmp");
		try (BufferedReader br = new BufferedReader(new FileReader(this.data));
				BufferedWriter bw = new BufferedWriter(new FileWriter(this.tempFile.getAbsoluteFile(), false))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.substring(0, 13).equals(account.getAccountNumber() + "" + account.getUser().getPIN())) {
					bw.write(line);
					bw.newLine();
				}
			}
		}
		if (!tempFile.renameTo(data))
			System.out.println("Could not delete account (Error renaming file)");
	}

	public long accountNumber() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.data))) {
			String line;
			long max = 100000000;
			while ((line = br.readLine()) != null) {
				if (Long.parseLong(line.substring(0, 9)) > max) {
					max = Long.parseLong(line.substring(0, 9));
				}
			}
			return max;
		}
		catch (IOException e) {
			System.out.println("No accounts found");
			return 100000000;
		}
	}
}
