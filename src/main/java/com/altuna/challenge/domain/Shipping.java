package com.altuna.challenge.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.altuna.challenge.domain.enumeration.OrderStatus;

import com.altuna.challenge.domain.enumeration.ShippingAgency;

/**
 * A Shipping.
 */
@Entity
@Table(name = "shipping")
public class Shipping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "email")
    private String email;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_in_campaign")
    private String productInCampaign;

    @Column(name = "order_amount")
    private Float orderAmount;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_agency")
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

    public Shipping customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Shipping ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getProductId() {
        return productId;
    }

    public Shipping productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEmail() {
        return email;
    }

    public Shipping email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public Shipping orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductInCampaign() {
        return productInCampaign;
    }

    public Shipping productInCampaign(String productInCampaign) {
        this.productInCampaign = productInCampaign;
        return this;
    }

    public void setProductInCampaign(String productInCampaign) {
        this.productInCampaign = productInCampaign;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public Shipping orderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Shipping orderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Shipping orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShippingAgency getShippingAgency() {
        return shippingAgency;
    }

    public Shipping shippingAgency(ShippingAgency shippingAgency) {
        this.shippingAgency = shippingAgency;
        return this;
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
        Shipping shipping = (Shipping) o;
        if (shipping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, shipping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Shipping{" +
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
