package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.ServidorGrupo;

public interface IServidorGrupoDAO extends GenericDAO<ServidorGrupo, Long>{
	
	public ServidorGrupo findServidorGrupoById(Long id);
	
	public List<ServidorGrupo> findServidorGrupoByServidor(Long idServidorGrupo);
	
	public List<ServidorGrupo> findByServidor(Long idServidor, Boolean activo);

}
