package com.example.quartzimpl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("HelloJob executed at " + java.time.Instant.now());
        // you can access JobDataMap via context if you passed data
    }
}