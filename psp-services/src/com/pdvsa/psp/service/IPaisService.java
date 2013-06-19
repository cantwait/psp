package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.pdvsa.psp.model.Pais;

@WebService
public interface IPaisService {
	
	@WebMethod
	@WebResult(name="pais")
	public Pais getPaisByNombre(@WebParam(name="nombre") String nombre);
	@WebMethod
	@WebResult(name="pais")
	public Pais getPaisById(@WebParam(name="id") Long idPais);
	@WebMethod
	@WebResult(name="paises")
	public List<Pais> getPaisesLikeNombre(@WebParam(name="nombre") String nombre);
	@WebResult(name="paises")
	@WebMethod
	public List<Pais> getJerarquiaPais(@WebParam(name="nombre")String nombre);
	@WebMethod
	@WebResult(name="estatus")
	public boolean removePais(@WebParam(name="id")Long id);
	@WebMethod
	@WebResult(name="pais")
	public Pais savePais(@WebParam(name="pais")Pais pais);
	@WebMethod
	@WebResult(name="paises")
	public List<Pais> getTreePais(@WebParam(name="nombre")String nombre);

}
