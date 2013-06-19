package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IGrupoDAO;
import com.pdvsa.psp.model.Grupo;

@Repository
public class GrupoDAO extends BaseDAO<Grupo, Long> implements IGrupoDAO{
	
	@Override
	public Grupo getNewGrupo() {
		return new Grupo();
	}

	@Override
	public Grupo findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Grupo> findLikeNombre(String value) {
		Search s = new Search(); 
		s.addFilterILike("nombre", value);
		return search(s);
	}

	@Override
	public List<Grupo> findGruposByServidor(Long idServidor, Boolean activo) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.addFilterEqual("servidorGrupos.servidorOpc.id", idServidor);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}

}
