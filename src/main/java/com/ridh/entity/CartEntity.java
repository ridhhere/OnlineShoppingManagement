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
@Table(name = "CART")
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private CustomerEntity customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private ProductEntity product;
	
	private Long quantity;
	private double totalPrice;
	
	@CreationTimestamp
	@Column(name = "create_on", updatable = false)
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@Column(name = "update_on", insertable = false)
	private LocalDateTime updatedOn;
}
