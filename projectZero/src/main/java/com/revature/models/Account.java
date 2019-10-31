package com.revature.models;

public class Account {
	private int id;
	private double balance;
	private String accountType;
	private User owner;
	private boolean isOpen;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, double balance, String accountType, User owner, boolean isOpen) {
		super();
		this.id = id;
		this.balance = balance;
		this.accountType = accountType;
		this.owner = owner;
		this.isOpen = isOpen;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public synchronized double getBalance() {
		return balance;
	}
	public synchronized void setBalance(double balance) {
		this.balance = balance;
	}
	public synchronized String getAccountType() {
		return accountType;
	}
	public synchronized void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public synchronized User getOwner() {
		return owner;
	}
	public synchronized void setOwner(User owner) {
		this.owner = owner;
	}
	public synchronized boolean isOpen() {
		return isOpen;
	}
	public synchronized void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + (isOpen ? 1231 : 1237);
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (isOpen != other.isOpen)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", accountType=" + accountType + ", owner=" + owner
				+ ", isOpen=" + isOpen + "]";
	}

}