package com.pack_s.mijung_noodles.order.infra.http.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class OrderCreateRequest {
	@NotBlank
	String customerName;

	@NotBlank
	String customerAddress;

	@NotNull
	Long totalPrice;
}
