package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ITipoUnidadMedidaDAO;
import com.pdvsa.psp.model.TipoUnidadMedida;

@Repository
public class TipoUnidadMedidaDAO extends BaseDAO<TipoUnidadMedida, Long> implements ITipoUnidadMedidaDAO{

	@Override
	public TipoUnidadMedida findTipoMedidaByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<TipoUnidadMedida> findTiposMedidaLikeNombre(String nombre) {
		Search s = new Search();
		s.addFilterILike("nombre", nombre);
		return search(s);
	}

}
