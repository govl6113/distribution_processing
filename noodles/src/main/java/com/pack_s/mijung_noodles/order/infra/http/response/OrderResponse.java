package com.pack_s.mijung_noodles.order.infra.http.response;

import com.pack_s.mijung_noodles.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponse {

    Long id;
    String createdAt;
    String updatedAt;
    String customerName;
    String customerAddress;
    Long totalPrice;

    @Builder
    public OrderResponse(Order order) {
        this.id = order.getId();
        this.createdAt = order.getCreatedAt().toString();
        this.updatedAt = order.getUpdatedAt().toString();
        this.customerName = order.getCustomerName();
        this.customerAddress = order.getCustomerAddress();
        this.totalPrice = order.getTotalPrice();
    }
}
