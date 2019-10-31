package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class CloseAccountPrompt implements Prompt {
	
	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		List<Account> accounts = accountDao.findbyOwner(authUtil.getCurrentUser());
		for(Account a: accounts) {
			System.out.println(a);
		}
		System.out.println("Please input the Account ID # you wish to close");
		String selection = scan.nextLine();
		int i = Integer.parseInt(selection);
		accountDao.close(i);
		System.out.println("Account closed");
		return new MainMenuPrompt();
	}

}
