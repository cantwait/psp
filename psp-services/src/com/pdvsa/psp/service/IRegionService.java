package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.model.Region;

@WebService
@Service
public interface IRegionService {
	
	@WebMethod 
	@WebResult(name="region") 
	public Region getRegionById(@WebParam(name="idRegion") Long idRegion);	
	
	@WebMethod 
	@WebResult(name="region") 
	public Region getRegionByNombre(@WebParam(name="nombre") String nombre);
	
	@WebMethod 
	@WebResult(name="regiones")
	public List<Region> getRegionesLikeNombre(@WebParam(name="value") String value);
	
	@WebMethod 
	@WebResult(name="regiones")
	public List<Region> getJerarquiaRegiones(@WebParam(name="nombre") String nombre);	
	
	@WebMethod 
	public boolean removeRegion(@WebParam(name="idRegion") Long idRegion);
	
	@WebMethod 
	@WebResult(name="region") 
	public Region saveRegion(@WebParam(name="region") Region region);
	@WebMethod
	@WebResult(name="region")
	public List<Region> getRegionByPais(@WebParam(name="idpais") Long idPais,@WebParam(name="activo") boolean activo);
	
	@WebMethod
	@WebResult(name="regiones")
	public List<Region> getAllRegiones();
	
}
