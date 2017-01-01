package com.altuna.challenge.service;

import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.domain.enumeration.DisputeStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Dispute.
 */
public interface DisputeService {

    /**
     * Save a dispute.
     *
     * @param dispute the entity to save
     * @return the persisted entity
     */
    Dispute save(Dispute dispute);

    /**
     *  Get all the disputes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Dispute> findAll(Pageable pageable);
    
    Page<Dispute> findByState(Pageable pageable, DisputeStatus status);
    
    Page<Dispute> findByStateIn(Pageable pageable, List<DisputeStatus> statuses);

    /**
     *  Get the "id" dispute.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Dispute findOne(Long id);

    /**
     *  Delete the "id" dispute.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
