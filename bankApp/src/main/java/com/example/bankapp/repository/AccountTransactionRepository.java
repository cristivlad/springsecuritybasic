package com.example.bankapp.repository;

import com.example.bankapp.model.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);
}
