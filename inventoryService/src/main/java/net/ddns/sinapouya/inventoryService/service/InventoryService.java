package net.ddns.sinapouya.inventoryService.service;

import lombok.RequiredArgsConstructor;
import net.ddns.sinapouya.inventoryService.dto.InventoryDto;
import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.exception.NotExistInInventoryException;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public InventoryDto isInStock(String skuCode){
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(
                () -> new NotExistInInventoryException("Cannot Find Product by sku code  = " + skuCode)
        );
        InventoryDto inventoryDto = InventoryDto.builder()
                .id(inventory.getId())
                .stock(inventory.getStock())
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getStock() > 0)
                .build();
        return inventoryDto;
    }
}
