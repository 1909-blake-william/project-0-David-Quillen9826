package com.revature.daos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDao {
	AccountDao currentImplementation = new AccountDaoSQL();
	
	int open(Account a);

	List<Account> findAll();

	Account findByID(int i);
	
	List<Account> findbyOwner(User u);
	
	void close(int a);
	
	int updateBalance(int i, int j);
}
