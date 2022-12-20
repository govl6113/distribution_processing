package com.pack_s.mijung_noodles.order.infra.persistence;

import com.pack_s.mijung_noodles.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
