package com.example.quartzimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class QuartzConfig {

    @Autowired
    @Lazy
    private Scheduler scheduler;

    @Bean
    public JobDetail helloJobDetail() {
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("helloJob")
                .usingJobData("message", "message-from-job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger helloJobTrigger(JobDetail helloJobDetail) {
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(2)
                .withIntervalInSeconds(10);

        return TriggerBuilder.newTrigger()
                .forJob(helloJobDetail)
                .withIdentity("helloTrigger")
                .usingJobData("message", "message-from-trigger")
                .withSchedule(schedule)
                .build();
    }

    // This method runs after the application context is ready
    @EventListener(ApplicationReadyEvent.class)
    public void updateTriggerIfExists() {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey("helloTrigger");
            Trigger oldTrigger = scheduler.getTrigger(triggerKey);

            if (oldTrigger != null) {
                log.info("Updating existing trigger: {}", triggerKey);

                // Define a new schedule (e.g., every 20 seconds instead of 10)
                SimpleScheduleBuilder newSchedule = SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(4)
                        .withIntervalInSeconds(10);

                // Rebuild trigger with new schedule
                Trigger newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(oldTrigger.getKey())
                        .forJob(oldTrigger.getJobKey())
                        .usingJobData("message", "message-UPDATED")
                        .withSchedule(newSchedule)
                        .build();

                // Reschedule it
                scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
                log.info("Trigger rescheduled successfully");
            } else {
                log.info("No existing trigger found, using bean definition");
            }
        } catch (SchedulerException e) {
            log.error("Failed to reschedule trigger", e);
        }
    }
}