package com.example.quartzimpl;

import org.quartz.*;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class JobService {
    private final Scheduler scheduler;

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    public void scheduleOneOffJob(String message) throws SchedulerException {
        JobDetail jd = JobBuilder.newJob(HelloJob2.class)
                .withIdentity("job-" + UUID.randomUUID())
                .usingJobData("message", message)
                .build();

        Trigger t = TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(30, DateBuilder.IntervalUnit.SECOND)) // 15 sec from now
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                .build();

        scheduler.scheduleJob(jd, t);
    }
}
