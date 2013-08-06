package com.obelisco.modelo.servicios.rest;

import com.pdvsa.psp.model.xml.OpcErrorMongoRequest;
import com.pdvsa.psp.model.xml.OpcErrorResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterListResponse;
import com.pdvsa.psp.model.xml.OpcInfoRegisterRequest;

public interface IServiciosMongo {
	
	public OpcInfoRegisterListResponse findDataPentaho(OpcInfoRegisterRequest req);
	
	public OpcInfoRegisterListResponse findLastRecord(OpcInfoRegisterRequest req);
	
	public OpcErrorResponse findErrors(OpcErrorMongoRequest req);

}
