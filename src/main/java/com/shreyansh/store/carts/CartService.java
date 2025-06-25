package com.shreyansh.store.carts;

import com.shreyansh.store.products.ProductNotFoundException;
import com.shreyansh.store.products.ProductNotFoundInCartException;
import com.shreyansh.store.products.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart(){
         var cart = new Cart();
         cartRepository.save(cart);
         return cartMapper.toCartDto(cart);
     }
     public CartItemDto addToCart(UUID cartId, Long productId) {
         var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
         if (cart == null) {
             throw new CartNotFoundException();
         }
         var product = productRepository.findById(productId).orElse(null);
         if (product == null) {
             throw new ProductNotFoundException();
         }

         var cartItems = cart.addItem(product);
         cartRepository.save(cart);
         return cartMapper.toDto(cartItems);

     }

     public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        return cartMapper.toCartDto(cart);
     }

     public CartItemDto updateCart  (UUID cartId, Long productId, Integer quantity) {
         var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
         if (cart == null) {
             throw new CartNotFoundException();
         }

         var cartItems = cart.getItem(productId);
         if (cartItems == null) {
             throw new ProductNotFoundInCartException();
         }
         cartItems.setQuantity(quantity);


         cartRepository.save(cart);

         return cartMapper.toDto(cartItems);
     }

     public void deleteProductFromCart(UUID cartId, Long productId) {
         var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
         if (cart == null) {
             throw new CartNotFoundException();
         }

         cart.removeItem(productId);
         cartRepository.save(cart);
     }

     public void deleteCart(UUID cartId) {
         var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
         if (cart == null) {
             throw new CartNotFoundException();

         }
         cart.clearCart();
         cartRepository.save(cart);
     }
}
