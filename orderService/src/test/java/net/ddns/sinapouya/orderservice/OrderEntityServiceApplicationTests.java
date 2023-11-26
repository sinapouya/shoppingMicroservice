package net.ddns.sinapouya.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.sinapouya.orderservice.dto.InventoryDto;
import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.dto.OrderItemDto;
import net.ddns.sinapouya.orderservice.dto.Response;
import net.ddns.sinapouya.orderservice.repository.OrderEntityRepository;
import net.ddns.sinapouya.orderservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderEntityServiceApplicationTests {

	public static final String VALID_SKU = "SKU123";
	public static final String INVALID_SKU = "INVALID";
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderEntityRepository orderEntityRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	InventoryService inventoryService;

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
		dynamicPropertyRegistry.add("spring.jpa.hibernate.ddl-auto", ()->"create-drop");
		dynamicPropertyRegistry.add("eureka.client.enabled",()->"false");

	}

	@BeforeEach
	void init() {
		mySQLContainer.start();
	}

	@Test
	void placeOrder() throws Exception {
		OrderDto orderDto = getOrderDto(VALID_SKU,2);
		String content = objectMapper.writeValueAsString(orderDto);
		when(inventoryService.getCodesResponse(List.of(VALID_SKU))).thenReturn(inventoryMockedResponse());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.info.orderItemDtos", hasSize(1)))
				.andExpect(jsonPath("$.info.orderItemDtos[0].skuCode").value(orderDto.getOrderItemDtos().get(0).getSkuCode()))
				.andExpect(jsonPath("$.info.orderItemDtos[0].price").value(orderDto.getOrderItemDtos().get(0).getPrice()))
				.andExpect(jsonPath("$.info.orderItemDtos[0].quantity").value(orderDto.getOrderItemDtos().get(0).getQuantity()));
	}
	@Test
	void placeOrderInvalidSku() throws Exception {
		OrderDto orderDto = getOrderDto(INVALID_SKU,2);
		String content = objectMapper.writeValueAsString(orderDto);
		when(inventoryService.getCodesResponse(List.of(INVALID_SKU))).thenReturn(inventoryMockedResponseWithInvalidSKU());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value(0));
	}
	@Test
	void placeOrderZeroQuantity() throws Exception {
		OrderDto orderDto = getOrderDto(VALID_SKU,0);
		String content = objectMapper.writeValueAsString(orderDto);
		when(inventoryService.getCodesResponse(List.of(VALID_SKU))).thenReturn(inventoryMockedResponseWithZeroQuantity());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.resultCode").value(0));
	}

	private Response<List<InventoryDto>> inventoryMockedResponse() {
		return new Response<>(1, "Success", Collections.singletonList(InventoryDto.builder().skuCode(VALID_SKU).isInStock(true).build()));
	}
	private Response<List<InventoryDto>> inventoryMockedResponseWithInvalidSKU() {
		return new Response<>(0, "Error: Invalid Code",null);
	}
	private Response<List<InventoryDto>> inventoryMockedResponseWithZeroQuantity() {
		return new Response<>(0, "Error: Zero Quantity",null);
	}
	private OrderDto getOrderDto(String skuCode,int quantity) {
		OrderItemDto orderLineItem = OrderItemDto.builder()
				.skuCode(skuCode)
				.price(new BigDecimal(100))
				.quantity(quantity)
				.build();

		return OrderDto.builder()
				.orderItemDtos(List.of(orderLineItem))
				.build();
	}
}
