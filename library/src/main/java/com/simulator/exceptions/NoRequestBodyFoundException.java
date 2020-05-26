package com.simulator.exceptions;

public class NoRequestBodyFoundException extends RuntimeException {
	public NoRequestBodyFoundException(String method) {
		super("No request body found for given method: " + method + ". "
					+ "The method " + method + " requires non empty request body.");
	}

}
