package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IServidorGrupoDAO;
import com.pdvsa.psp.model.ServidorGrupo;

@Repository
public class ServidorGrupoDAO extends BaseDAO<ServidorGrupo, Long> implements IServidorGrupoDAO {

	@Override
	public ServidorGrupo findServidorGrupoById(Long id) {
		Search s = new Search();
		s.addFilterEqual("id", id);
		return searchUnique(s);
	}

	@Override
	public List<ServidorGrupo> findServidorGrupoByServidor(
			Long idServidorGrupo) {
		Search s = new Search();
		s.addFilterEqual("servidorOpc.id", idServidorGrupo);
		return search(s);
	}
	
	@Override
	public List<ServidorGrupo> findByServidor(Long idServidor, Boolean activo) {
		Search s = new Search(); 
		s.addFilterEqual("servidorOpc.id", idServidor);
		if (activo != null) {
			s.addFilterEqual("grupo.activo", activo);
		}
		s.addSortAsc("id");
		return search(s);
	}	

}
