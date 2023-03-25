package com.ridh.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartId;
	private Long customerId;
    private Long productId;
    private Long quantity;
    private double totalPrice;
	private String status;
    private LocalDateTime createdOn;
    private ProductDTO productDetails;

}
