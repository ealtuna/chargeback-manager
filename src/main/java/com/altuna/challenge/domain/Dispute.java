package com.altuna.challenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.altuna.challenge.domain.enumeration.DisputeStatus;

import com.altuna.challenge.domain.enumeration.DisputeType;

import com.altuna.challenge.domain.enumeration.CardType;

import com.altuna.challenge.domain.enumeration.AnalystDesition;

/**
 * A Dispute.
 */
@Entity
@Table(name = "dispute")
public class Dispute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DisputeStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "dispute_type")
    private DisputeType disputeType;

    @Column(name = "case_number")
    private String caseNumber;

    @Column(name = "filed_day")
    private LocalDate filedDay;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @Column(name = "reason_code")
    private String reasonCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "analyst_desition")
    private AnalystDesition analystDesition;

    @Column(name = "notes")
    private String notes;

    @OneToOne
    @JoinColumn(unique = true)
    private Transaction transaction;

    @OneToOne
    @JoinColumn(unique = true)
    private Shipping shipping;

    @OneToMany(mappedBy = "dispute")
    @JsonIgnore
    private Set<Refund> refunds = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisputeStatus getStatus() {
        return status;
    }

    public Dispute status(DisputeStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    public DisputeType getDisputeType() {
        return disputeType;
    }

    public Dispute disputeType(DisputeType disputeType) {
        this.disputeType = disputeType;
        return this;
    }

    public void setDisputeType(DisputeType disputeType) {
        this.disputeType = disputeType;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public Dispute caseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
        return this;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public LocalDate getFiledDay() {
        return filedDay;
    }

    public Dispute filedDay(LocalDate filedDay) {
        this.filedDay = filedDay;
        return this;
    }

    public void setFiledDay(LocalDate filedDay) {
        this.filedDay = filedDay;
    }

    public Double getAmount() {
        return amount;
    }

    public Dispute amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Dispute cardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public Dispute reasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
        return this;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public AnalystDesition getAnalystDesition() {
        return analystDesition;
    }

    public Dispute analystDesition(AnalystDesition analystDesition) {
        this.analystDesition = analystDesition;
        return this;
    }

    public void setAnalystDesition(AnalystDesition analystDesition) {
        this.analystDesition = analystDesition;
    }

    public String getNotes() {
        return notes;
    }

    public Dispute notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Dispute transaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public Dispute shipping(Shipping shipping) {
        this.shipping = shipping;
        return this;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Set<Refund> getRefunds() {
        return refunds;
    }

    public Dispute refunds(Set<Refund> refunds) {
        this.refunds = refunds;
        return this;
    }

    public Dispute addRefunds(Refund refund) {
        refunds.add(refund);
        refund.setDispute(this);
        return this;
    }

    public Dispute removeRefunds(Refund refund) {
        refunds.remove(refund);
        refund.setDispute(null);
        return this;
    }

    public void setRefunds(Set<Refund> refunds) {
        this.refunds = refunds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dispute dispute = (Dispute) o;
        if (dispute.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, dispute.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Dispute{" +
            "id=" + id +
            ", status='" + status + "'" +
            ", disputeType='" + disputeType + "'" +
            ", caseNumber='" + caseNumber + "'" +
            ", filedDay='" + filedDay + "'" +
            ", amount='" + amount + "'" +
            ", cardType='" + cardType + "'" +
            ", reasonCode='" + reasonCode + "'" +
            ", analystDesition='" + analystDesition + "'" +
            ", notes='" + notes + "'" +
            '}';
    }
}
