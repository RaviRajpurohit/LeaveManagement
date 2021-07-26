package com.practice;

import java.util.TimerTask;
import java.util.logging.Logger;

public class SchedulerJob extends TimerTask {
    private static final Logger logger = Logger.getLogger(SchedulerJob.class.getName());

    @Override
    public void run() {
        logger.entering("SchedulerJob", "run");
        try{
            LeaveManager leaveManager = ApplicationRunner.leaveManager;

            if (leaveManager != null) {
                leaveManager.manageLeaves();
            }
        } catch (Exception e) {
            logger.warning("Leave Management Application failed with some error, Please check above logs.");
            logger.warning("Please resolve error as soon as possible you can. otherwise stop application.");
        }
        logger.exiting("SchedulerJob", "run");
    }
}
