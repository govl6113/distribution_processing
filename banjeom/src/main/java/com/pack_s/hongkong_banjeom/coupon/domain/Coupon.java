package com.pack_s.hongkong_banjeom.coupon.domain;

import com.pack_s.hongkong_banjeom.coupon.infra.http.response.CouponResponse;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Coupon {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    String customerName;

    @Column(nullable = false)
    String customerPhone;

    @Column(nullable = false)
    Integer painting = 1;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    @Builder
    public Coupon(String customerName, String customerPhone) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public Coupon update(String customerName, String customerPhone) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        return this;
    }

    public Coupon addSeal() {
        this.painting += 1;
        return this;
    }

    public CouponResponse toResponse() {
        return CouponResponse.builder()
            .coupon(this)
            .build();
    }
}
