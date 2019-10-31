package com.revature.prompts;

import java.util.Scanner;

import com.revature.util.AuthUtil;

public class MainMenuPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;

	@Override
	public Prompt run() {

		System.out.println("Welcome " + authUtil.getCurrentUser().getUsername() + ", please choose an option");
		System.out.println("Enter 1 to open accounts");
		System.out.println("Enter 2 to close accounts");
		System.out.println("Enter 3 to view accounts");
		System.out.println("Enter 4 to deposit Gil");
		System.out.println("Enter 5 to withdraw Gil");
		if (authUtil.getCurrentUser().isAdmin()) {
			System.out.println("Enter 6 to view other accounts");
		}
		System.out.println("Enter 'L' to logout");

		// get user input
		String selection = scan.nextLine();

		switch (selection) {
		case "1":
			return new OpenAccountPrompt();
		case "2":
			return new CloseAccountPrompt();
		case "3":
			return new ViewAccountPrompt();
		case "4":
			return new DepositPrompt();
		case "5":
			return new WithdrawPrompt();
		case "6":
			if (authUtil.getCurrentUser().isAdmin()) {
				return new ViewAccountAdminPrompt();
			}
		case "L":
			authUtil.logout();
			return new LogInPrompt();
		default:
			System.out.println("invalid selection, try again.");
			break;
		}
		return this;
	}

}