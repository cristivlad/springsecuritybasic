package com.example.bankapp.controller;

import com.example.bankapp.model.AccountTransaction;
import com.example.bankapp.model.Customer;
import com.example.bankapp.repository.AccountTransactionRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionRepository accountTransactionRepository;
    private CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
        List<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail != null && !byEmail.isEmpty()) {
            return accountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(byEmail.get(0).getId());
        }
        return null;
    }
}
