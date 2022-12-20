package com.pack_s.mijung_noodles.order.exception;

public class NotFoundOrderException extends RuntimeException{
	public NotFoundOrderException(){
		super("해당하는 주문이 존재하지 않습니다.");
	}
}
