package com.pdvsa.psp.mule.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.pdvsa.psp.model.OpcInfoRegisterMongo;

public class OpcInfoRestService {
	
	@POST
	@Path("/save-opc-item")
	@Consumes("text/xml")
	@Produces("text/xml")
	public OpcInfoRegisterMongo saveOpcItem(OpcInfoRegisterMongo xml){
		
		return xml;
		
	}

}
