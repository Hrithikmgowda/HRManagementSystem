package com.example.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerConfig {

    private static final Logger logger =
            LoggerFactory.getLogger(SchedulerConfig.class);

    @Scheduled(cron = "0 0 9 * * *")
    public void dailyHrCheck() {

        logger.info("Daily HR system scheduled check is running.");
    }
}

