package com.altuna.challenge.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.altuna.challenge.domain.enumeration.DisputeType;
import com.altuna.challenge.domain.enumeration.CardType;

/**
 * A DTO for the Dispute entity.
 */
public class DisputeDTO implements Serializable {

    private Long id;

    @NotNull
    private String status;

    private DisputeType disputeType;

    private String caseNumber;

    private LocalDate filedDay;

    private Double amount;

    private CardType cardType;

    private String reasonCode;


    private Long transactionId;
    
    private Long shippingId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public DisputeType getDisputeType() {
        return disputeType;
    }

    public void setDisputeType(DisputeType disputeType) {
        this.disputeType = disputeType;
    }
    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }
    public LocalDate getFiledDay() {
        return filedDay;
    }

    public void setFiledDay(LocalDate filedDay) {
        this.filedDay = filedDay;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DisputeDTO disputeDTO = (DisputeDTO) o;

        if ( ! Objects.equals(id, disputeDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DisputeDTO{" +
            "id=" + id +
            ", status='" + status + "'" +
            ", disputeType='" + disputeType + "'" +
            ", caseNumber='" + caseNumber + "'" +
            ", filedDay='" + filedDay + "'" +
            ", amount='" + amount + "'" +
            ", cardType='" + cardType + "'" +
            ", reasonCode='" + reasonCode + "'" +
            '}';
    }
}
