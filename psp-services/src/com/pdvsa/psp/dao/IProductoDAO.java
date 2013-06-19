package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Producto;

public interface IProductoDAO extends GenericDAO<Producto, Long>{
	
	public Producto getNewProducto();
	
	public Producto findByNombre(String nombre);
	
	public List<Producto> findLikeNombre(String value);
	
}
