package com.obelisco.modelo.servicios.rest;

import java.util.Date;

import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.model.xml.PageLoggerResponse;
import com.pdvsa.psp.model.xml.PageOpcInfoResponse;

public interface IServiciosMongo {
	
	public OpcItemsTransfer getLastItemsByTanque(String nombreServidor, String tanqueNombre);
	
	public PageOpcInfoResponse getDataPentaho(String desde, String hasta, String pais, String region, String localidad, Integer pagina, Integer tamano);
	
	public PageLoggerResponse findLogByPropertiesOnDemand(String desde, String hasta, String tipoEvento, Integer pagina, Integer tamano);
	

}
