package com.altuna.challenge.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Refund.
 */
@Entity
@Table(name = "refund")
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private String amount;

    @ManyToOne
    private Dispute dispute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Refund date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public Refund amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Dispute getDispute() {
        return dispute;
    }

    public Refund dispute(Dispute dispute) {
        this.dispute = dispute;
        return this;
    }

    public void setDispute(Dispute dispute) {
        this.dispute = dispute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Refund refund = (Refund) o;
        if (refund.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, refund.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Refund{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", amount='" + amount + "'" +
            '}';
    }
}
