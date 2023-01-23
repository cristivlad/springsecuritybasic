package com.example.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "customer")
@Getter
@Setter
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String email;
    private String mobileNumber;
    @JsonProperty(access = WRITE_ONLY)
    private String pwd;
    private String role;
    private String createDt;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch= EAGER)
    private Set<Authority> authorities;
}
