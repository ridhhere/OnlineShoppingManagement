package com.ridh.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    
    private Long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String shippingAddress;
    private String billingAddress;
    private String paymentInformation;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<CartDTO> cartItems;
//    private List<CheckoutDTO> checkouts;
//    private List<CartItemDTO> cartItems;
//    private List<ProductDTO> products;


    
    // getters and setters
}

