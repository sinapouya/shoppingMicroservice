package net.ddns.sinapouya.inventoryService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ddns.sinapouya.inventoryService.dto.InventoryDto;
import net.ddns.sinapouya.inventoryService.dto.Response;
import net.ddns.sinapouya.inventoryService.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    ResponseEntity<Response<InventoryDto>> isInStock(@PathVariable String skuCode) {
        InventoryDto inventoryDto = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(new Response<>(inventoryDto));
    }
    @GetMapping()
    ResponseEntity<Response<List<InventoryDto>>> isInStock(@RequestParam List<String> skuCodes) {
        List<InventoryDto> inventoryDtos = inventoryService.isInStock(skuCodes);
        return ResponseEntity.ok(new Response<>(inventoryDtos));
    }
    @GetMapping("/getAll")
    ResponseEntity<Response<List<InventoryDto>>> getAllStock() {
        List<InventoryDto> inventoryDtos = inventoryService.findAllInStock();
        return ResponseEntity.ok(new Response<>(inventoryDtos));
    }


}
