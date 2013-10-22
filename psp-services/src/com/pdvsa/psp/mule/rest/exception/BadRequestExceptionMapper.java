package com.pdvsa.psp.mule.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException>{

	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.status(400).entity(exception.getMessage()).type("text/xml").build();
	}
}
