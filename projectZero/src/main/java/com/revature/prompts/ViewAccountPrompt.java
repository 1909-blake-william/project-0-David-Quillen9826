package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class ViewAccountPrompt implements Prompt {

	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	private Scanner scan = new Scanner(System.in);


	@Override
	public Prompt run() {
		List<Account> accounts = accountDao.findbyOwner(authUtil.getCurrentUser());
		for (Account a : accounts) {
			System.out.println(a);
		}
		System.out.println("Do you want to view transactions? Y/n");
		if (scan.nextLine().equals("Y")) {
			return new ViewTransactionsPrompt();
		} else {
			return new MainMenuPrompt();

		}
	}

}
