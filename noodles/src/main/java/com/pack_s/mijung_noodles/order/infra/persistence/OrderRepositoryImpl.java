package com.pack_s.mijung_noodles.order.infra.persistence;

import com.pack_s.mijung_noodles.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository{

	private final OrderJpaRepository orderJpaRepository;

	@Override
	public Order save(Order menu) {
		return orderJpaRepository.save(menu);
	}

	@Override
	public Optional<Order> getById(Long menuId) {
		return orderJpaRepository.findById(menuId);
	}

	@Override
	public List<Order> findAll() {
		return orderJpaRepository.findAll();
	}

	@Override
	public void deleteById(Long menuId) {
		orderJpaRepository.deleteById(menuId);
	}
}
