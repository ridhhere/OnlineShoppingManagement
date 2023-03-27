package com.ridh.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.ridh.enums.StatusEnum;

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
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private CustomerEntity customer;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "productId")
	private ProductEntity product;

	private Long quantity;
	private double totalPrice;
	private StatusEnum status;

	@CreationTimestamp
	@Column(name = "create_on", updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "update_on", insertable = false)
	private LocalDateTime updatedOn;
}
