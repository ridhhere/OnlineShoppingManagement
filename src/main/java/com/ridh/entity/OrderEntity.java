package com.ridh.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ridh.enums.StatusEnum;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "CHECKOUT")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private CartEntity cart;

    private String paymentInformation;
    private String shippingAddress;
    private String billingAddress;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private StatusEnum orderStatus;

    @CreationTimestamp
    @Column(name = "create_on", updatable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "update_on", insertable = false)
    private LocalDateTime updatedOn;
}
