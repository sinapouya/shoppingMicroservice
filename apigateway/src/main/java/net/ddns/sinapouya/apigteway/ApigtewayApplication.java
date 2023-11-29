package net.ddns.sinapouya.apigteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class ApigtewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigtewayApplication.class, args);
	}

}
