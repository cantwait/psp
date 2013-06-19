package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.model.Tanque;

@WebService
@Service
public interface ITanqueService {

	@WebMethod
	@WebResult(name = "tanque")
	public Tanque getTanqueById(@WebParam(name = "idTanque") Long idTanque);

	@WebMethod
	@WebResult(name = "tanque")
	public Tanque getTanqueByNombre(@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "tanques")
	public List<Tanque> getTanquesLikeNombre(
			@WebParam(name = "value") String value);

	@WebMethod
	public boolean removeTanque(@WebParam(name = "idTanque") Long idTanque);

	@WebMethod
	@WebResult(name = "tanque")
	public Tanque saveTanque(@WebParam(name = "tanque") Tanque tanque);

	@WebMethod
	@WebResult(name = "tanques")
	public List<Tanque> getTanquesByServidor(
			@WebParam(name = "idservidor") Long servidor,
			@WebParam(name = "activo") Boolean activo);

}
