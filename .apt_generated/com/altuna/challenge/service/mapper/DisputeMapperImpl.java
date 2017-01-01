package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.Dispute;
import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.domain.Transaction;
import com.altuna.challenge.domain.enumeration.DisputeStatus;
import com.altuna.challenge.service.dto.DisputeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-01-01T22:18:45+0100",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.2.v20161117-1814, environment: Java 1.8.0_20 (Oracle Corporation)"
)
@Component
public class DisputeMapperImpl implements DisputeMapper {

    @Override
    public DisputeDTO disputeToDisputeDTO(Dispute dispute) {
        if ( dispute == null ) {
            return null;
        }

        DisputeDTO disputeDTO = new DisputeDTO();

        disputeDTO.setTransactionId( disputeTransactionId( dispute ) );
        disputeDTO.setShippingId( disputeShippingId( dispute ) );
        disputeDTO.setAmount( dispute.getAmount() );
        disputeDTO.setCardType( dispute.getCardType() );
        disputeDTO.setCaseNumber( dispute.getCaseNumber() );
        disputeDTO.setDisputeType( dispute.getDisputeType() );
        disputeDTO.setFiledDay( dispute.getFiledDay() );
        disputeDTO.setId( dispute.getId() );
        disputeDTO.setReasonCode( dispute.getReasonCode() );
        if ( dispute.getStatus() != null ) {
            disputeDTO.setStatus( dispute.getStatus().name() );
        }

        return disputeDTO;
    }

    @Override
    public List<DisputeDTO> disputesToDisputeDTOs(List<Dispute> disputes) {
        if ( disputes == null ) {
            return null;
        }

        List<DisputeDTO> list = new ArrayList<DisputeDTO>();
        for ( Dispute dispute : disputes ) {
            list.add( disputeToDisputeDTO( dispute ) );
        }

        return list;
    }

    @Override
    public Dispute disputeDTOToDispute(DisputeDTO disputeDTO) {
        if ( disputeDTO == null ) {
            return null;
        }

        Dispute dispute = new Dispute();

        dispute.setShipping( shippingFromId( disputeDTO.getShippingId() ) );
        dispute.setTransaction( transactionFromId( disputeDTO.getTransactionId() ) );
        dispute.setAmount( disputeDTO.getAmount() );
        dispute.setCardType( disputeDTO.getCardType() );
        dispute.setCaseNumber( disputeDTO.getCaseNumber() );
        dispute.setDisputeType( disputeDTO.getDisputeType() );
        dispute.setFiledDay( disputeDTO.getFiledDay() );
        dispute.setId( disputeDTO.getId() );
        dispute.setReasonCode( disputeDTO.getReasonCode() );
        if ( disputeDTO.getStatus() != null ) {
            dispute.setStatus( Enum.valueOf( DisputeStatus.class, disputeDTO.getStatus() ) );
        }

        return dispute;
    }

    @Override
    public List<Dispute> disputeDTOsToDisputes(List<DisputeDTO> disputeDTOs) {
        if ( disputeDTOs == null ) {
            return null;
        }

        List<Dispute> list = new ArrayList<Dispute>();
        for ( DisputeDTO disputeDTO : disputeDTOs ) {
            list.add( disputeDTOToDispute( disputeDTO ) );
        }

        return list;
    }

    private Long disputeTransactionId(Dispute dispute) {

        if ( dispute == null ) {
            return null;
        }
        Transaction transaction = dispute.getTransaction();
        if ( transaction == null ) {
            return null;
        }
        Long id = transaction.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long disputeShippingId(Dispute dispute) {

        if ( dispute == null ) {
            return null;
        }
        Shipping shipping = dispute.getShipping();
        if ( shipping == null ) {
            return null;
        }
        Long id = shipping.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
