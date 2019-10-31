package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.TransactionDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class WithdrawPrompt implements Prompt{


	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;

	@Override
	public Prompt run() {
		List<Account> accounts = accountDao.findbyOwner(authUtil.getCurrentUser());
		for (Account a : accounts) {
			System.out.println(a);
		}
		System.out.println("Please input the Account ID # you wish to withdraw from");
		String selection = scan.nextLine();
		int i = Integer.parseInt(selection);
		
		System.out.println("Please input the amount of Gil to be withdrawn");
		String amount = scan.nextLine();
		int j = Integer.parseInt(amount);
		
		transactionDao.deposit(j,i);
		j =j*-1;
		accountDao.updateBalance(i , j);
		j =j*-1;
		System.out.println(authUtil.getCurrentUser().getUsername() + " withdrew "+ j + " Gil.");
		return new MainMenuPrompt();
	}
}