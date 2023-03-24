package com.ridh.model;

import java.time.LocalDateTime;

import com.ridh.entity.ProductEntity;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {
	private Long cartId;
    private Long customerId;
//    private Long productId;
	private ProductEntity product;

    private Long quantity;
    private double totalPrice;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
