package org.adk.shopping.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.adk.shopping.inventory.dto.InventoryDTO;
import org.adk.shopping.inventory.repository.InventoryRepository;
import org.adk.shopping.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(InventoryService::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
