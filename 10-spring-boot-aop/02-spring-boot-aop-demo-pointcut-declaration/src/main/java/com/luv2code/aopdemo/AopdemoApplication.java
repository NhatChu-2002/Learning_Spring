package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO)
	{
		return runner -> {
//			demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
			
			demoTheAfterReturningAdvice(theAccountDAO);
		};
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		//call the method to find accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();

		System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");

	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){

		Account theAccount = new Account();
		theAccount.setName("Badoo");
		theAccount.setLevel("silver");
		theAccountDAO.addAccount(theAccount,true);

		theAccountDAO.doWork();

		// call the account getter and setter methods
		theAccountDAO.setName("fool");
		theAccountDAO.setServiceCode("silver");

//		String name = theAccountDAO.getName();
//		String code = theAccountDAO.getServiceCode();

//		theMembershipDAO.addAccount();

//		theMembershipDAO.addSillyMember();

//		System.out.println("Again! ");

//		theAccountDAO.addAccount();

	}

}
