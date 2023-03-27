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
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;
    @Column(name = "billing_address", nullable = false)
    private String billingAddress;
    @Column(name = "payment_information", nullable = false)
    private String paymentInformation;
    @OneToMany(mappedBy = "customer")
    private List<CartEntity> carts;
    @CreationTimestamp
    @Column(name = "create_on", updatable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    @Column(name = "update_on", insertable = false)
    private LocalDateTime updatedOn;
}
