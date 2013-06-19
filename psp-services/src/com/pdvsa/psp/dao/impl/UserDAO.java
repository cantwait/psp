package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IUserDAO;
import com.pdvsa.psp.model.Usuario;

@Repository
public class UserDAO extends BaseDAO<Usuario, Long> implements IUserDAO {

	public Usuario getNewUser() {
		return new Usuario();
	}
	
	public Usuario findByLoginName(String loginName) {
		Search s = new Search(); 
		s.addFilterEqual("login", loginName);
		return searchUnique(s);
	}

	public Usuario findByName(String name) {
		Search s = new Search(); 
		s.addFilterEqual("name", name);
		return searchUnique(s);
	}

	public List<Usuario> findLikeEmail(String value) {
		Search s = new Search(); 
		s.addFilterILike("email", value);
		return search(s);
	}

	public List<Usuario> findLikeLoginName(String value) {
		Search s = new Search(); 
		s.addFilterILike("loginName", value);
		return search(s);
	}

	public List<Usuario> findLikeName(String value) {
		Search s = new Search(); 
		s.addFilterILike("name", value);
		return search(s);
	}

	@Override
	public List<Usuario> findUsuariosByRol(Long idRol) {
		Search s = new Search();
		s.addFilterEqual("usuarioRoles.rol.id", idRol);
		return search(s);
	}
}
