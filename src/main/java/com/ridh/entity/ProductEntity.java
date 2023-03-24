package com.ridh.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Long productId;
	private String productName;
	private String description;
	private double price;
	private String imageUrl;
	private String category;
	private long availableQuantity;
	
	@CreationTimestamp
	@Column(name = "create_on", updatable = false)
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@Column(name = "update_on", insertable = false)
	private LocalDateTime updatedOn;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartEntity> carts = new ArrayList<>();
}
