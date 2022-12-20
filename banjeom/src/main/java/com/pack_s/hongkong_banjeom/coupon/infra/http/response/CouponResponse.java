package com.pack_s.hongkong_banjeom.coupon.infra.http.response;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CouponResponse {

    Long id;
    String createdAt;
    String updatedAt;
    String customerName;
    String customerPhone;

    @Builder
    public CouponResponse(Coupon coupon) {
        this.id = coupon.getId();
        this.createdAt = coupon.getCreatedAt().toString();
        this.updatedAt = coupon.getUpdatedAt().toString();
        this.customerName = coupon.getCustomerName();
        this.customerPhone = coupon.getCustomerPhone();
    }
}

