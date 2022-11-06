package com.aninfo.repository;

import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Collection<Transaction> findTransactionByCbu(Long cbu);

    Transaction findTransactionById(Long id);

    @Override
    List<Transaction> findAll();
}
