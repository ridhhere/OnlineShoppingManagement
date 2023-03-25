package com.ridh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long productId;
	private String productName;
	private String description;
	private double price;
	private String imageUrl;
	private String category;
	private int availableQuantity;
}
