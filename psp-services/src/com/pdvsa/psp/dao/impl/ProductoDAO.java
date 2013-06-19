package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IProductoDAO;
import com.pdvsa.psp.model.Producto;

@Repository
public class ProductoDAO extends BaseDAO<Producto, Long> implements IProductoDAO{

	@Override
	public Producto getNewProducto() {
		return new Producto();
	}
	
	@Override
	public Producto findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Producto> findLikeNombre(String value) {
		Search s = new Search();
		s.addFilterEqual("nombre", value);
		return search(s);
	}

}
