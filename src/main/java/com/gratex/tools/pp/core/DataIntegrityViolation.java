package com.gratex.tools.pp.core;

public class DataIntegrityViolation extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityViolation(String message) {
		super(message);
	}

}
