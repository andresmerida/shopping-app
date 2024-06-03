package org.adk.shopping.inventory.web.rest;

import lombok.RequiredArgsConstructor;
import org.adk.shopping.inventory.dto.InventoryDTO;
import org.adk.shopping.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{skuCode}/{quantity}/available")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isAvailable(@PathVariable String skuCode,
                               @PathVariable Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
