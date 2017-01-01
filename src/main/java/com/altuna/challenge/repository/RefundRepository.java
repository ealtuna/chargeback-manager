package com.altuna.challenge.repository;

import com.altuna.challenge.domain.Refund;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Refund entity.
 */
@SuppressWarnings("unused")
public interface RefundRepository extends JpaRepository<Refund,Long> {

}
