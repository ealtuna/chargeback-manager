package com.altuna.challenge.service.impl;

import com.altuna.challenge.service.ShippingService;
import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.repository.ShippingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Shipping.
 */
@Service
@Transactional
public class ShippingServiceImpl implements ShippingService{

    private final Logger log = LoggerFactory.getLogger(ShippingServiceImpl.class);
    
    @Inject
    private ShippingRepository shippingRepository;

    /**
     * Save a shipping.
     *
     * @param shipping the entity to save
     * @return the persisted entity
     */
    public Shipping save(Shipping shipping) {
        log.debug("Request to save Shipping : {}", shipping);
        Shipping result = shippingRepository.save(shipping);
        return result;
    }

    /**
     *  Get all the shippings.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Shipping> findAll(Pageable pageable) {
        log.debug("Request to get all Shippings");
        Page<Shipping> result = shippingRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one shipping by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Shipping findOne(Long id) {
        log.debug("Request to get Shipping : {}", id);
        Shipping shipping = shippingRepository.findOne(id);
        return shipping;
    }

    /**
     *  Delete the  shipping by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Shipping : {}", id);
        shippingRepository.delete(id);
    }
}
