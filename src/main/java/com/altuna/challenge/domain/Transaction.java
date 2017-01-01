package com.altuna.challenge.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private String amount;

    @Column(name = "address_verification")
    private String addressVerification;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "auth_code")
    private String authCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transaction date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public Transaction amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddressVerification() {
        return addressVerification;
    }

    public Transaction addressVerification(String addressVerification) {
        this.addressVerification = addressVerification;
        return this;
    }

    public void setAddressVerification(String addressVerification) {
        this.addressVerification = addressVerification;
    }

    public String getCvv() {
        return cvv;
    }

    public Transaction cvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getAuthCode() {
        return authCode;
    }

    public Transaction authCode(String authCode) {
        this.authCode = authCode;
        return this;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        if (transaction.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, transaction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", amount='" + amount + "'" +
            ", addressVerification='" + addressVerification + "'" +
            ", cvv='" + cvv + "'" +
            ", authCode='" + authCode + "'" +
            '}';
    }
}
