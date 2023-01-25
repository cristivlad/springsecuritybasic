package com.example.bankapp.controller;

import com.example.bankapp.model.Customer;
import com.example.bankapp.model.Loan;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private CustomerRepository customerRepository;

    @PostAuthorize("hasRole('ROOT')")
    @GetMapping("/myLoans")
    public List<Loan> getLoanDetails(@RequestParam String email) {
        List<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail != null && !byEmail.isEmpty()) {
            return loanRepository.findByCustomerIdOrderByStartDtDesc(byEmail.get(0).getId());
        }
        return null;
    }
}
