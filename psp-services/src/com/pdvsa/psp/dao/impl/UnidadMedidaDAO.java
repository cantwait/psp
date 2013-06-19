package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IUnidadMedidaDAO;
import com.pdvsa.psp.model.UnidadMedida;

@Repository
public class UnidadMedidaDAO extends BaseDAO<UnidadMedida, Long> implements IUnidadMedidaDAO{

	@Override
	public UnidadMedida findUnidadByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<UnidadMedida> findUnidadesLikeNombre(String nombre) {
		Search s = new Search();
		s.addFilterILike("nombre", nombre);
		return search(s);
	}

}
