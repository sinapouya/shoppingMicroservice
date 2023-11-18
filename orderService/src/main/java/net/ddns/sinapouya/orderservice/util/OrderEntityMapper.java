package net.ddns.sinapouya.orderservice.util;

import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.model.OrderEntity;

public class OrderEntityMapper {

    public static OrderDto toOrderDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .id(orderEntity.getId())
                .orderItemDtos(orderEntity.getOrderLineItemList().stream().map(OrderLineItemMapper::toOrderLineItemDto).toList())
                .build();
    }
}
