package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class CricketCoach implements Coach{
    public CricketCoach()
    {
        System.out.println("In constructor:" + getClass().getSimpleName());
    }

    //define init method
    @PostConstruct
    public void doMyStartupstuff()
    {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());

    }
    @PreDestroy
    public void doMyCleanupstuff()
    {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());

    }

    //define destroy method
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 mins  ";
    }
}
