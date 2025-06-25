package com.shreyansh.store.carts;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull
    @Min(value = 1, message = "Quantity Should be 1 or more")
    @Max(value = 100, message = "Quantity Should not be greater than 100")
    private Integer quantity;
}
