package org.id.bank_Spring_batch;

import org.id.bank_Spring_batch.model.BankTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.id.bank_Spring_batch.utils.Constants.*;



@Configuration
@EnableBatchProcessing

public class SpringBatchConfig {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private ItemReader<BankTransaction> bankTransactionItemReader;
    @Autowired
    private ItemWriter<BankTransaction> bankTransactionItemWriter;
    @Autowired
    private ItemProcessor<BankTransaction, BankTransaction> bankTransactionItemProcessor;


    @Bean
    public Step transactionStep1() {
        return new StepBuilder(TRANSACTION_STEP_NAME, jobRepository)
                .<BankTransaction, BankTransaction>chunk(TRANSACTION_CHUNK_SIZE,transactionManager)
                .reader(bankTransactionItemReader)
                .processor(bankTransactionItemProcessor)
                .writer(bankTransactionItemWriter)
                .build();
    }

    @Bean
    public Job transactionJob() {
        return new JobBuilder(ETL_JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(transactionStep1())
                .build();
    }


}