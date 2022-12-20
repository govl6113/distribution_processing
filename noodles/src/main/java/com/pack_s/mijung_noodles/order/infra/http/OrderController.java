package com.pack_s.mijung_noodles.order.infra.http;

import com.pack_s.mijung_noodles.order.application.OrderServiceImpl;
import com.pack_s.mijung_noodles.order.domain.Order;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderCreateRequest;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderUpdateRequest;
import com.pack_s.mijung_noodles.order.infra.http.response.OrderResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

	private final OrderServiceImpl orderService;

	@PostMapping("/new")
	public ResponseEntity<OrderResponse> create(
		@Valid @RequestBody OrderCreateRequest request
	) {
		return ResponseEntity.ok().body(orderService.create(request).toResponse());
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<OrderResponse> update(
		@PathVariable("orderId") Long orderId,
		@Valid @RequestBody OrderUpdateRequest request
	) {
		return ResponseEntity.ok().body(orderService.update(orderId, request).toResponse());
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> get(
		@PathVariable("orderId") Long orderId
	) {
		return ResponseEntity.ok().body(orderService.get(orderId).toResponse());
	}

	@GetMapping("/list")
	public ResponseEntity<List<OrderResponse>> getList() {
		return ResponseEntity.ok().body(
			orderService.getList()
				.stream().map(Order::toResponse)
				.collect(Collectors.toList())
		);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<Boolean> delete(
		@PathVariable("orderId") Long orderId
	) {
		return ResponseEntity.ok().body(orderService.delete(orderId));
	}

}
