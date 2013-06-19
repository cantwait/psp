package org.home.psp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/")
public class StringRestfulService {
	
	@POST
	@Produces()
	public Response sayHello(@QueryParam(value="stationId") String stationId, @QueryParam(value="regValue") String regValue, @QueryParam(value="tagName") String tagName){
		
		System.out.println("Hello Rest Service!..." + stationId + " " + regValue + " " + tagName);
		
		return Response.status(Status.OK).entity("OK").build();
	}

}
