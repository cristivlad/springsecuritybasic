package com.example.bankapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "account_transactions")
@Getter
@Setter
@RequiredArgsConstructor
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int transactionId;
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "transaction_dt")
    private Date transactionDt;
    @Column(name = "transaction_summary")
    private String transactionSummary;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "transaction_amt")
    private int transactionAmt;
    @Column(name = "closing_balance")
    private int closingBalance;
    @Column(name = "create_dt")
    private String createDt;
}
