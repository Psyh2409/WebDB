package com.gmail.psyh2409.entities;

import com.gmail.psyh2409.utils.Action;
import com.gmail.psyh2409.utils.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clients_id")
    private Client client;

    @Column(name = "Account")
    private Long accountNumber;

    @Column(name = "AccountCurrency")
    private Currency accCurrency;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "Rest")
    private BigDecimal rest;

    @Column(name = "Date")
    private LocalDateTime dateTime;

    @Column (name = "Target")
    private Long target;

    @Column(name = "TargetCurrency")
    private Currency tarCurrency;

    @Column(name = "Action")
    @Enumerated(EnumType.STRING)
    private Action action;

    public Transaction() {
        super();
        dateTime = LocalDateTime.now();
    }

    public Transaction(Client client, Long accountNumber, Currency accCurrency, BigDecimal amount,
                       BigDecimal rest, Long target, Currency tarCurrency, Action action) {
        this.client = client;
        this.accountNumber = accountNumber;
        this.accCurrency = accCurrency;
        this.amount = amount;
        this.rest = rest;
        this.dateTime = LocalDateTime.now();
        this.target = target;
        this.tarCurrency = tarCurrency;
        this.action = action;
    }

    public Currency getAccCurrency() {
        return accCurrency;
    }

    public void setAccCurrency(Currency accCurrency) {
        this.accCurrency = accCurrency;
    }

    public Currency getTarCurrency() {
        return tarCurrency;
    }

    public void setTarCurrency(Currency tarCurrency) {
        this.tarCurrency = tarCurrency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", accountNumber=" + accountNumber +
                ", accCurrency=" + accCurrency +
                ", amount=" + amount +
                ", rest=" + rest +
                ", dateTime=" + dateTime +
                ", target=" + target +
                ", tarCurrency=" + tarCurrency +
                ", action=" + action +
                '}';
    }
}
