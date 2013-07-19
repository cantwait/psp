package com.pdvsa.psp.mule.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;

@Path("/")
public class OpcInfoRestService {
	
	@POST
	@Path("/transfer-opc-item")
	@Consumes("text/xml")
	@Produces("text/xml")
	public OpcItemsTransfer saveOpcItem(OpcItemsTransfer xml){		
		return xml;		
	}

}
