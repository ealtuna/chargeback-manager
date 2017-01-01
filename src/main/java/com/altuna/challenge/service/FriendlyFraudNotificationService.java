package com.altuna.challenge.service;

import com.altuna.challenge.domain.FriendlyFraudNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing FriendlyFraudNotification.
 */
public interface FriendlyFraudNotificationService {

    /**
     * Save a friendlyFraudNotification.
     *
     * @param friendlyFraudNotification the entity to save
     * @return the persisted entity
     */
    FriendlyFraudNotification save(FriendlyFraudNotification friendlyFraudNotification);

    /**
     *  Get all the friendlyFraudNotifications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<FriendlyFraudNotification> findAll(Pageable pageable);

    /**
     *  Get the "id" friendlyFraudNotification.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FriendlyFraudNotification findOne(Long id);

    /**
     *  Delete the "id" friendlyFraudNotification.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
