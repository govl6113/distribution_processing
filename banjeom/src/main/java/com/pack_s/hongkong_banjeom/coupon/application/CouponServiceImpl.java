package com.pack_s.hongkong_banjeom.coupon.application;

import com.pack_s.hongkong_banjeom.coupon.domain.Coupon;
import com.pack_s.hongkong_banjeom.coupon.exception.NotFoundCouponException;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponCreateRequest;
import com.pack_s.hongkong_banjeom.coupon.infra.http.request.CouponUpdateRequest;
import com.pack_s.hongkong_banjeom.coupon.infra.persistence.CouponRepositoryImpl;
import com.pack_s.hongkong_banjeom.redis.application.RedisPubServiceImpl;
import com.pack_s.hongkong_banjeom.redis.dto.Message;
import com.pack_s.hongkong_banjeom.redis.dto.MessageRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepositoryImpl couponRepository;
    private final RedisPubServiceImpl redisPubService;

    @Value("${metadata.service-id}")
    private String serviceId;

    @Value("${metadata.replica-id}")
    private String replicaId;

    @Override
    @Transactional
    public Coupon create(CouponCreateRequest request) {
        if (couponRepository.getByCustomerName(request.getCustomerName()) != null) {
            return couponRepository.getByCustomerName(request.getCustomerName());
        }

        Coupon coupon = couponRepository.save(
            Coupon.builder()
                .customerName(request.getCustomerName())
                .customerPhone(request.getCustomerPhone())
                .build()
        );

        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("POST")
                .request(
                    MessageRequest.builder()
                        .key(coupon.getId())
                        .data(coupon)
                        .build()
                )
                .build()
        );
        return coupon;
    }

    @Override
    public Coupon get(Long couponId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("GET")
                .request(
                    MessageRequest.builder()
                        .key(couponId)
                        .data(null)
                        .build()
                )
                .build()
        );
        return couponRepository.getById(couponId).orElseThrow(NotFoundCouponException::new);
    }

    @Override
    public List<Coupon> getList() {
        return couponRepository.getAll();
    }

    @Override
    @Transactional
    public Coupon update(Long couponId, CouponUpdateRequest request) {
        Coupon coupon = couponRepository.getById(couponId).orElseThrow(NotFoundCouponException::new);
        Coupon updatedCoupon = coupon.update(request.getCustomerName(), request.getCustomerPhone());

        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("PUT")
                .request(
                    MessageRequest.builder()
                        .key(couponId)
                        .data(updatedCoupon)
                        .build()
                )
                .build()
        );
        return updatedCoupon;
    }

    @Override
    @Transactional
    public Coupon addSeal(Long couponId) {
        Coupon coupon = couponRepository.getById(couponId).orElseThrow(NotFoundCouponException::new);
        Coupon updatedCoupon = coupon.addSeal();

        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("PUT")
                .request(
                    MessageRequest.builder()
                        .key(couponId)
                        .data(updatedCoupon)
                        .build()
                )
                .build()
        );
        return updatedCoupon;
    }

    @Override
    @Transactional
    public Boolean delete(Long couponId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("DELETE")
                .request(
                    MessageRequest.builder()
                        .key(couponId)
                        .data(null)
                        .build()
                )
                .build()
        );

        couponRepository.delete(couponId);
        return true;
    }
}
