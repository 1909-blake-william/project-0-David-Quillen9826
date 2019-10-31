package com.revature.prompts;

import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class OpenAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;

	@Override
	public Prompt run() {
	System.out.println("Enter Account type: ");
	String type = scan.nextLine();
	
	Account a = new Account(0 ,0, type, authUtil.getCurrentUser(), true);
	accountDao.open(a);
	return new MainMenuPrompt();
	}

}
