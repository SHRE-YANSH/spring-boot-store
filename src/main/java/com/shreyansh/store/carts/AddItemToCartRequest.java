package com.shreyansh.store.carts;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {

    @NotNull(message = "Product Id cannot be null")
    private Long productId;

}
