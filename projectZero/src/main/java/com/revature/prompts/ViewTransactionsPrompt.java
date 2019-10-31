package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.TransactionDao;
import com.revature.models.Transaction;

public class ViewTransactionsPrompt implements Prompt {
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	private AccountDao accountDao = AccountDao.currentImplementation;
	private Scanner scan = new Scanner(System.in);

	@Override
	public Prompt run() {
	System.out.println("Please input the Account ID # you wish to view");
	String selection = scan.nextLine();
	int i = Integer.parseInt(selection);
	List<Transaction> allTransactions = transactionDao.findByAccount(accountDao.findByID(i));
	for (Transaction t : allTransactions) {
		System.out.println(t.toString());
	}
	return new MainMenuPrompt();
	}

}
