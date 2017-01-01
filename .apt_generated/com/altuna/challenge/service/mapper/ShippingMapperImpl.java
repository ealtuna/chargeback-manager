package com.altuna.challenge.service.mapper;

import com.altuna.challenge.domain.Shipping;
import com.altuna.challenge.service.dto.ShippingDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-01-01T20:11:31+0100",
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

        shippingDTO.setId( shipping.getId() );
        shippingDTO.setCustomerId( shipping.getCustomerId() );
        shippingDTO.setIpAddress( shipping.getIpAddress() );
        shippingDTO.setProductId( shipping.getProductId() );
        shippingDTO.setEmail( shipping.getEmail() );
        shippingDTO.setOrderId( shipping.getOrderId() );
        shippingDTO.setProductInCampaign( shipping.getProductInCampaign() );
        shippingDTO.setOrderAmount( shipping.getOrderAmount() );
        shippingDTO.setOrderDate( shipping.getOrderDate() );
        shippingDTO.setOrderStatus( shipping.getOrderStatus() );

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

        shipping.setId( shippingDTO.getId() );
        shipping.setCustomerId( shippingDTO.getCustomerId() );
        shipping.setIpAddress( shippingDTO.getIpAddress() );
        shipping.setProductId( shippingDTO.getProductId() );
        shipping.setEmail( shippingDTO.getEmail() );
        shipping.setOrderId( shippingDTO.getOrderId() );
        shipping.setProductInCampaign( shippingDTO.getProductInCampaign() );
        shipping.setOrderAmount( shippingDTO.getOrderAmount() );
        shipping.setOrderDate( shippingDTO.getOrderDate() );
        shipping.setOrderStatus( shippingDTO.getOrderStatus() );

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
