# Problem Set 5

Problem Set 5 is an enhanced version of the simple ATM program you already implemented. It will do a lot of the same things, but, of course, will provide additional functionality that your earlier version did not.

## Getting Started

To get started, you'll need to create a [GitHub](https://github.com/) repository to store your Problem Set 5 code. After cloning my skeleton repository, you'll need to setup a remote to push your code to your repository instead of mine. Steps to accomplish this are outlined below.

### Setup

01. Login to your [GitHub](https://github.com/) account and create a new repository named `problem-set-5`.
02. In GitBash, navigate to your `APCSA` folder.
```
cd ~/Desktop/APCSA
```
03. Clone my skeleton repository from [GitHub](https://github.com/). This will make a copy of my repository and store it locally.
```
git clone git@github.com:rwilson-ucvts/java-pset-5-skeleton.git
```
04. The cloning process will have created a folder named `java-pset-5-skeleton`. Rename this folder to `pset5`.
```
mv java-pset-5-skeleton pset5
```
05. Change directories to get into your `pset5` folder.
```
cd pset5
```
06. Originally, the remote will be pointing at my repository. We need to overwrite this.
```
git remote rename origin upstream
```
07. Lastly, we need to add a new remote that points at the repository you created earlier. Make sure you replace `YOUR-USERNAME` with your actual username.
```
git remote add origin git@github.com:YOUR-USERNAME/problem-set-5.git
```
08. Launch Eclipse and set the `Workspace` to the `APCSA` folder you created on your `Desktop`. Make sure you're using your `Workspace`, as others' will be similarly named.
09. From within the `Package Explorer` (the left-most panel), right-click and select `Import...`.
10. Select `Git > Projects from Git`, and click `Next >`.
11. Select `Existing local repository` and click `Next >`.
12. Click the `Add...` button, and then the `Browse...` button.
13. Navigate to the `APCSA` folder on your `Desktop`, click the `pset5` project folder, and click `Open`.
14. Select the checkbox next to your project and click `Finish`.
15. Now that we've imported the Git project, we can click `Next >`, `Next >`, and `Finish` once more.

You should now see a `Project` in the `Package Explorer` in Eclipse.

16. Expand the `Project` folder. You should see the `JRE System Library` folder, as well as the `src` folder.
17. Expand the `src` folder. You should see several source files that you'll need to implement to complete this program.

## Requirements

Unlike previous problem sets, there is no list of exercises to solve. Instead, there is a list of requirements your program needs to meet. It is your job to determine how best to meet them. Let's start with the most simple (at least in terms of functionality).

### User

The `User` class is responsible for managing all aspects of the customer's personal information, as outlined below.

* The customer's first name.
* The customer's last name.
* The customer's PIN.
* The customer's date of birth.
* The customer's telephone number.
* The customer's street address.
* The customer's city.
* The customer's state.
* The customer's postal code.

Generally, the `User` class should have the ability to retrieve and modify each of the above pieces of information. Exceptions to this general rule of thumb are outlined below.

* In order to modify the PIN, a customer must first enter his or her existing PIN before choosing a new one.
* A customer should not be permitted to modify his or her first name, last name, or date of birth.

### BankAccount

The `BankAccount` class is responsible for managing all aspects of the customer's account information, as outlined below.

* The system-generated account number.
* The user associated with the account (i.e., an instance of the `User` class).
* The current account balance.

The `BankAccount` class should have the ability to deposit and withdraw money, as well as transfer funds to another account. That being said, customers should be prohibited from doing any of the following.

* Depositing a dollar amount that is less than or equal to $0.00.
* Withdrawing a dollar amount that is less than or equal to $0.00.
* Withdrawing a dollar amount that is greater than the account balance.
* Transfering a dollar amount that is less than or equal to $0.00.
* Transfering a dollar amount that is greater than the sending account balance.
* Transfering funds to non-existent accounts.

### Database

The `Database` class is responsible for interfacing between the fixed width file that serves as our database and the `ATM` class. It should contain basic CRUD (create, read, update, delete) operations.

* Create new accounts from user-provided information.
* Retrieve existing accounts.
* Update existing accounts.
* Delete existing accounts.

Including in this repository is a file called `accounts-db.txt`. This is a fixed-width file, meaning each field is assigned a specific number of characters. The account number, for example, is 9 characters wide and it is the very first field. This means that the first 9 characters of each line correspond to the account number. Full field definitions and descriptions are outlined below.

| Field | Description |
| --- | --- |
| Account Number | 9 characters wide, consisting only of numbers (i.e., 100000001). Account numbers should be system-generated and assigned during account creation. |
| PIN | 4 characters wide, consisting only of numbers (i.e., 1234). |
| Balance | 15 characters wide (including the decimal point). Dollar signs should not be written to the database file. This means that maximum possible dollar amount an account can hold is $999,999,999,999.99 (i.e., less than 1 trillion). |
| Last Name | 20 characters wide. |
| First Name | 15 characters wide. |
| Date of Birth | 8 characters wide (format: YYYYMMDD). |
| Phone Number | 10 characters wide (format: ##########). |
| Street Address | 30 characters wide (i.e., 1776 Raritan Road). |
| City | 30 characters wide (i.e., Scotch Plains). |
| State | 2 characters wide (i.e., NJ). |
| Postal Code | 5 characters wide (i.e., 07076). |
| Account Status | 1 character wide (Y for open accounts, N for closed accounts). |

### ATM

The `ATM` class is responsible for managing the interaction between the customer and ATM. Most of the logic will be implemented in this class. It should meet the specifications outlined below.

* Display a simple main menu.
   - Open account
   - Login
   - Quit
* Display a more complex submenu after logging in.
   - Deposit funds
   - Withdraw funds
   - Transfer funds
   - View balance
   - View personal information
   - Update personal infromation
   - Close account
   - Logout
* Respond accordingly to each of the menu options.

The expected inputs for a program like this will understandably vary. It is your responsibility to handle this. Your program needs to be able to handle anything a customer might throw at it. Simply put, it should never crash.

### Tester

The `Tester` class is responsible only for starting the program. This is where your `main` method should be written. A working version, with all features correctly implemented, is available in JAR form. You can download it from this repository and run it using the command below.
```
java -jar enhanced-atm.jar
```

## Deadline

Your Canvas submission is due at or before 11:59pm on TBD.

### Submission Requirements

All that is required for submission is the URL to your [GitHub](https://github.com/) repository for this problem set.
