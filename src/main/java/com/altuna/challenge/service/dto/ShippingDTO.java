package com.altuna.challenge.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

import com.altuna.challenge.domain.enumeration.OrderStatus;
import com.altuna.challenge.domain.enumeration.ShippingAgency;

/**
 * A DTO for the Shipping entity.
 */
public class ShippingDTO implements Serializable {

    private Long id;

    private String customerId;

    private String ipAddress;

    private String productId;

    private String email;

    private String orderId;

    private String productInCampaign;

    private Float orderAmount;

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    private ShippingAgency shippingAgency;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProductInCampaign() {
        return productInCampaign;
    }

    public void setProductInCampaign(String productInCampaign) {
        this.productInCampaign = productInCampaign;
    }
    public Float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public ShippingAgency getShippingAgency() {
        return shippingAgency;
    }

    public void setShippingAgency(ShippingAgency shippingAgency) {
        this.shippingAgency = shippingAgency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShippingDTO shippingDTO = (ShippingDTO) o;

        if ( ! Objects.equals(id, shippingDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ShippingDTO{" +
            "id=" + id +
            ", customerId='" + customerId + "'" +
            ", ipAddress='" + ipAddress + "'" +
            ", productId='" + productId + "'" +
            ", email='" + email + "'" +
            ", orderId='" + orderId + "'" +
            ", productInCampaign='" + productInCampaign + "'" +
            ", orderAmount='" + orderAmount + "'" +
            ", orderDate='" + orderDate + "'" +
            ", orderStatus='" + orderStatus + "'" +
            ", shippingAgency='" + shippingAgency + "'" +
            '}';
    }
}
