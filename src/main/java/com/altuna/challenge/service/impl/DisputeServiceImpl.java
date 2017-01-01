package com.altuna.challenge.service.impl;

import com.altuna.challenge.service.DisputeService;
import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.domain.enumeration.DisputeStatus;
import com.altuna.challenge.repository.DisputeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Dispute.
 */
@Service
@Transactional
public class DisputeServiceImpl implements DisputeService{

    private final Logger log = LoggerFactory.getLogger(DisputeServiceImpl.class);
    
    @Inject
    private DisputeRepository disputeRepository;

    /**
     * Save a dispute.
     *
     * @param dispute the entity to save
     * @return the persisted entity
     */
    public Dispute save(Dispute dispute) {
        log.debug("Request to save Dispute : {}", dispute);
        Dispute result = disputeRepository.save(dispute);
        return result;
    }

    /**
     *  Get all the disputes.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Dispute> findAll(Pageable pageable) {
        log.debug("Request to get all Disputes");
        Page<Dispute> result = disputeRepository.findAll(pageable);
        return result;
    }
    
    @Transactional(readOnly = true) 
    public Page<Dispute> findByState(Pageable pageable, DisputeStatus status) {
        Page<Dispute> result = disputeRepository.findByStatus(pageable, status);
        return result;
    }
    
    @Transactional(readOnly = true) 
    public Page<Dispute> findByStateIn(Pageable pageable, List<DisputeStatus> statuses) {
        Page<Dispute> result = disputeRepository.findByStatusIn(pageable, statuses);
        return result;
    }

    /**
     *  Get one dispute by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Dispute findOne(Long id) {
        log.debug("Request to get Dispute : {}", id);
        Dispute dispute = disputeRepository.findOne(id);
        return dispute;
    }

    /**
     *  Delete the  dispute by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Dispute : {}", id);
        disputeRepository.delete(id);
    }
}
