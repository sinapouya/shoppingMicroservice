package net.ddns.sinapouya.inventoryService;

import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1= Inventory.builder()
					.skuCode("SKU 1")
					.stock(100)
					.build();
			Inventory inventory2= Inventory.builder()
					.skuCode("SKU 2")
					.stock(0)
					.build();
			inventoryRepository.saveAll(List.of(inventory1,inventory2));
		};
	}
}