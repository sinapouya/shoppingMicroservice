package net.ddns.sinapouya.inventoryService;

import net.ddns.sinapouya.inventoryService.entity.Inventory;
import net.ddns.sinapouya.inventoryService.repository.InventoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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
		inventoryRepository.save(getInventory());
	}
	@AfterEach
	void tearDown() {
		inventoryRepository.deleteAll();
	}

	@Test
	void isInStock() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/SKU123"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info.skuCode").value("SKU123"))
				.andExpect(jsonPath("$.info.isInStock").value(true));
		;
	}
	@Test
	void isInStockInvalidSkuCode() throws Exception {

		String invalidSkuCode = "INVALIDSKU";

		mockMvc.perform(MockMvcRequestBuilders.get("/" + invalidSkuCode))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.resultCode").value(0));
	}
	private Inventory getInventory() {
		Inventory inventory = Inventory.builder()
				.skuCode("SKU123")
				.stock(2)
				.build();

		return inventory;
	}

}
