package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.*;
import com.altuna.challenge.service.dto.DisputeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Dispute and its DTO DisputeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DisputeMapper {

    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "shipping.id", target = "shippingId")
    DisputeDTO disputeToDisputeDTO(Dispute dispute);

    List<DisputeDTO> disputesToDisputeDTOs(List<Dispute> disputes);

    @Mapping(source = "transactionId", target = "transaction")
    @Mapping(source = "shippingId", target = "shipping")
    Dispute disputeDTOToDispute(DisputeDTO disputeDTO);

    List<Dispute> disputeDTOsToDisputes(List<DisputeDTO> disputeDTOs);

    default Transaction transactionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }

    default Shipping shippingFromId(Long id) {
        if (id == null) {
            return null;
        }
        Shipping shipping = new Shipping();
        shipping.setId(id);
        return shipping;
    }
}
