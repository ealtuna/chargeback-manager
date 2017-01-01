package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.*;
import com.altuna.challenge.service.dto.RefundDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Refund and its DTO RefundDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RefundMapper {

    RefundDTO refundToRefundDTO(Refund refund);

    List<RefundDTO> refundsToRefundDTOs(List<Refund> refunds);

    @Mapping(target = "transactions", ignore = true)
    Refund refundDTOToRefund(RefundDTO refundDTO);

    List<Refund> refundDTOsToRefunds(List<RefundDTO> refundDTOs);
}
