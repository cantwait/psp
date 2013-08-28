package com.pdvsa.psp.mule.rest.exception;

import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestException extends Exception{

	
	private static final long serialVersionUID = 3681090938651375124L;
	
	public BadRequestException(){
		super("Invalid Request!");
	}
	
	public BadRequestException(String message){
		super(message);
	}
	
	public BadRequestException(String message, Throwable cause){
		super(message, cause);
	}
	

}
