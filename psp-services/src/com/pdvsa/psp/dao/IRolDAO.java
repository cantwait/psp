package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Usuario;

public interface IRolDAO extends GenericDAO<Rol, Long>{
	
	public Rol getNewRol();
	
	public Rol findByNombre(String nombre);
	
	public List<Rol> findLikeNombre(String value);
	
	public List<Rol> findByServidor(Long idServidor, Boolean activo);
	
	public List<Rol> findByUsuario(Usuario usuario);

}
