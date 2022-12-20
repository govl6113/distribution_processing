package com.pack_s.hongkong_banjeom.coupon.infra.persistence;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {

	Coupon findByCustomerName(String customerName);
}
