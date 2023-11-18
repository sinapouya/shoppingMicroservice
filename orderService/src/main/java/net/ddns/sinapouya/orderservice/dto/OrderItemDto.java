package net.ddns.sinapouya.orderservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    @NotEmpty(message = "skuCode cannot be empty")
    @NotNull(message = "skuCode cannot be null")
    private String skuCode;

    @NotEmpty(message = "price cannot be empty")
    @NotNull(message = "price cannot be null")
    private BigDecimal price;

    @NotNull(message = "quantity cannot be null")
    @DecimalMin(value = "0", message = "price must be greater than to 0")
    private Integer quantity;
}
