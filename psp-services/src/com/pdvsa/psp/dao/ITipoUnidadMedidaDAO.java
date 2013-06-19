package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.TipoUnidadMedida;

public interface ITipoUnidadMedidaDAO extends GenericDAO<TipoUnidadMedida, Long>{
	
	public TipoUnidadMedida findTipoMedidaByNombre(String nombre);
	public List<TipoUnidadMedida> findTiposMedidaLikeNombre(String nombre);

}
