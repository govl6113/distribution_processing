package com.pack_s.mijung_noodles.order.application;

import com.pack_s.mijung_noodles.order.domain.Order;
import com.pack_s.mijung_noodles.order.exception.NotFoundOrderException;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderCreateRequest;
import com.pack_s.mijung_noodles.order.infra.http.request.OrderUpdateRequest;
import com.pack_s.mijung_noodles.order.infra.persistence.OrderRepositoryImpl;
import com.pack_s.mijung_noodles.redis.application.RedisPubServiceImpl;
import com.pack_s.mijung_noodles.redis.dto.Message;
import com.pack_s.mijung_noodles.redis.dto.MessageRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;
    private final RedisPubServiceImpl redisPubService;

    @Value("${metadata.service-id}")
    private String serviceId;

    @Value("${metadata.replica-id}")
    private String replicaId;

    @Override
    @Transactional
    public Order create(OrderCreateRequest request) {
        Order order = orderRepository.save(
            Order.builder()
                .customerName(request.getCustomerName())
                .customerAddress(request.getCustomerAddress())
                .totalPrice(request.getTotalPrice())
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
                        .key(order.getId())
                        .data(order)
                        .build()
                )
                .build()
        );
        return order;
    }

    @Override
    @Transactional
    public Order update(Long orderId, OrderUpdateRequest request) {
        Order order = orderRepository.getById(orderId).orElseThrow(NotFoundOrderException::new);
        Order updatedOrder = order.update(
            request.getCustomerName(),
            request.getCustomerAddress(),
            request.getTotalPrice()
        );
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("PUT")
                .request(
                    MessageRequest.builder()
                        .key(order.getId())
                        .data(order)
                        .build()
                )
                .build()
        );
        return updatedOrder;
    }

    @Override
    public Order get(Long orderId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("GET")
                .request(
                    MessageRequest.builder()
                        .key(orderId)
                        .data(null)
                        .build()
                )
                .build()
        );
        return orderRepository.getById(orderId).orElseThrow(NotFoundOrderException::new);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll();
    }

    @Override
    public Boolean delete(Long orderId) {
        redisPubService.publishMessage(
            Message.builder()
                .time(LocalDateTime.now())
                .serviceId(serviceId)
                .replicaId(replicaId)
                .method("DELETE")
                .request(
                    MessageRequest.builder()
                        .key(orderId)
                        .data(null)
                        .build()
                )
                .build()
        );
        
        orderRepository.deleteById(orderId);
        return true;
    }
}
