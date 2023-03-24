package com.ridh.model;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
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
}
