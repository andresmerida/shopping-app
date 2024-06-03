package org.adk.shopping.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.adk.shopping.order.dto.OrderDTO;
import org.adk.shopping.order.dto.OrderRequest;
import org.adk.shopping.order.repository.OrderRepository;
import org.adk.shopping.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO placeOrder(OrderRequest orderRequest) {
        return OrderService.toDTO(orderRepository.save(OrderService.toEntity(orderRequest)));
    }
}
