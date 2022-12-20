package com.pack_s.coffee.menu.exception;

public class NotFoundMenuException extends RuntimeException {
	public NotFoundMenuException() {
		super("해당하는 menu가 존재하지 않습니다.");
	}
}
