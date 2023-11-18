package net.ddns.sinapouya.orderservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.dto.OrderItemDto;
import net.ddns.sinapouya.orderservice.repository.OrderEntityRepository;
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

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class OrderEntityServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderEntityRepository orderEntityRepository;

	@Autowired
	private ObjectMapper objectMapper;

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
	}

	@BeforeEach
	void init() {
		mySQLContainer.start();
	}


	@AfterEach
	void tearDown() {
		mySQLContainer.stop();
	}
	@Test
	void placeOrder() throws Exception {
		OrderDto orderDto = getOrderDto();
		String content = objectMapper.writeValueAsString(orderDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.info.orderItemDtos", hasSize(1)))
				.andExpect(jsonPath("$.info.orderItemDtos[0].skuCode").value(orderDto.getOrderItemDtos().get(0).getSkuCode()))
				.andExpect(jsonPath("$.info.orderItemDtos[0].price").value(orderDto.getOrderItemDtos().get(0).getPrice()))
				.andExpect(jsonPath("$.info.orderItemDtos[0].quantity").value(orderDto.getOrderItemDtos().get(0).getQuantity()));
	}
	private OrderDto getOrderDto() {
		OrderItemDto orderLineItem = OrderItemDto.builder()
				.skuCode("SKU123")
				.price(new BigDecimal(100))
				.quantity(2)
				.build();

		return OrderDto.builder()
				.orderItemDtos(List.of(orderLineItem))
				.build();
	}
}
