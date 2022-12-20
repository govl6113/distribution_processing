package com.pack_s.hongkong_banjeom.coupon.application;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponCreateRequest;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponUpdateRequest;
import java.util.List;

public interface CouponService {

    Coupon create(CouponCreateRequest request);

    Coupon get(Long couponId);

    List<Coupon> getList();

    Coupon update(Long couponId, CouponUpdateRequest request);

    Coupon addSeal(Long couponId);

    Boolean delete(Long couponId);
}
