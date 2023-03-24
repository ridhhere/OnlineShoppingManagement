package com.ridh.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	private Long productId;
    private String productName;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
    private int availableQuantity;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
