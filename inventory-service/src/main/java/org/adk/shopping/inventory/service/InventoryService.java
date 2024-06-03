package org.adk.shopping.inventory.service;


import org.adk.shopping.inventory.domain.Inventory;
import org.adk.shopping.inventory.dto.InventoryDTO;
import org.adk.shopping.inventory.dto.InventoryRequest;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
    boolean isInStock(String skuCode, Integer quantity);

    static Inventory toEntity(InventoryRequest inventoryRequest) {
        // need to be implement when it is necessary
        return new Inventory();
    }

    static InventoryDTO toDto(Inventory inventory) {
        return new InventoryDTO(
                inventory.getId(),
                inventory.getSkuCode(),
                inventory.getQuantity()
        );
    }
}
