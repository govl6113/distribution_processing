package com.pack_s.hongkong_banjeom.coupon.infra.persistence;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        return couponJpaRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> getById(Long couponId) {
        return couponJpaRepository.findById(couponId);
    }

    @Override
    public Coupon getByCustomerName(String customerName) {
        return couponJpaRepository.findByCustomerName(customerName);
    }

    @Override
    public List<Coupon> getAll() {
        return couponJpaRepository.findAll();
    }

    @Override
    public void delete(Long couponId) {
        couponJpaRepository.deleteById(couponId);
    }
}
