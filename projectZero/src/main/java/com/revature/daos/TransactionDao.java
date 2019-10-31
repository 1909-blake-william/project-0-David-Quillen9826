package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Transaction;

public interface TransactionDao {
	TransactionDao currentImplementation = new TransactionDaoSQL();

	int deposit(int d, int i);
	
	int withdraw(int w, int i);
	
	List<Transaction> findAll();
	
	List<Transaction> findByAccount(Account a);
	
}
