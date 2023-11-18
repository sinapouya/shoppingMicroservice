package net.ddns.sinapouya.inventoryService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ddns.sinapouya.inventoryService.dto.InventoryDto;
import net.ddns.sinapouya.inventoryService.dto.Response;
import net.ddns.sinapouya.inventoryService.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    ResponseEntity<Response<InventoryDto>> isInStock(@PathVariable String skuCode) {
        InventoryDto inventoryDto = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(new Response<>(inventoryDto));
    }
}
