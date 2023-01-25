package com.example.bankapp.repository;

import com.example.bankapp.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @PreAuthorize("hasRole('ROOT')")
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
