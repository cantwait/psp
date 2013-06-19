package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import com.pdvsa.psp.model.Localidad;
@WebService
public interface ILocalidadService {
	@WebMethod 
	@WebResult(name="localidad") 
	public Localidad getLocalidadById(@WebParam(name="idLocalidad") Long idLocalidad);	
	
	@WebMethod 
	@WebResult(name="localidad") 
	public Localidad getLocalidadByNombre(@WebParam(name="nombre") String nombre);
	
	@WebMethod 
	@WebResult(name="localidades")
	public List<Localidad> getLocalidadesLikeNombre(@WebParam(name="value") String value);
	
	@WebMethod 
	public boolean removeLocalidad(@WebParam(name="idLocalidad") Long idLocalidad);
	
	@WebMethod 
	@WebResult(name="localidad") 
	public Localidad saveLocalidad(@WebParam(name="localidad") Localidad localidad);
	
	@WebMethod
	@WebResult(name="localidades")
	public List<Localidad> getLocalidadesByRegion(@WebParam(name="idregion")Long region,@WebParam(name="activo") Boolean activo);

}
