package com.shreyansh.store.carts;

import com.shreyansh.store.products.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Data
public class CartProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}