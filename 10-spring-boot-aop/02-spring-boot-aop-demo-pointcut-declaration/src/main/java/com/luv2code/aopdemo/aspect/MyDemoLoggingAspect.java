package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

//    @Before("execution(public void addAccount())")

//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")

//    @Before("execution(public void add*())")

//    @Before("execution(* add*())")

//    @Before("execution(* add*(com.luv2code.aopdemo.Account,..))")

//    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable
    {
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @Around on method: " + method);

        //get the begin timestamp
        long begin = System.currentTimeMillis();

        //execute the given method
        Object result = null;
        try{
            theProceedingJoinPoint.proceed();
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());

//            result = "Major Accident! But no worries, your private AOP helicopter is on the way";
            //rethrow exception
            throw exc;
        }


        // get the end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end - begin;
        System.out.println("\n=======> Duration: " + duration/1000.0 + " seconds");

        return result;
    }
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint)
    {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @After on method: " + method);
    }
    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsService(JoinPoint theJoinPoint,Throwable theExc)
    {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterThrowing on method: " + method);

        System.out.println("\n====>>>> theExc is: " + theExc);
    }

    // add a new advice for AfterReturning on findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result)
    {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n====>>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n====>>>> result: " + result);

        // modify return data

        //convert the account name to uppercase
        convertAccountNameToUppercase(result);
        System.out.println("\n====>>>> result: " + result);
    }

    private void convertAccountNameToUppercase(List<Account> result) {
        // loop thru accounts
        for(Account tempAccount: result)
        {
            //get uppercase version of name
            String theUpperName= tempAccount.getName().toUpperCase();

            //update the name on account
            tempAccount.setName(theUpperName);
        }


    }

    @Before("com.luv2code.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void addAccountBeforeAdvice(JoinPoint theJointPoint) {
        System.out.println("\n=======>>>>> Executing @Before advice on addAccount()");

        //display the method signature
        MethodSignature methodSig = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: " + methodSig);

        //display the method argument

        //get args
        Object[] args = theJointPoint.getArgs();
        //loop thru args
        for(Object tempArg: args)
        {
            System.out.println(tempArg);
            if(tempArg instanceof Account)
            {
                Account theAccount = (Account) tempArg;

                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());

            }
        }

    }



}
