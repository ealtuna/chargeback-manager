package com.altuna.challenge.repository;

import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.domain.enumeration.DisputeStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Dispute entity.
 */
@SuppressWarnings("unused")
public interface DisputeRepository extends JpaRepository<Dispute,Long> {
	Page<Dispute> findByStatus(Pageable pageable, DisputeStatus status);
	Page<Dispute> findByStatusIn(Pageable pageable, List<DisputeStatus> statuses);
}
