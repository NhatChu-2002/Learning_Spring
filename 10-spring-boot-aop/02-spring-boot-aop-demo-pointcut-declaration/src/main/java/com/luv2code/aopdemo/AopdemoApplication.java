package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
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
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficForTuneService)
	{
		return runner -> {
//			demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
			
//			demoTheAfterReturningAdvice(theAccountDAO);

//			demoTheAfterThrowingAdvice(theAccountDAO);

//			demoTheAfterAdvice(theAccountDAO);

//			demoTheAroundAdvice(theTrafficForTuneService);

//			demoTheAroundAdviceHandleException(theTrafficForTuneService);

			demoTheAroundAdviceRethrowException(theTrafficForTuneService);

		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficForTuneService) {
		System.out.println("\nMain program: demoTheAroundAdviceRethrowException");
		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = theTrafficForTuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficForTuneService) {
		System.out.println("\nMain program: demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = theTrafficForTuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");

	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficForTuneService) {
		System.out.println("\nMain program: demoTheAroundAdvice");
		System.out.println("Calling getFortune()");

		String data = theTrafficForTuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try{
			boolean tripWire = false;

			theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc)
		{
			System.out.println("\n\nMain Program: ....caught exc: " + exc);
		}


		System.out.println("\n\nMain program: demoTheAfterThrowingAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		//call the method to find accounts
		List<Account> theAccounts = null;
		try{
			boolean tripWire = true;

			theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc)
		{
			System.out.println("\n\nMain Program: ....caught exc: " + exc);
		}


		System.out.println("\n\nMain program: demoTheAfterThrowingAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");
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
