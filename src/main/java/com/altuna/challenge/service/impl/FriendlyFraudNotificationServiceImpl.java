package com.altuna.challenge.service.impl;

import com.altuna.challenge.service.FriendlyFraudNotificationService;
import com.altuna.challenge.domain.FriendlyFraudNotification;
import com.altuna.challenge.repository.FriendlyFraudNotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing FriendlyFraudNotification.
 */
@Service
@Transactional
public class FriendlyFraudNotificationServiceImpl implements FriendlyFraudNotificationService{

    private final Logger log = LoggerFactory.getLogger(FriendlyFraudNotificationServiceImpl.class);
    
    @Inject
    private FriendlyFraudNotificationRepository friendlyFraudNotificationRepository;

    /**
     * Save a friendlyFraudNotification.
     *
     * @param friendlyFraudNotification the entity to save
     * @return the persisted entity
     */
    public FriendlyFraudNotification save(FriendlyFraudNotification friendlyFraudNotification) {
        log.debug("Request to save FriendlyFraudNotification : {}", friendlyFraudNotification);
        FriendlyFraudNotification result = friendlyFraudNotificationRepository.save(friendlyFraudNotification);
        return result;
    }

    /**
     *  Get all the friendlyFraudNotifications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<FriendlyFraudNotification> findAll(Pageable pageable) {
        log.debug("Request to get all FriendlyFraudNotifications");
        Page<FriendlyFraudNotification> result = friendlyFraudNotificationRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one friendlyFraudNotification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FriendlyFraudNotification findOne(Long id) {
        log.debug("Request to get FriendlyFraudNotification : {}", id);
        FriendlyFraudNotification friendlyFraudNotification = friendlyFraudNotificationRepository.findOne(id);
        return friendlyFraudNotification;
    }

    /**
     *  Delete the  friendlyFraudNotification by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FriendlyFraudNotification : {}", id);
        friendlyFraudNotificationRepository.delete(id);
    }
}
