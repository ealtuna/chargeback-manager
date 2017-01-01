package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.*;
import com.altuna.challenge.service.dto.ShippingDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Shipping and its DTO ShippingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShippingMapper {

    ShippingDTO shippingToShippingDTO(Shipping shipping);

    List<ShippingDTO> shippingsToShippingDTOs(List<Shipping> shippings);

    Shipping shippingDTOToShipping(ShippingDTO shippingDTO);

    List<Shipping> shippingDTOsToShippings(List<ShippingDTO> shippingDTOs);
}
