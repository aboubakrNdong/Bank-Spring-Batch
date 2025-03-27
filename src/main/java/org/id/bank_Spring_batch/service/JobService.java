package org.id.bank_Spring_batch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public BatchStatus runJob() throws JobExecutionException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis(), true)
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        while (jobExecution.isRunning()) {
            logger.info("Job is running...please wait...");
        }

        BatchStatus status = jobExecution.getStatus();
        logger.info("Job execution status: {}", status);

        return status;
    }
}
