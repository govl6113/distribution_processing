package com.pack_s.hongkong_banjeom.coupon.infra.persistence;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import java.util.List;
import java.util.Optional;

public interface CouponRepository {

    Coupon save(Coupon coupon);

    Optional<Coupon> getById(Long couponId);

    Coupon getByCustomerName(String customerName);

    List<Coupon> getAll();

    void delete(Long couponId);
}
