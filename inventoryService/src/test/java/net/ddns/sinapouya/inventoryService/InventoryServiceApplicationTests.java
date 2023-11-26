package net.ddns.sinapouya.inventoryService;

import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:5.7")
			.withDatabaseName("testdb")
			.withUsername("testuser")
			.withPassword("testpass");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
	}

	@BeforeEach
	void insertSampleInventoryRecord() {
		inventoryRepository.saveAll(getInventories());
	}
	@AfterEach
	void tearDown() {
		inventoryRepository.deleteAll();
	}

	@Test
	void isInStock() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/SKU123"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info.skuCode").value("SKU123"))
				.andExpect(jsonPath("$.info.isInStock").value(true));
		;
	}
	@Test
	void isInStockInvalidSkuCode() throws Exception {

		String invalidSkuCode = "INVALIDSKU";

		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory/" + invalidSkuCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value(0));
	}
	@Test
	void isInStockMultipleHasInvalidSKUCodes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory")
				.param("skuCodes", "SKU123", "INVALIDSKU")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value(0))
				.andExpect(jsonPath("$.resultMessage").value("Error: Cannot Find Product by sku codes = [INVALIDSKU]"));
	}
	@Test
	void isInStockMultipleSkuCodes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory")
				.param("skuCodes", "SKU123","SKU456")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info").isArray())
				.andExpect(jsonPath("$.info[0].skuCode").value("SKU123"))
				.andExpect(jsonPath("$.info[0].isInStock").value(true))
				.andExpect(jsonPath("$.info[1].skuCode").value("SKU456"))
				.andExpect(jsonPath("$.info[1].isInStock").value(false));
	}

	private List<Inventory> getInventories() {
		Inventory inventory1 = Inventory.builder()
				.skuCode("SKU123")
				.stock(2)
				.build();
		Inventory inventory2 = Inventory.builder()
				.skuCode("SKU456")
				.stock(0)
				.build();

		return List.of(inventory1,inventory2);
	}

}
