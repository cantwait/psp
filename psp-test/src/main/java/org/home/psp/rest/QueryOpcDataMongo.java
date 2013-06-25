package org.home.psp.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.home.psp.data.OpcInfoRegister;
import org.home.psp.data.OpcInfoRegisterRequest;

@Path("/")
public class QueryOpcDataMongo {
	
	
	@Path("/find-items")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	@POST
	public OpcInfoRegisterRequest findObjects(OpcInfoRegisterRequest xml){
		
		return xml;
		
	}

}
