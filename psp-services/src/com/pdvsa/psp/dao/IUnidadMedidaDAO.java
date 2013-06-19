package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.UnidadMedida;

public interface IUnidadMedidaDAO extends GenericDAO<UnidadMedida, Long>{
	
	public List<UnidadMedida> findUnidadesLikeNombre(String nombre);
	
	public UnidadMedida findUnidadByNombre(String nombre);

}
