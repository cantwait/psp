package com.pdvsa.psp.modbus.processimage;

public class IllegalAddressException extends RuntimeException {
	private static final long serialVersionUID = 4310859555988203712L;

	public IllegalAddressException() {
	}
	
	public IllegalAddressException(String message) {
		super(message);
	}
}
