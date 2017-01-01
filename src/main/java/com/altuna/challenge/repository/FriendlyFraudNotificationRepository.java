package com.altuna.challenge.repository;

import com.altuna.challenge.domain.FriendlyFraudNotification;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FriendlyFraudNotification entity.
 */
@SuppressWarnings("unused")
public interface FriendlyFraudNotificationRepository extends JpaRepository<FriendlyFraudNotification,Long> {

}
