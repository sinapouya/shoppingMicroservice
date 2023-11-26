package net.ddns.sinapouya.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ddns.sinapouya.orderservice.dto.InventoryDto;
import net.ddns.sinapouya.orderservice.dto.Response;
import net.ddns.sinapouya.orderservice.exception.OrderException;
import net.ddns.sinapouya.orderservice.model.OrderEntity;
import net.ddns.sinapouya.orderservice.model.OrderLineItem;
import net.ddns.sinapouya.orderservice.repository.OrderEntityRepository;
import net.ddns.sinapouya.orderservice.dto.OrderDto;
import net.ddns.sinapouya.orderservice.util.OrderLineItemMapper;
import net.ddns.sinapouya.orderservice.util.OrderEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEntityService {

    private final OrderEntityRepository orderEntityRepository;
    private final InventoryService inventoryService;

    public OrderDto placeService(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderDto.getOrderItemDtos()
                .stream().map(OrderLineItemMapper::toOrderLineItem).toList();
        orderEntity.setOrderLineItemList(orderLineItems);
        List<String> skuCodes = orderLineItems.stream().map(OrderLineItem::getSkuCode).toList();
        Response<List<InventoryDto>> skuCodesResponse = inventoryService.getCodesResponse(skuCodes);
        validateInventoryResponse(skuCodesResponse);
        List<InventoryDto> inventoryDtoList = skuCodesResponse.getInfo();
        validateInventoryAvailability(inventoryDtoList);
        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);
        return OrderEntityMapper.toOrderDto(savedOrderEntity);
    }

    private void validateInventoryAvailability(List<InventoryDto> inventoryDtoList) {
        boolean allInStock = inventoryDtoList.stream().allMatch(InventoryDto::getIsInStock);
        if(!allInStock){

            List<String> nonExistSkucodes = inventoryDtoList.stream()
                    .filter(skuCode -> !skuCode.getIsInStock())
                    .map(InventoryDto::getSkuCode)
                    .toList();
            log.error("order exception these skues are empty in stock "+nonExistSkucodes);
            throw new OrderException("order exception these skues are empty in stock "+nonExistSkucodes);
        }

    }

    private void validateInventoryResponse(Response<List<InventoryDto>> skuCodesResponse) {
        if(skuCodesResponse.getResultCode()!=1){
            throw new OrderException("order exception "+skuCodesResponse.getResultMessage());
        }
    }


}
