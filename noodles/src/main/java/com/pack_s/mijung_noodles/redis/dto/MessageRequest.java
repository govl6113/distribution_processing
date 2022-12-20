package com.pack_s.mijung_noodles.redis.dto;

import com.pack_s.mijung_noodles.order.domain.Order;
import com.pack_s.mijung_noodles.order.infra.http.response.OrderResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {

    private Long key;
    private OrderResponse data;

    @Builder
    public MessageRequest(Long key, Order data) {
        this.key = key;
        this.data = data != null ? data.toResponse() : null;
    }
}
