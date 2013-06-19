package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.pdvsa.psp.model.UnidadMedida;

@WebService
@Service
public interface IUnidadMedidaService {
	
	@WebMethod
	@WebResult(name="unidades")
	public List<UnidadMedida> getUnidadesMedidaLikeNombre(@WebParam(name="nombre")String nombre);
	
	@WebMethod
	@WebResult(name="unidad")
	public UnidadMedida getUnidadMedidaByNombre(@WebParam(name="nombre") String nombre);
	
	@WebMethod
	@WebResult(name="unidad")
	public UnidadMedida saveUnidad(@WebParam(name="unidad")UnidadMedida unidad);
	
	@WebMethod
	public boolean removeUnidad(@WebParam(name="idunidad")Long idUnidad);
}
