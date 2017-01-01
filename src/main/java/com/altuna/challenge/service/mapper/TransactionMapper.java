package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.*;
import com.altuna.challenge.service.dto.TransactionDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Transaction and its DTO TransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransactionMapper {

    @Mapping(source = "refund.id", target = "refundId")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    List<TransactionDTO> transactionsToTransactionDTOs(List<Transaction> transactions);

    @Mapping(source = "refundId", target = "refund")
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);

    List<Transaction> transactionDTOsToTransactions(List<TransactionDTO> transactionDTOs);

    default Refund refundFromId(Long id) {
        if (id == null) {
            return null;
        }
        Refund refund = new Refund();
        refund.setId(id);
        return refund;
    }
}
