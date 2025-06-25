package com.shreyansh.store.carts;

import com.shreyansh.store.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CartItem> items = new LinkedHashSet<>();

    public BigDecimal getTotalPrice() {
        return items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CartItem getItem(Long productId) {
        return items.stream().filter(cartItem -> cartItem.getProduct().getId().equals(productId)).findFirst().orElse(null);


    }

    public CartItem addItem(Product product) {
        var cartItems = getItem(product.getId());
        if (cartItems != null) {
            cartItems.setQuantity(cartItems.getQuantity() + 1);
        }
        else{
            cartItems = new CartItem();
            cartItems.setProduct(product);
            cartItems.setQuantity(1);
            cartItems.setCart(this);
            this.getItems().add(cartItems);

        }
        return cartItems;
    }

    public void removeItem(Long productId) {
        var cartItems = getItem(productId);
        if (cartItems != null) {
            items.remove(cartItems);
        }

    }

    public void clearCart() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}