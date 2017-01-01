package com.altuna.challenge.service;

import com.altuna.challenge.domain.Refund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Refund.
 */
public interface RefundService {

    /**
     * Save a refund.
     *
     * @param refund the entity to save
     * @return the persisted entity
     */
    Refund save(Refund refund);

    /**
     *  Get all the refunds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Refund> findAll(Pageable pageable);

    /**
     *  Get the "id" refund.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Refund findOne(Long id);

    /**
     *  Delete the "id" refund.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
