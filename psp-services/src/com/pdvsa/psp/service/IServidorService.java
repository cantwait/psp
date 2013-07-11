package com.pdvsa.psp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.ServidorRol;
import com.pdvsa.psp.model.Tanque;

@WebService
@Service
public interface IServidorService {

	@WebMethod
	@WebResult(name = "servidor")
	public ServidorOpc getServidorById(
			@WebParam(name = "idServidor") Long idServidor);

	@WebMethod
	@WebResult(name = "servidor")
	public ServidorOpc getServidorByNombre(
			@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "servidores")
	public List<ServidorOpc> getServidoresLikeNombre(
			@WebParam(name = "value") String value);

	@WebMethod
	public boolean removeServidor(@WebParam(name = "idServidor") Long idServidor);

	@WebMethod
	@WebResult(name = "servidor")
	public ServidorOpc saveServidor(
			@WebParam(name = "servidor") ServidorOpc servidor);

	@WebMethod
	@WebResult(name = "servidores")
	public List<ServidorOpc> getServidoresByLocalidad(
			@WebParam(name = "idLocalidad") Long localidad,
			@WebParam(name = "activo") Boolean activo);

	@WebMethod
	@WebResult(name = "servidores")
	public ServidorOpc saveServidorOpc(
			@WebParam(name = "servidor") ServidorOpc servidor,
			@WebParam(name = "tanques") List<Tanque> tanques,
			@WebParam(name = "roles") List<ServidorRol> roles,
			@WebParam(name = "grupos") List<ServidorGrupo> grupos);

	@WebMethod
	@WebResult(name = "grupos")
	public List<ServidorGrupo> getServidorGrupoByServidor(
			@WebParam(name = "servidoresGrupo") Long idServidorGrupo);

	@WebMethod
	@WebResult(name = "roles")
	public List<ServidorRol> getServidorRolByServidor(
			@WebParam(name = "servidorRolId") Long idServidorRol);
	
	public HashMap<String, Object> getValuesFromServerById(Long id);
	
	public HashMap<String, Object> getValuesFromTankByName(String nombre);
	
	@WebMethod
	@WebResult(name="servidores")
	public List<ServidorOpc> findUntransferredServers();

}
