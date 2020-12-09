package com.gmail.psyh2409.entities;

import com.gmail.psyh2409.utils.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Number")
    private Long number;
    @Column(name = "CurrencyType")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "Amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    public Account() {
        super();
    }

    public Account(Currency currency, BigDecimal amount, Client client) {
        number = (long) (Math.pow(10, 16) * Math.random());
        this.currency = currency;
        this.amount = amount==null ? new BigDecimal("0") : amount;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", currency=" + currency +
                ", amount=" + amount +
                ", client=" + client.getName() +
                '}';
    }
}
