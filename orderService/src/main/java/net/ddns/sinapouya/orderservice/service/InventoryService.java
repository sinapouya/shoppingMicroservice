package net.ddns.sinapouya.orderservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.ddns.sinapouya.orderservice.dto.InventoryDto;
import net.ddns.sinapouya.orderservice.dto.Response;
import net.ddns.sinapouya.orderservice.exception.OrderException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class InventoryService {
    private final WebClient webClient;

    private final LoadBalancerClient loadBalancerClient;

    public  Response<List<InventoryDto>> getCodesResponse(List<String> skuCodes) {

        try{
            ServiceInstance inventoryservice = loadBalancerClient.choose("inventoryservice");
            URI uri = inventoryservice.getUri();
            Response<List<InventoryDto>> response = webClient.get()
                    .uri(uri + "/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Response<List<InventoryDto>>>() {
                    })
                    .blockOptional()
                    .orElseThrow(() -> new OrderException("Failed to retrieve inventory information"));
            return response;
        }catch (Exception e){
            log.error("Failed to retrieve inventory information",e);
            throw new OrderException("Failed to retrieve inventory information");
        }

    }
}
