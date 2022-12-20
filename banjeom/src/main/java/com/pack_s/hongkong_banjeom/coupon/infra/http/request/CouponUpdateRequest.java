package com.pack_s.hongkong_banjeom.coupon.infra.http.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CouponUpdateRequest {

	@NotBlank
	String customerName;

	@NotBlank
	String customerPhone;
}
