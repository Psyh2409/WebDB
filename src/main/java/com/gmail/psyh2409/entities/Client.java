package com.gmail.psyh2409.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nickname")
    private String name;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Account> accounts;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;

    public Client() {
        super();
        accounts = new LinkedHashSet<>();
        transactions = new LinkedHashSet<Transaction>();
    }

    public Client(String name) {
        this.name = name;
        accounts = new LinkedHashSet<>();
        transactions = new LinkedHashSet<Transaction>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                ", transactions=" + transactions +
                '}';
    }
}
