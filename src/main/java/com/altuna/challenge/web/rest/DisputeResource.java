package com.altuna.challenge.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.domain.enumeration.AnalystDesition;
import com.altuna.challenge.domain.enumeration.DisputeStatus;
import com.altuna.challenge.service.DisputeService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Dispute.
 */
@RestController
@RequestMapping("/api")
public class DisputeResource {

    private final Logger log = LoggerFactory.getLogger(DisputeResource.class);
        
    @Inject
    private DisputeService disputeService;

    /**
     * POST  /disputes : Create a new dispute.
     *
     * @param dispute the dispute to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dispute, or with status 400 (Bad Request) if the dispute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disputes")
    @Timed
    public ResponseEntity<Dispute> createDispute(@RequestBody Dispute dispute) throws URISyntaxException {
        log.debug("REST request to save Dispute : {}", dispute);
        if (dispute.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("dispute", "idexists", "A new dispute cannot already have an ID")).body(null);
        }
        dispute.setStatus(DisputeStatus.NEW);
        Dispute result = disputeService.save(dispute);
        return ResponseEntity.created(new URI("/api/disputes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("dispute", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disputes : Updates an existing dispute.
     *
     * @param dispute the dispute to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dispute,
     * or with status 400 (Bad Request) if the dispute is not valid,
     * or with status 500 (Internal Server Error) if the dispute couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disputes")
    @Timed
    public ResponseEntity<Dispute> updateDispute(@RequestBody Dispute dispute) throws URISyntaxException {
        log.debug("REST request to update Dispute : {}", dispute);
        if (dispute.getId() == null) {
            return createDispute(dispute);
        }
        Dispute result = disputeService.save(dispute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("dispute", dispute.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disputes : get all the disputes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of disputes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/disputes")
    @Timed
    public ResponseEntity<List<Dispute>> getAllNewDisputes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Disputes");
        Page<Dispute> page = disputeService.findByState(pageable, DisputeStatus.NEW);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/disputes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/disputes/processing")
    @Timed
    public ResponseEntity<List<Dispute>> getAllProcessingDisputes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Disputes");
        Page<Dispute> page = disputeService.findByState(pageable, DisputeStatus.PROCESSING);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/disputes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/disputes/desided")
    @Timed
    public ResponseEntity<List<Dispute>> getAllDesidedDisputes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Disputes");
        List<DisputeStatus> statuses = new ArrayList<>();
        statuses.add(DisputeStatus.LOST);
        statuses.add(DisputeStatus.WON);
        statuses.add(DisputeStatus.NOT_FOUGHT);
        Page<Dispute> page = disputeService.findByStateIn(pageable, statuses);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/disputes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/disputes/desitionpending")
    @Timed
    public ResponseEntity<List<Dispute>> getAllDesitionPendingDisputes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Disputes");
        Page<Dispute> page = disputeService.findByState(pageable, DisputeStatus.DESICION_PENDING);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/disputes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /disputes/:id : get the "id" dispute.
     *
     * @param id the id of the dispute to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dispute, or with status 404 (Not Found)
     */
    @GetMapping("/disputes/{id}")
    @Timed
    public ResponseEntity<Dispute> getDispute(@PathVariable Long id) {
        log.debug("REST request to get Dispute : {}", id);
        Dispute dispute = disputeService.findOne(id);
        return Optional.ofNullable(dispute)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/disputes/processing/{id}")
    @Timed
    public ResponseEntity<Void> processingDispute(@PathVariable Long id) {
        log.debug("REST request to get Dispute : {}", id);
        Dispute dispute = disputeService.findOne(id);
        dispute.setStatus(DisputeStatus.PROCESSING);
        disputeService.save(dispute);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }
    
    @PutMapping("/disputes/truefraud/{id}")
    @Timed
    public ResponseEntity<Void> setTrueFraud(@PathVariable Long id) {
        log.debug("REST request to get Dispute : {}", id);
        Dispute dispute = disputeService.findOne(id);
        dispute.setStatus(DisputeStatus.NOT_FOUGHT);
        dispute.setAnalystDesition(AnalystDesition.TRUE_FRAUD);
        disputeService.save(dispute);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }
    
    @PutMapping("/disputes/{id}/friendlyfraud")
    @Timed
    public ResponseEntity<Void> setFriendlyFraud(@PathVariable Long id) {
        log.debug("REST request to get Dispute : {}", id);
        Dispute dispute = disputeService.findOne(id);
        dispute.setStatus(DisputeStatus.DESICION_PENDING);
        dispute.setAnalystDesition(AnalystDesition.FRIENDLY_FRAUD);
        disputeService.save(dispute);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }
    
    @PostMapping("/disputes/undecide/{id}")
    @Timed
    public ResponseEntity<Void> setUndecided(@RequestBody Dispute dispute) {
    	Long id = dispute.getId();
        Dispute disputeDb = disputeService.findOne(id);
        disputeDb.setNotes(dispute.getNotes());
        disputeDb.setStatus(DisputeStatus.NOT_FOUGHT);
        disputeDb.setAnalystDesition(AnalystDesition.UNABLE_TO_DECIDE);
        disputeService.save(disputeDb);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }
    
    @PostMapping("/disputes/desided/{id}")
    @Timed
    public ResponseEntity<Void> setDecided(@RequestBody Dispute dispute) {
    	Long id = dispute.getId();
        Dispute disputeDb = disputeService.findOne(id);
        disputeDb.setStatus(dispute.getStatus());
        disputeService.save(disputeDb);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }

    /**
     * DELETE  /disputes/:id : delete the "id" dispute.
     *
     * @param id the id of the dispute to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disputes/{id}")
    @Timed
    public ResponseEntity<Void> deleteDispute(@PathVariable Long id) {
        log.debug("REST request to delete Dispute : {}", id);
        disputeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dispute", id.toString())).build();
    }

}
