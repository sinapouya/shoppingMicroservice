package net.ddns.sinapouya.inventoryService;

import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1= Inventory.builder()
					.skuCode("SKU1")
					.stock(100)
					.build();
			Inventory inventory2= Inventory.builder()
					.skuCode("SKU2")
					.stock(0)
					.build();
			Inventory inventory3= Inventory.builder()
					.skuCode("SKU3")
					.stock(0)
					.build();
			inventoryRepository.saveAll(List.of(inventory1,inventory2,inventory3));
		};
	}
}
