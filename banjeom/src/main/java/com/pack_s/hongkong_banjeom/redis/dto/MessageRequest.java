package com.pack_s.hongkong_banjeom.redis.dto;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import com.pack_s.hongkong_banjeom.coupon.infra.http.response.CouponResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {

    private Long key;
    private CouponResponse data;

    @Builder
    public MessageRequest(Long key, Coupon data) {
        this.key = key;
        this.data = data != null ? data.toResponse() : null;
    }
}
