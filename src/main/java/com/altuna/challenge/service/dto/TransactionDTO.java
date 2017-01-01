package com.altuna.challenge.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Transaction entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private String amount;

    private String addressVerification;

    private String cvv;

    private String authCode;


    private Long refundId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAddressVerification() {
        return addressVerification;
    }

    public void setAddressVerification(String addressVerification) {
        this.addressVerification = addressVerification;
    }
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;

        if ( ! Objects.equals(id, transactionDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", amount='" + amount + "'" +
            ", addressVerification='" + addressVerification + "'" +
            ", cvv='" + cvv + "'" +
            ", authCode='" + authCode + "'" +
            '}';
    }
}
