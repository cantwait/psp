package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.model.UsuarioRol;

public interface IUserRoleDAO extends GenericDAO<UsuarioRol, Long> {
	
	public List<Usuario> getUsuariosByRol(String roleName);

}
