package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Customer;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam String email) {
        List<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail != null && !byEmail.isEmpty()) {
            return accountRepository.findByCustomerId(byEmail.get(0).getId());
        }
        return null;
    }
}
