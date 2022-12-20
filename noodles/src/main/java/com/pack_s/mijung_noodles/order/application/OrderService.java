package com.pack_s.mijung_noodles.order.application;

import com.pack_s.mijung_noodles.order.domain.Order;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderCreateRequest;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
	@Transactional
	Order create(OrderCreateRequest request);

	@Transactional
	Order update(Long orderId, OrderUpdateRequest request);

	Order get(Long orderId);

	List<Order> getList();

	Boolean delete(Long orderId);
}
