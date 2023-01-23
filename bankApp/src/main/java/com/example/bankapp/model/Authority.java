package com.example.bankapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "authorities")
@Getter
public class Authority {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;

}
