package org.home.psp.rest;

import javax.validation.constraints.AssertFalse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.RequestWrapper;

import org.home.psp.data.OpcInfoRegister;
import org.home.psp.data.ResponseMessage;
import org.springframework.http.MediaType;

@Path("/")
public class OpcInfoRegisterRestfulService {
	
	@POST
	@Path("/save-opc-item")
	@Consumes("text/xml")
	@Produces("text/xml")
	public ResponseMessage saveOpcItem(OpcInfoRegister xml){
		
		return new ResponseMessage(xml.getStationId());
		
	}

}
