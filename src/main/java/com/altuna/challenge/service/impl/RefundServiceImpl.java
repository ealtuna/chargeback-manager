package com.altuna.challenge.service.impl;

import com.altuna.challenge.service.RefundService;
import com.altuna.challenge.domain.Refund;
import com.altuna.challenge.repository.RefundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Refund.
 */
@Service
@Transactional
public class RefundServiceImpl implements RefundService{

    private final Logger log = LoggerFactory.getLogger(RefundServiceImpl.class);
    
    @Inject
    private RefundRepository refundRepository;

    /**
     * Save a refund.
     *
     * @param refund the entity to save
     * @return the persisted entity
     */
    public Refund save(Refund refund) {
        log.debug("Request to save Refund : {}", refund);
        Refund result = refundRepository.save(refund);
        return result;
    }

    /**
     *  Get all the refunds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Refund> findAll(Pageable pageable) {
        log.debug("Request to get all Refunds");
        Page<Refund> result = refundRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one refund by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Refund findOne(Long id) {
        log.debug("Request to get Refund : {}", id);
        Refund refund = refundRepository.findOne(id);
        return refund;
    }

    /**
     *  Delete the  refund by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Refund : {}", id);
        refundRepository.delete(id);
    }
}
