package com.shreyansh.store.products;

public class ProductNotFoundInCartException extends RuntimeException {
    public ProductNotFoundInCartException() {
        super("Product not found in cart");
    }
}
