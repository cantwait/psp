package com.pdvsa.psp.mule.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;

@Path("/")
public class QueryErrorLogDataMongo {

	@Path("/consultar")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	@POST
	public OpcErrorMongoRequest getErrorsFromBus(OpcErrorMongoRequest req){
		System.out.println("Leyendo fechas desde componente REST: " + req.getDesde() + " " + req.getHasta());
		return req;
	}

}
