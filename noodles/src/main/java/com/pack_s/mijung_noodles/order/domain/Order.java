package com.pack_s.mijung_noodles.order.domain;

import com.pack_s.mijung_noodles.order.infra.http.response.OrderResponse;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String customerName;

    @Column(nullable = false)
    String customerAddress;

    @Column(nullable = false)
    Long totalPrice;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    @Builder
    public Order(String customerName, String customerAddress, Long totalPrice) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.totalPrice = totalPrice;
    }

    public Order update(String customerName, String customerAddress, Long totalPrice) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderResponse toResponse() {
        return OrderResponse.builder()
            .order(this)
            .build();
    }
}
