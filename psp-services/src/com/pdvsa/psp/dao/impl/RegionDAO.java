package com.pdvsa.psp.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IRegionDAO;
import com.pdvsa.psp.model.Region;

@Repository
public class RegionDAO extends BaseDAO<Region, Long> implements IRegionDAO {

	@Override
	public Region getNewRegion() {
		return new Region();
	}
	
	@Override
	public Region findByNombre(String nombre) {
		Search s = new Search(); 
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}
	
	@Override
	public List<Region> findLikeNombre(String value) {
		Search s = new Search(); 
		s.addFilterILike("nombre", value);
		return search(s);
	}

	@Override
	public List<Region> findRegionesByPais(Long pais, Boolean activo) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.addFilterEqual("pais.id", pais);
		if(activo != null){
			s.addFilterEqual("activo", activo);
		}
		return search(s);
	}

}
