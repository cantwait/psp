package org.home.psp.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.home.psp.data.OpcInfoRegister;

@Path("/")
public class QueryOpcDataMongo {
	
	
	@Path("/find-items")
	@Produces("text/xml")
	@Consumes("text/xml")
	@POST
	public OpcInfoRegister findObjects(OpcInfoRegister xml){
		
		return xml;
		
	}

}
