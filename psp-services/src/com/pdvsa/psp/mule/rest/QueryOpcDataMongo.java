package com.pdvsa.psp.mule.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;


@Path("/")
public class QueryOpcDataMongo {
	
	
	@Path("/consultar")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	@POST
	public OpcInfoRegisterRequest findObjects(OpcInfoRegisterRequest xml){
		return xml;
	}
	
	@Path("/consultar-ultimo")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	@POST
	public OpcInfoRegisterRequest findLastItem(OpcInfoRegisterRequest request){
		return request;
	}

}