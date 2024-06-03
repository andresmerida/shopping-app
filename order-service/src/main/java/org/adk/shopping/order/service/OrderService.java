package org.adk.shopping.order.service;

import org.adk.shopping.order.domain.Order;
import org.adk.shopping.order.dto.OrderDTO;
import org.adk.shopping.order.dto.OrderRequest;

import java.util.UUID;

public interface OrderService {
    OrderDTO placeOrder(OrderRequest orderRequest);

    static Order toEntity(OrderRequest orderRequest) {
        return new Order(
                UUID.randomUUID().toString(),
                orderRequest.skuCode(),
                orderRequest.price(),
                orderRequest.quantity()
        );
    }

    static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        );
    }
}
