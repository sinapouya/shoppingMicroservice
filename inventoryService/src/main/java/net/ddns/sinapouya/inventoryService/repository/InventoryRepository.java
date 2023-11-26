package net.ddns.sinapouya.inventoryService.repository;

import net.ddns.sinapouya.inventoryService.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
    Optional<List<Inventory>> findBySkuCodeIn(List<String> skuCodes);

}
