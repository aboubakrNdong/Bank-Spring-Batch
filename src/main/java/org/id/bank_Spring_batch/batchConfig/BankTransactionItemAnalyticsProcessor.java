package org.id.bank_Spring_batch.batchConfig;

import java.math.BigDecimal;

import org.id.bank_Spring_batch.model.BankTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import lombok.Getter;
import static org.id.bank_Spring_batch.utils.Constants.*;

@Component
public class BankTransactionItemAnalyticsProcessor implements ItemProcessor<BankTransaction, BankTransaction> {

    @Getter
    private BigDecimal totalDebit = BigDecimal.ZERO;

    @Getter
    private BigDecimal totalCredit = BigDecimal.ZERO;

    @Override
    public BankTransaction process(BankTransaction bankTransaction) {
        if (bankTransaction == null) {
            return null;
        }

        updateTotals(bankTransaction);
        return bankTransaction;
    }

    private void updateTotals(BankTransaction transaction) {
        String transactionType = transaction.getTransactionType();
        BigDecimal amount = BigDecimal.valueOf(transaction.getAmount());

        if (DEBIT_TYPE.equals(transactionType)) {
            totalDebit = totalDebit.add(amount);
        } else if (CREDIT_TYPE.equals(transactionType)) {
            totalCredit = totalCredit.add(amount);
        }
    }

}
