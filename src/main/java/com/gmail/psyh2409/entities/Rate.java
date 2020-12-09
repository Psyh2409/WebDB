package com.gmail.psyh2409.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EUR")
    private Double eur;

    @Column(name = "USD")
    private Double usd;

    @Column(name = "currentDate")
    private LocalDate date;

    public Rate() {
        super();
        this.date = LocalDate.now();
    }

    public Rate(Double eur, Double usd) {
        this.eur = eur;
        this.usd = usd;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", eur=" + eur +
                ", usd=" + usd +
                ", date=" + date +
                '}';
    }
}
