package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
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
    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsService(JoinPoint theJointPoint,Throwable theExc)
    {
        String method = theJointPoint.getSignature().toShortString();
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
