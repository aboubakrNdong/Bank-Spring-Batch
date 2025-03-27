package org.id.bank_Spring_batch.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.id.bank_Spring_batch.itemsConfig.BankTransactionItemAnalyticsProcessor;
import org.id.bank_Spring_batch.service.JobService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/batch")
public class JobRestController {
    private static final Logger logger = LoggerFactory.getLogger(JobRestController.class);

    private final JobService jobService;

    private final BankTransactionItemAnalyticsProcessor analyticsProcessor;

    public JobRestController(JobService jobService,
            BankTransactionItemAnalyticsProcessor analyticsProcessor) {
        this.jobService = jobService;
        this.analyticsProcessor = analyticsProcessor;
    }

    @GetMapping("/startJob")
    public ResponseEntity<BatchStatus> startJob() throws JobExecutionException {
        BatchStatus status = jobService.runJob();
        logger.info("Job execution completed with status: {}", status);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, BigDecimal>> getAnalytics() {
        Map<String, BigDecimal> analytics = new HashMap<>();
        analytics.put("totalCredit", analyticsProcessor.getTotalCredit());
        analytics.put("totalDebit", analyticsProcessor.getTotalDebit());

        return ResponseEntity.ok(analytics);

    }

}
