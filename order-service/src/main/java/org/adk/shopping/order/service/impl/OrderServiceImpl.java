package org.adk.shopping.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.adk.shopping.order.client.InventoryClient;
import org.adk.shopping.order.dto.OrderDTO;
import org.adk.shopping.order.dto.OrderRequest;
import org.adk.shopping.order.repository.OrderRepository;
import org.adk.shopping.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public OrderDTO placeOrder(OrderRequest orderRequest) {
        if (inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {
            return OrderService.toDTO(orderRepository.save(OrderService.toEntity(orderRequest)));
        } else {
            throw new RuntimeException(
                    String.format("Product with SkuCode %s is not in stock", orderRequest.skuCode()));
        }
    }
}
