package com.altuna.challenge.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FriendlyFraudNotification.
 */
@Entity
@Table(name = "friendly_fraud_notification")
public class FriendlyFraudNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "attached_letter")
    private String attachedLetter;

    @OneToOne
    @JoinColumn(unique = true)
    private Dispute dispute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public FriendlyFraudNotification email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttachedLetter() {
        return attachedLetter;
    }

    public FriendlyFraudNotification attachedLetter(String attachedLetter) {
        this.attachedLetter = attachedLetter;
        return this;
    }

    public void setAttachedLetter(String attachedLetter) {
        this.attachedLetter = attachedLetter;
    }

    public Dispute getDispute() {
        return dispute;
    }

    public FriendlyFraudNotification dispute(Dispute dispute) {
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
        FriendlyFraudNotification friendlyFraudNotification = (FriendlyFraudNotification) o;
        if (friendlyFraudNotification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, friendlyFraudNotification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FriendlyFraudNotification{" +
            "id=" + id +
            ", email='" + email + "'" +
            ", attachedLetter='" + attachedLetter + "'" +
            '}';
    }
}
