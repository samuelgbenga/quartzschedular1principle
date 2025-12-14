package com.example.quartzimpl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Good for you " + java.time.Instant.now());
        // you can access JobDataMap via context if you passed data

        String message = context.getMergedJobDataMap().getString("message");
        log.info("HelloJob executed at " + message);
    }
}
