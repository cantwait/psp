package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.mule.component.StoreDataInCache;

@Path("/")
public class TransferOpcInfoRegisterRest {
	
	@POST
	@Path("/transferir")
	@Consumes("text/xml")
	@Produces("text/xml")
	public OpcItemsTransfer save(OpcItemsTransfer xml){
		return xml;		
	}
	
		

}
