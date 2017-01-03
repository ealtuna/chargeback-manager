package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.service.dto.ShippingDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-01-03T13:40:10+0100",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.12.2.v20161117-1814, environment: Java 1.8.0_20 (Oracle Corporation)"
)
@Component
public class ShippingMapperImpl implements ShippingMapper {

    @Override
    public ShippingDTO shippingToShippingDTO(Shipping shipping) {
        if ( shipping == null ) {
            return null;
        }

        ShippingDTO shippingDTO = new ShippingDTO();

        shippingDTO.setCustomerId( shipping.getCustomerId() );
        shippingDTO.setEmail( shipping.getEmail() );
        shippingDTO.setId( shipping.getId() );
        shippingDTO.setIpAddress( shipping.getIpAddress() );
        shippingDTO.setOrderAmount( shipping.getOrderAmount() );
        shippingDTO.setOrderDate( shipping.getOrderDate() );
        shippingDTO.setOrderId( shipping.getOrderId() );
        shippingDTO.setOrderStatus( shipping.getOrderStatus() );
        shippingDTO.setProductId( shipping.getProductId() );
        shippingDTO.setProductInCampaign( shipping.getProductInCampaign() );
        shippingDTO.setShippingAgency( shipping.getShippingAgency() );

        return shippingDTO;
    }

    @Override
    public List<ShippingDTO> shippingsToShippingDTOs(List<Shipping> shippings) {
        if ( shippings == null ) {
            return null;
        }

        List<ShippingDTO> list = new ArrayList<ShippingDTO>();
        for ( Shipping shipping : shippings ) {
            list.add( shippingToShippingDTO( shipping ) );
        }

        return list;
    }

    @Override
    public Shipping shippingDTOToShipping(ShippingDTO shippingDTO) {
        if ( shippingDTO == null ) {
            return null;
        }

        Shipping shipping = new Shipping();

        shipping.setCustomerId( shippingDTO.getCustomerId() );
        shipping.setEmail( shippingDTO.getEmail() );
        shipping.setId( shippingDTO.getId() );
        shipping.setIpAddress( shippingDTO.getIpAddress() );
        shipping.setOrderAmount( shippingDTO.getOrderAmount() );
        shipping.setOrderDate( shippingDTO.getOrderDate() );
        shipping.setOrderId( shippingDTO.getOrderId() );
        shipping.setOrderStatus( shippingDTO.getOrderStatus() );
        shipping.setProductId( shippingDTO.getProductId() );
        shipping.setProductInCampaign( shippingDTO.getProductInCampaign() );
        shipping.setShippingAgency( shippingDTO.getShippingAgency() );

        return shipping;
    }

    @Override
    public List<Shipping> shippingDTOsToShippings(List<ShippingDTO> shippingDTOs) {
        if ( shippingDTOs == null ) {
            return null;
        }

        List<Shipping> list = new ArrayList<Shipping>();
        for ( ShippingDTO shippingDTO : shippingDTOs ) {
            list.add( shippingDTOToShipping( shippingDTO ) );
        }

        return list;
    }
}
