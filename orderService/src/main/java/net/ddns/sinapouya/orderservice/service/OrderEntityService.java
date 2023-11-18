package net.ddns.sinapouya.orderservice.service;

import lombok.RequiredArgsConstructor;
import net.ddns.sinapouya.orderservice.model.OrderEntity;
import net.ddns.sinapouya.orderservice.repository.OrderEntityRepository;
import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.util.OrderLineItemMapper;
import net.ddns.sinapouya.orderservice.util.OrderEntityMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderEntityService {

    private final OrderEntityRepository orderEntityRepository;

    public OrderDto PlaceService(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(UUID.randomUUID().toString());
        orderEntity.setOrderLineItemList(orderDto.getOrderItemDtos()
                .stream().map(OrderLineItemMapper::toOrderLineItem).toList());
        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);
        return OrderEntityMapper.toOrderDto(savedOrderEntity);
    }
}
