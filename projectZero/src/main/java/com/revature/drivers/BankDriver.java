package com.revature.drivers;

import org.apache.log4j.Logger;

import com.revature.prompts.LogInPrompt;
import com.revature.prompts.Prompt;

public class BankDriver {
	private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
		Prompt p = new LogInPrompt();
		log.debug("STARTING A NEW INSTANCE OF THE DRIVER-----------------------------------------------------------------------------------------");
		while (true) {
			log.debug("starting prompt of type: " + p.getClass());
			p = p.run();
			log.debug("next prompt is of type: " + p.getClass());
		}

	}
}
