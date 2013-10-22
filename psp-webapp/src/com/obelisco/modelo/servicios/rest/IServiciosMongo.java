package com.obelisco.modelo.servicios.rest;

import java.util.Date;

import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.model.xml.PageLogResponseImpl;
import com.pdvsa.psp.model.xml.PageOpcInfoRegisterResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;

public interface IServiciosMongo {
	
	public OpcItemsTransfer getLastItemsByTanque(String nombreServidor, String tanqueNombre);
	
	public PageResponse queryItems(String desde, String hasta, String pais, String region, String localidad, String variable, Integer pagina, Integer tamano);
	
	public PageResponse queryLog(String desde, String hasta, String tipoEvento, Integer pagina, Integer tamano);
	

}
