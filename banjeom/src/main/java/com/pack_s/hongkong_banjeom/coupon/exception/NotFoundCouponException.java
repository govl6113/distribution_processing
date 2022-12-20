package com.pack_s.hongkong_banjeom.coupon.exception;

public class NotFoundCouponException extends RuntimeException {
	public NotFoundCouponException() {
		super("해당하는 쿠폰이 없습니다.");
	}

}
