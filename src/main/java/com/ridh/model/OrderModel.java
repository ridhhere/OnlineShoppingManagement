package com.ridh.model;

import java.time.LocalDateTime;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
	private Long checkoutId;
	private Long customerId;
	private Long cartId;
	private String paymentInformation;
	private String shippingAddress;
	private String billingAddress;
	private double totalPrice;
	private String orderStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
