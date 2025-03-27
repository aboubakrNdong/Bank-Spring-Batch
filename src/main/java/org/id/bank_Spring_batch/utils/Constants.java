package org.id.bank_Spring_batch.utils;

public class Constants {
    
    public static final String ETL_JOB_NAME = "ETL-Load";
    public static final String TRANSACTION_STEP_NAME = "ETL-Transaction-File-Load";
    public static final int TRANSACTION_CHUNK_SIZE = 100;

    
    public static final String DEBIT_TYPE = "D";
    public static final String CREDIT_TYPE = "C";

      
    public static final String READER_NAME = "BankTransactionReader";
    public static final String DELIMITER = ",";
    public static final String[] FIELD_NAMES = {"id", "accoundID", "strTransactionDate", "transactionType", "amount"};
    public static final int LINES_TO_SKIP = 1;
    
}
