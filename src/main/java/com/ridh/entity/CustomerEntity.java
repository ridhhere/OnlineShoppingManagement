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

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String shippingAddress;
    private String billingAddress;
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
