package com.pdvsa.psp.service;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.model.Grupo;
import com.pdvsa.psp.model.GrupoItem;
import com.pdvsa.psp.model.ServidorGrupo;

@WebService
@Service
public interface IGrupoService {

	@WebMethod
	@WebResult(name = "grupo")
	public Grupo getGrupoById(@WebParam(name = "idGrupo") Long idGrupo);

	@WebMethod
	@WebResult(name = "grupo")
	public Grupo getGrupoByNombre(@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "grupos")
	public List<Grupo> getGruposLikeNombre(
			@WebParam(name = "value") String value);

	@WebMethod
	public boolean removeGrupo(@WebParam(name = "idGrupo") Long idGrupo);

	@WebMethod
	@WebResult(name = "grupo")
	public Grupo saveGrupo(@WebParam(name = "grupo") Grupo grupo);

	@WebMethod
	@WebResult(name = "grupos")
	public List<Grupo> getGruposByServidor(
			@WebParam(name = "idServidor") Long idServidor,
			@WebParam(name = "activo") Boolean activo);

	@WebMethod
	@WebResult(name = "servidorGrupo")
	public ServidorGrupo saveServidorGrupo(
			@WebParam(name = "servidorGrupo") ServidorGrupo servidorGrupo);

	@WebMethod
	@WebResult(name = "grupo")
	public Grupo saveGrupoItems(@WebParam(name = "grupo") Grupo grupo,
			@WebParam(name = "grupoDeItems") List<GrupoItem> items);
	
	@WebMethod
	@WebResult(name="grupos-items")
	public List<GrupoItem> getGrupoItemById(@WebParam(name="id")Long id);
	
	@WebMethod
	@WebResult(name="grupos-items")
	public List<GrupoItem> getGrupoItemByGrupo(@WebParam(name="idGrupo")Long idGrupo);
	
	@WebMethod
	@WebResult(name="grupo-items")
	public List<Grupo> findUntransferredGroups();

}
