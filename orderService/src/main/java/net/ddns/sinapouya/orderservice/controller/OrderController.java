package net.ddns.sinapouya.orderservice.controller;

import lombok.RequiredArgsConstructor;
import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.dto.Response;
import net.ddns.sinapouya.orderservice.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderEntityService orderEntityService;

    @PostMapping
    public ResponseEntity<Response<OrderDto>> placeOrder(@RequestBody OrderDto orderDto){
        OrderDto orderResponseDto = orderEntityService.PlaceService(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<OrderDto>(orderResponseDto));
    }

}
