package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Usuario;

@Service
@WebService
public interface IUserService {

	public Usuario getNewUser();
	
	@WebMethod
	@WebResult(name="usuarios")
	public List<Usuario> getAllUsers();

	@WebMethod
	@WebResult()
	public Usuario saveOrUpdate(Usuario user);

	public boolean delete(Usuario user);

	public Usuario getUserByLoginName(String loginName);
	
	@WebMethod
	@WebResult(name="usuarios")
	public List<Usuario> getUsuariosByRol(@WebParam(name="idRol")Long idRol);
	
	@WebMethod
	@WebResult(name="roles")
	public List<Rol> getAllRoles();

	public List<Usuario> getUsersLikeLoginName(String value);

	public List<Usuario> getUsersLikeName(String value);

	public List<Usuario> getUsersLikeEmail(String value);	
}
