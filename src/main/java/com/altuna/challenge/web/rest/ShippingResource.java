package com.altuna.challenge.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.service.ShippingService;
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
 * REST controller for managing Shipping.
 */
@RestController
@RequestMapping("/api")
public class ShippingResource {

    private final Logger log = LoggerFactory.getLogger(ShippingResource.class);
        
    @Inject
    private ShippingService shippingService;

    /**
     * POST  /shippings : Create a new shipping.
     *
     * @param shipping the shipping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shipping, or with status 400 (Bad Request) if the shipping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shippings")
    @Timed
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) throws URISyntaxException {
        log.debug("REST request to save Shipping : {}", shipping);
        if (shipping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("shipping", "idexists", "A new shipping cannot already have an ID")).body(null);
        }
        Shipping result = shippingService.save(shipping);
        return ResponseEntity.created(new URI("/api/shippings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("shipping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shippings : Updates an existing shipping.
     *
     * @param shipping the shipping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shipping,
     * or with status 400 (Bad Request) if the shipping is not valid,
     * or with status 500 (Internal Server Error) if the shipping couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shippings")
    @Timed
    public ResponseEntity<Shipping> updateShipping(@RequestBody Shipping shipping) throws URISyntaxException {
        log.debug("REST request to update Shipping : {}", shipping);
        if (shipping.getId() == null) {
            return createShipping(shipping);
        }
        Shipping result = shippingService.save(shipping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("shipping", shipping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shippings : get all the shippings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of shippings in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/shippings")
    @Timed
    public ResponseEntity<List<Shipping>> getAllShippings(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Shippings");
        Page<Shipping> page = shippingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/shippings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /shippings/:id : get the "id" shipping.
     *
     * @param id the id of the shipping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shipping, or with status 404 (Not Found)
     */
    @GetMapping("/shippings/{id}")
    @Timed
    public ResponseEntity<Shipping> getShipping(@PathVariable Long id) {
        log.debug("REST request to get Shipping : {}", id);
        Shipping shipping = shippingService.findOne(id);
        return Optional.ofNullable(shipping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /shippings/:id : delete the "id" shipping.
     *
     * @param id the id of the shipping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shippings/{id}")
    @Timed
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        log.debug("REST request to delete Shipping : {}", id);
        shippingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("shipping", id.toString())).build();
    }

}
