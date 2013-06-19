package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Pais;

public interface IPaisDAO extends GenericDAO<Pais, Long>{
	
	public Pais getNewPais();
	public List<Pais> findPaisLikeNombre(String nombre);
	public Pais findPaisByNombre(String nombre);
	public List<Pais> findTreePais(String nombre);
	
}
