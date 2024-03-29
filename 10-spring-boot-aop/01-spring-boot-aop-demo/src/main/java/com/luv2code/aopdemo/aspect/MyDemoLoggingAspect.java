package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

//    @Before("execution(public void addAccount())")

//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")

//    @Before("execution(public void add*())")

//    @Before("execution(* add*())")

//    @Before("execution(* add*(com.luv2code.aopdemo.Account,..))")

//    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")

    @Before("execution(* com.luv2code.aopdemo.dao.*.add*(..))")
    public void addAccountBeforeAdvice()
    {
        System.out.println("\n =======>>>>>> executing @Before advice on addAccount()");
    }
}
