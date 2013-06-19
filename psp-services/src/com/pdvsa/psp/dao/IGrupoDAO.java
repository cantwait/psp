package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Grupo;

public interface IGrupoDAO extends GenericDAO<Grupo, Long>{
	
	public Grupo getNewGrupo();
	
	public Grupo findByNombre(String nombre);
	
	public List<Grupo> findLikeNombre(String nombre);
	
	public List<Grupo> findGruposByServidor(Long idServidor, Boolean activo);
	
}
