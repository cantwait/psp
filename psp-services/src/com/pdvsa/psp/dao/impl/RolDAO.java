package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IRolDAO;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Usuario;

@Repository
public class RolDAO extends BaseDAO<Rol, Long> implements IRolDAO{
	
	@Override
	public Rol getNewRol() {
		return new Rol();
	}	

	@Override
	public Rol findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		s.addFilterEqual("activo", true);
		return searchUnique(s);
	}

	@Override
	public List<Rol> findLikeNombre(String value) {
		Search s = new Search();
		s.addFilterILike("nombre", value);
		s.addFilterEqual("activo", true);
		return search(s);
	}

	@Override
	public List<Rol> findByServidor(Long idServidor, Boolean activo) {
		Search s = new Search();
		s.addFilterEqual("servidorRoles.servidorOpc.id", idServidor);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}
	
	public List<Rol> findByUsuario(Usuario usuario) {
		Search s = new Search(); 
		s.addFilterEqual("userRoles.user", usuario);
		return search(s);
	}	

}
