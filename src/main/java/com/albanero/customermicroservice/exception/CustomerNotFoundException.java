package com.albanero.customermicroservice.exception;

public class CustomerNotFoundException extends RuntimeException {

	public String message;
	
	public CustomerNotFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
