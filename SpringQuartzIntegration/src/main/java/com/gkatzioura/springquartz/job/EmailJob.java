package com.gkatzioura.springquartz.job;

import com.gkatzioura.springquartz.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gkatzioura on 6/6/16.
 */
public class EmailJob implements Job {

    @Autowired
    private EmailService cronService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        cronService.sendSpam();
    }
}
