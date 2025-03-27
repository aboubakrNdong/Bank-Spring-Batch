package org.id.bank_Spring_batch.itemsConfig;


import org.id.bank_Spring_batch.model.BankTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component
public class BankTransactionItemProcessor implements ItemProcessor<BankTransaction, BankTransaction> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        Date transactionDate = dateFormat.parse(bankTransaction.getStrTransactionDate());
        bankTransaction.setTransactionDate(transactionDate);
        return bankTransaction;
    }
}
