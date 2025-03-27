package org.id.bank_Spring_batch.itemsConfig;

import org.id.bank_Spring_batch.model.BankTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import lombok.Getter;

@Component
public class BankTransactionItemAnalyticsProcessor implements ItemProcessor<BankTransaction, BankTransaction> {

    @Getter
    private double totalDebit;

    @Getter
    private double totalCredit;


    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {

        if(bankTransaction.getTransactionType().equals("D"))
            totalDebit += bankTransaction.getAmount();
        else if (bankTransaction.getTransactionType().equals("C"))
            totalCredit += bankTransaction.getAmount();
        return bankTransaction;
    }
}
