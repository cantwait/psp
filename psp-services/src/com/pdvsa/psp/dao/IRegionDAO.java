package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Region;

public interface IRegionDAO extends GenericDAO<Region, Long> {

	public Region getNewRegion();
	
	public Region findByNombre(String nombre);

	public List<Region> findLikeNombre(String value);
	
	public List<Region> findRegionesByPais(Long pais, Boolean activo);

}
