package com.practice;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationRunner {

    static LeaveManager leaveManager;
    private static final Logger logger = Logger.getLogger(ApplicationRunner.class.getName());

    public static void main(String[] args) {
        try {
            logger.info("Leave Management Application starting.");
            Properties properties = new Properties();
            properties.load(new FileReader("../leave-management/src/main/resources/scheduler.properties"));

            String interval = properties.getProperty("leave.management.scheduler.interval");
            if (interval == null || interval.isEmpty()) {
                logger.log(Level.SEVERE, "Application needs leave.management.scheduler.interval property. Please provide interval.");
                System.exit(0);
            }
            SchedulerJob job = new SchedulerJob();

            leaveManager = new LeaveManager();

            logger.info("Leave Management Application schedule for interval " + interval);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(job, 0, Long.parseLong(interval));
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "application failed to load scheduler.properties, properties file must be present at class path.", e);
        }


    }

}
