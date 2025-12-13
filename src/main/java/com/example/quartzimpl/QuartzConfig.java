package com.example.quartzimpl;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail helloJobDetail() {
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob")
                .storeDurably()           // keeps the JobDetail in the Scheduler even without triggers
                .build();
    }

    @Bean
    public Trigger helloJobTrigger() {
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)   // run every 10 seconds
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetail())
                .withIdentity("helloTrigger")
                .withSchedule(schedule)
                .build();
    }
}
