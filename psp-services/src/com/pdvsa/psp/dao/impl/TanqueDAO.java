package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ITanqueDAO;
import com.pdvsa.psp.model.Tanque;

@Repository
public class TanqueDAO extends BaseDAO<Tanque, Long> implements ITanqueDAO {

	@Override
	public Tanque getNewTanque() {
		return new Tanque();
	}
	
	@Override
	public Tanque findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Tanque> findLikeNombre(String value) {
		Search s = new Search(); 
		s.addFilterILike("nombre", value);
		return search(s);
	}

	@Override
	public List<Tanque> findTanquesByServidor(Long servidor,
			Boolean activo) {
		Search s = new Search();
		s.addFilterEqual("servidorOpc.id",servidor);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}
}
