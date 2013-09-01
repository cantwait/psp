package com.obelisco.modelo.servicios.rest;

import java.util.Date;

import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.model.xml.PageLoggerResponseImpl;
import com.pdvsa.psp.model.xml.PageOpcInfoResponseImpl;
import com.pdvsa.psp.model.xml.PageResponse;

public interface IServiciosMongo {
	
	public OpcItemsTransfer getLastItemsByTanque(String nombreServidor, String tanqueNombre);
	
	public PageResponse getDataPentaho(String desde, String hasta, String pais, String region, String localidad, String variable, Integer pagina, Integer tamano);
	
	public PageResponse findLogByPropertiesOnDemand(String desde, String hasta, String tipoEvento, Integer pagina, Integer tamano);
	

}
