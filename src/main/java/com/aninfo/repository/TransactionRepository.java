package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Override
    List<Transaction> findAll();

    @Override
    List<Transaction> findAllById(Iterable<Long> ids);
}
