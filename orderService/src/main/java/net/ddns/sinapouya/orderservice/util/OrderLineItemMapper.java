package net.ddns.sinapouya.orderservice.util;

import net.ddns.sinapouya.orderservice.dto.OrderItemDto;
import net.ddns.sinapouya.orderservice.model.OrderLineItem;

public class OrderLineItemMapper {

    public static OrderLineItem toOrderLineItem(OrderItemDto orderItemDto) {
        return OrderLineItem.builder()
                .price(orderItemDto.getPrice())
                .quantity(orderItemDto.getQuantity())
                .skuCode(orderItemDto.getSkuCode()).build();
    }
    public static OrderItemDto toOrderLineItemDto(OrderLineItem orderLineItem) {
        return OrderItemDto.builder()
                .id(orderLineItem.getId())
                .price(orderLineItem.getPrice())
                .quantity(orderLineItem.getQuantity())
                .skuCode(orderLineItem.getSkuCode()).build();
    }
}
