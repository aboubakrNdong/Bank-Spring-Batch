package org.id.bank_Spring_batch.repository;

import org.id.bank_Spring_batch.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

}
