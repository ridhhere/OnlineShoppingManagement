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
    private Long productId;

    private String productName;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
    private Long availableQuantity;

    @OneToMany(mappedBy = "product")
    private List<CartEntity> carts;

    @CreationTimestamp
    @Column(name = "create_on", updatable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "update_on", insertable = false)
    private LocalDateTime updatedOn;
}
