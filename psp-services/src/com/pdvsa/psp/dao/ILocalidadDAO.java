package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Localidad;

public interface ILocalidadDAO extends GenericDAO<Localidad, Long>{
	
	public Localidad getNewLocalidad();
	
	public Localidad findByNombre(String nombre);
	
	public List<Localidad> findLikeNombre(String value);
	
	public List<Localidad> findLocalidadesByRegion(Long region, Boolean activo);
}
