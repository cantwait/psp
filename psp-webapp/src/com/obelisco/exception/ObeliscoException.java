package com.obelisco.exception;

public class ObeliscoException extends RuntimeException {

	private int numError = 0;

	public ObeliscoException(int numError) {
		super();
		this.numError = numError;
	}
	
	public ObeliscoException(String message) {
		super(message); 
		this.numError = 0;
	}

	public int getNumError() {
		return numError;
	}

	public void setNumError(int numError) {
		this.numError = numError;
	}

}
