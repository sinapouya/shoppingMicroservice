package net.ddns.sinapouya.inventoryService.service;

import lombok.RequiredArgsConstructor;
import net.ddns.sinapouya.inventoryService.dto.InventoryDto;
import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.exception.NotExistInInventoryException;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import net.ddns.sinapouya.inventoryService.util.InventoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<InventoryDto> isInStock(List<String> skuCodes) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes).orElseThrow(
                () -> new NotExistInInventoryException("Cannot Find Product by sku codes  = " + skuCodes)
        );
        Set<String> foundSkuCodes = inventoryList.stream()
                .map(item->item.getSkuCode()).collect(Collectors.toSet());

        List<String> invalidSkuCodes = skuCodes.stream().
                filter(skuCode-> !foundSkuCodes.contains(skuCode))
                .toList();
        if (!invalidSkuCodes.isEmpty()) {
            throw new NotExistInInventoryException("Cannot Find Product by sku codes = " + invalidSkuCodes);
        }

        return inventoryList.stream().map(inventory -> {
            InventoryDto inventoryDto = InventoryDto.builder()
                    .id(inventory.getId())
                    .stock(inventory.getStock())
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getStock() > 0)
                    .build();
            return inventoryDto;
        }).toList();
    }
    public List<InventoryDto> findAllInStock() {
        return inventoryRepository.findAll().stream().map(InventoryMapper::toDto).toList();

    }

}
