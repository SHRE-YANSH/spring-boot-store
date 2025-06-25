package com.shreyansh.store.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderDate", source = "createdAt")
   // @Mapping(target = "categoryId", source = "items.product.category")
    OrderDto toDto(Order order);
}
