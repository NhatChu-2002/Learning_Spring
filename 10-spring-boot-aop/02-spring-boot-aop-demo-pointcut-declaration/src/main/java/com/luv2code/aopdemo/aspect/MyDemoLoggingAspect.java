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
