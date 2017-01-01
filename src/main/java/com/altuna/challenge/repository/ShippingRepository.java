package com.altuna.challenge.repository;

import com.altuna.challenge.domain.Shipping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Shipping entity.
 */
@SuppressWarnings("unused")
public interface ShippingRepository extends JpaRepository<Shipping,Long> {

}
