package org.adk.shopping.order.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
    @GetExchange("/api/inventories/{skuCode}/{quantity}/available")
    boolean isInStock(@PathVariable String skuCode, @PathVariable Integer quantity);
}
