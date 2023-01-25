package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Card;
import com.example.bankapp.model.Customer;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Card> getCardDetails(@RequestParam String email) {
        List<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail != null && !byEmail.isEmpty()) {
            return cardRepository.findByCustomerId(byEmail.get(0).getId());
        }
        return Collections.emptyList();
    }
}
