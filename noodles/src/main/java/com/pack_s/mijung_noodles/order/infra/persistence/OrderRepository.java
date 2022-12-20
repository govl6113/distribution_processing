package com.pack_s.mijung_noodles.order.infra.persistence;

import com.pack_s.mijung_noodles.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
	Order save(Order menu);

	Optional<Order> getById(Long menuId);

	List<Order> findAll();

	void deleteById(Long menuId);
}
