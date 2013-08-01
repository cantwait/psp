package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.UsuarioRol;

@WebService
public interface IRolService {

	@WebMethod
	@WebResult(name = "rol")
	public Rol getRolByName(@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "roles")
	public List<Rol> getRolesLikeNombre(@WebParam(name = "nombre") String nombre);

	@WebMethod
	@WebResult(name = "rol")
	public Rol saveRol(@WebParam(name = "rol") Rol rol);

	@WebMethod
	@WebResult(name = "roles")
	public List<Rol> getRolesByServidor(
			@WebParam(name = "idServidor") Long idServidor,
			@WebParam(name = "activo") Boolean activo);


	@WebMethod
	@WebResult(name = "Rol")
	public Rol saveRolUsuario(@WebParam(name = "rol") Rol rol,
			@WebParam(name = "usuarios") List<UsuarioRol> usuarios);
	
	@WebMethod
	public boolean removeRol(@WebParam(name="idRol")Long idRol);
	
	@WebMethod
	@WebResult(name="correos")
	public String getEmailAdressesByRole(@WebParam(name="roleName")String roleName);

}
