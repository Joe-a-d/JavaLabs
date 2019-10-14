/**
 * A class to represent a simplified bank account.
 * 
 * Mary Ellen Foster, MaryEllen.Foster@glasgow.ac.uk
 */
public class BankAccount {
	// Fields
	private int balance;
	private String name;
	private int id;

	// Used to ensure that all accounts have a unique ID
	private static int nextId = 0;

	/**
	 * Creates a new bank account with the given name and initial balance.
	 */
	public BankAccount(String name, int initialAmount) {
		this.name = name;
		this.balance = initialAmount;
		this.id = BankAccount.nextId++;
	}

	/** 
	 * Creates a new anonymous bank account with a zero balance.
	 */
	public BankAccount() {
		this("Anonymous", 0);
	}

	/**
	 * Deposits the given amount into the bank account.
	 */
	public void deposit(int value) {
		this.balance += value;
	}

	/**
	 * Withdraws the given amount from the bank account.
	 */
	public void withdraw(int value) {
		this.balance -= value;
	}

	// Getters and setters
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return this.balance;
	}
	public int getID() {
		return this.id;
	}

	/**
	 * Returns a nicely formatted String representation of this BankAccount.
	 */
	public String toString() {
		return "Account #" + id + " Name=" + name + ", balance=" + balance;
	}
	
	/**
	 * A test method to exercise the methods of the BankAccount class.
	 */
	public static void main(String[] args) {
		BankAccount ba1 = new BankAccount("Mary", 100);
		System.out.println(ba1);
		
		ba1.deposit(50);
		System.out.println(ba1);
		ba1.withdraw(100);
		System.out.println(ba1);
		
		BankAccount ba2 = new BankAccount();
		System.out.println(ba2);
		ba2.setName("Susan");
		ba2.deposit(5000);
		System.out.println(ba2);

	}

}
