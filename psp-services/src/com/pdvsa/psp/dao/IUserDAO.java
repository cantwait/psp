package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Usuario;

public interface IUserDAO extends GenericDAO<Usuario, Long> {
	
	public Usuario getNewUser();

	public Usuario findByName(String name);

	public Usuario findByLoginName(String loginName);

	public List<Usuario> findLikeName(String value);

	public List<Usuario> findLikeLoginName(String value);

	public List<Usuario> findLikeEmail(String value);
	
	public List<Usuario> findUsuariosByRol(Long idRol);
}
