package com.altuna.challenge.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.altuna.challenge.domain.FriendlyFraudNotification;
import com.altuna.challenge.service.FriendlyFraudNotificationService;
import com.altuna.challenge.web.rest.util.HeaderUtil;
import com.altuna.challenge.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FriendlyFraudNotification.
 */
@RestController
@RequestMapping("/api")
public class FriendlyFraudNotificationResource {

    private final Logger log = LoggerFactory.getLogger(FriendlyFraudNotificationResource.class);
        
    @Inject
    private FriendlyFraudNotificationService friendlyFraudNotificationService;

    /**
     * POST  /friendly-fraud-notifications : Create a new friendlyFraudNotification.
     *
     * @param friendlyFraudNotification the friendlyFraudNotification to create
     * @return the ResponseEntity with status 201 (Created) and with body the new friendlyFraudNotification, or with status 400 (Bad Request) if the friendlyFraudNotification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/friendly-fraud-notifications")
    @Timed
    public ResponseEntity<FriendlyFraudNotification> createFriendlyFraudNotification(@RequestBody FriendlyFraudNotification friendlyFraudNotification) throws URISyntaxException {
        log.debug("REST request to save FriendlyFraudNotification : {}", friendlyFraudNotification);
        if (friendlyFraudNotification.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("friendlyFraudNotification", "idexists", "A new friendlyFraudNotification cannot already have an ID")).body(null);
        }
        FriendlyFraudNotification result = friendlyFraudNotificationService.save(friendlyFraudNotification);
        return ResponseEntity.created(new URI("/api/friendly-fraud-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("friendlyFraudNotification", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /friendly-fraud-notifications : Updates an existing friendlyFraudNotification.
     *
     * @param friendlyFraudNotification the friendlyFraudNotification to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated friendlyFraudNotification,
     * or with status 400 (Bad Request) if the friendlyFraudNotification is not valid,
     * or with status 500 (Internal Server Error) if the friendlyFraudNotification couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/friendly-fraud-notifications")
    @Timed
    public ResponseEntity<FriendlyFraudNotification> updateFriendlyFraudNotification(@RequestBody FriendlyFraudNotification friendlyFraudNotification) throws URISyntaxException {
        log.debug("REST request to update FriendlyFraudNotification : {}", friendlyFraudNotification);
        if (friendlyFraudNotification.getId() == null) {
            return createFriendlyFraudNotification(friendlyFraudNotification);
        }
        FriendlyFraudNotification result = friendlyFraudNotificationService.save(friendlyFraudNotification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("friendlyFraudNotification", friendlyFraudNotification.getId().toString()))
            .body(result);
    }

    /**
     * GET  /friendly-fraud-notifications : get all the friendlyFraudNotifications.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of friendlyFraudNotifications in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/friendly-fraud-notifications")
    @Timed
    public ResponseEntity<List<FriendlyFraudNotification>> getAllFriendlyFraudNotifications(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of FriendlyFraudNotifications");
        Page<FriendlyFraudNotification> page = friendlyFraudNotificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/friendly-fraud-notifications");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /friendly-fraud-notifications/:id : get the "id" friendlyFraudNotification.
     *
     * @param id the id of the friendlyFraudNotification to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the friendlyFraudNotification, or with status 404 (Not Found)
     */
    @GetMapping("/friendly-fraud-notifications/{id}")
    @Timed
    public ResponseEntity<FriendlyFraudNotification> getFriendlyFraudNotification(@PathVariable Long id) {
        log.debug("REST request to get FriendlyFraudNotification : {}", id);
        FriendlyFraudNotification friendlyFraudNotification = friendlyFraudNotificationService.findOne(id);
        return Optional.ofNullable(friendlyFraudNotification)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /friendly-fraud-notifications/:id : delete the "id" friendlyFraudNotification.
     *
     * @param id the id of the friendlyFraudNotification to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/friendly-fraud-notifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteFriendlyFraudNotification(@PathVariable Long id) {
        log.debug("REST request to delete FriendlyFraudNotification : {}", id);
        friendlyFraudNotificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("friendlyFraudNotification", id.toString())).build();
    }

}
