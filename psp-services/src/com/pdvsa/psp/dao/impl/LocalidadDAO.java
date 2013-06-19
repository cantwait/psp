package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ILocalidadDAO;
import com.pdvsa.psp.model.Localidad;

@Repository
public class LocalidadDAO extends BaseDAO<Localidad, Long> implements ILocalidadDAO {
	
	@Override
	public Localidad getNewLocalidad() {
		return new Localidad();
	}	

	@Override
	public Localidad findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Localidad> findLikeNombre(String value) {
		Search s = new Search();
		s.addFilterILike("nombre", value);
		return search(s);
	}

	@Override
	public List<Localidad> findLocalidadesByRegion(Long region, Boolean activo) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.addFilterEqual("region.id", region);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}

}
