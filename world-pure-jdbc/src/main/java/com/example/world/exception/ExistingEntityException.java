package com.example.world.exception;

@SuppressWarnings("serial")
public class ExistingEntityException extends RuntimeException {

	public ExistingEntityException(String message) {
		super(message);
	}

}
