package net.ddns.sinapouya.userservice;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class UserServiceApplicationTests {

	static final KeycloakContainer keycloak;

	@Autowired
	private MockMvc mockMvc;

	static {
		keycloak = new KeycloakContainer("quay.io/keycloak/keycloak:22.0.3")
				.withRealmImportFile("./keycloak.json");
		keycloak.start();
	}
	@Test
	void generateTokenTest() throws Exception {
		mockMvc.perform(post("/api/user")
				.contentType("application/json")
				.content("{\"clientId\":\"spring-client\"," +
						" \"userName\":\"user1\"," +
						" \"password\":\"pass123\"," +
						" \"grantType\":\"password\"}"))
				.andExpect(status().isOk());
	}



}
