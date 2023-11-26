package net.ddns.sinapouya.inventoryService.util;

import net.ddns.sinapouya.inventoryService.dto.InventoryDto;
import net.ddns.sinapouya.inventoryService.entity.Inventory;

public class InventoryMapper {
    public static InventoryDto toDto(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .stock(inventory.getStock())
                .isInStock(inventory.getStock()>0).build();
    }
}
