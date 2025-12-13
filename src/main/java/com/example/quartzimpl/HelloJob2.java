package com.example.quartzimpl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class HelloJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Good for you " + java.time.Instant.now());
        // you can access JobDataMap via context if you passed data
    }
}
