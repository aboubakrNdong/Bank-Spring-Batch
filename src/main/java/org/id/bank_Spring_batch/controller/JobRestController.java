package org.id.bank_Spring_batch.controller;


import java.util.HashMap;
import java.util.Map;

import org.id.bank_Spring_batch.itemsConfig.BankTransactionItemAnalyticsProcessor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;


@RestController
public class JobRestController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @Autowired
    private BankTransactionItemAnalyticsProcessor bankTransactionItemAnalyticsProcessor;

    @GetMapping("/startJob")
    public BatchStatus load() throws JobExecutionException {

        JobParameters jobParameters = new JobParametersBuilder()
        .addLong("time", System.currentTimeMillis(), true)
        .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            System.out.println("...Job is running...please wait..");
        }
        return jobExecution.getStatus();
    }

    @GetMapping("/analytics")
    public Map<String, Double> analytics () {
        Map<String, Double> map = new HashMap<>();
        map.put("totalCredit",bankTransactionItemAnalyticsProcessor.getTotalCredit());
        map.put("totalDebit",bankTransactionItemAnalyticsProcessor.getTotalDebit());

        return map;

    }

}
 