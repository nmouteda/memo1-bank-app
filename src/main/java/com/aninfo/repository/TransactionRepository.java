package com.aninfo.repository;

import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    List<Transaction> findByAccountCbu(Long cbu);
    Transaction findByAccountCbuAndTransactionNumber(Long cbu, Long transactionNumber);
    void deleteByAccountCbuAndTransactionNumber(Long cbu, Long transactionNumber);
}
