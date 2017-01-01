package com.altuna.challenge.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.altuna.challenge.domain.Refund;
import com.altuna.challenge.service.RefundService;
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
 * REST controller for managing Refund.
 */
@RestController
@RequestMapping("/api")
public class RefundResource {

    private final Logger log = LoggerFactory.getLogger(RefundResource.class);
        
    @Inject
    private RefundService refundService;

    /**
     * POST  /refunds : Create a new refund.
     *
     * @param refund the refund to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refund, or with status 400 (Bad Request) if the refund has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/refunds")
    @Timed
    public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) throws URISyntaxException {
        log.debug("REST request to save Refund : {}", refund);
        if (refund.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("refund", "idexists", "A new refund cannot already have an ID")).body(null);
        }
        Refund result = refundService.save(refund);
        return ResponseEntity.created(new URI("/api/refunds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("refund", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /refunds : Updates an existing refund.
     *
     * @param refund the refund to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refund,
     * or with status 400 (Bad Request) if the refund is not valid,
     * or with status 500 (Internal Server Error) if the refund couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/refunds")
    @Timed
    public ResponseEntity<Refund> updateRefund(@RequestBody Refund refund) throws URISyntaxException {
        log.debug("REST request to update Refund : {}", refund);
        if (refund.getId() == null) {
            return createRefund(refund);
        }
        Refund result = refundService.save(refund);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("refund", refund.getId().toString()))
            .body(result);
    }

    /**
     * GET  /refunds : get all the refunds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of refunds in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/refunds")
    @Timed
    public ResponseEntity<List<Refund>> getAllRefunds(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Refunds");
        Page<Refund> page = refundService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/refunds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /refunds/:id : get the "id" refund.
     *
     * @param id the id of the refund to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refund, or with status 404 (Not Found)
     */
    @GetMapping("/refunds/{id}")
    @Timed
    public ResponseEntity<Refund> getRefund(@PathVariable Long id) {
        log.debug("REST request to get Refund : {}", id);
        Refund refund = refundService.findOne(id);
        return Optional.ofNullable(refund)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /refunds/:id : delete the "id" refund.
     *
     * @param id the id of the refund to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/refunds/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefund(@PathVariable Long id) {
        log.debug("REST request to delete Refund : {}", id);
        refundService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("refund", id.toString())).build();
    }

}
