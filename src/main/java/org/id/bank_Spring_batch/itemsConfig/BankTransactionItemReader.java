package org.id.bank_Spring_batch.itemsConfig;

import org.id.bank_Spring_batch.model.BankTransaction;
import static org.id.bank_Spring_batch.utils.Constants.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class BankTransactionItemReader {

    

    @Bean
    public FlatFileItemReader<BankTransaction> flatFileItemReader(@Value("${inputFile}") Resource inputResource) {
        FlatFileItemReader<BankTransaction> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setName(READER_NAME);
        fileItemReader.setLinesToSkip(LINES_TO_SKIP);
        fileItemReader.setResource(inputResource);
        fileItemReader.setLineMapper(createLineMapper());
        return fileItemReader;
    }

    private LineMapper<BankTransaction> createLineMapper() {
        DefaultLineMapper<BankTransaction> lineMapper = new DefaultLineMapper<>();
        
        lineMapper.setLineTokenizer(createLineTokenizer());
        lineMapper.setFieldSetMapper(createFieldSetMapper());
        
        return lineMapper;
    }
    
    private DelimitedLineTokenizer createLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DELIMITER);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(FIELD_NAMES);
        return lineTokenizer;
    }

    private BeanWrapperFieldSetMapper<BankTransaction> createFieldSetMapper() {
        BeanWrapperFieldSetMapper<BankTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BankTransaction.class);
        return fieldSetMapper;
    }

}
