package com.pdvsa.psp.service.impl;

import java.util.List;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.dao.IProductoDAO;
import com.pdvsa.psp.model.Producto;
import com.pdvsa.psp.service.IProductoService;


@WebService(serviceName="manageProductoService", endpointInterface="com.pdvsa.psp.service.IProductoService") 
@Service("productoService")
public class ProductoService implements IProductoService{
	private IProductoDAO productoDAO;
	
	@Autowired
	public void setProductoDAO(IProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public IProductoDAO getProductoDAO() {
		return productoDAO;
	}
	
	@Override
	public Producto getProductoById(Long idProducto) {
		return productoDAO.find(idProducto);
	}

	@Override
	public Producto getProductoByNombre(String nombre) {
		return productoDAO.findByNombre(nombre);
	}

	@Override
	public List<Producto> getProductosLikeNombre(String value) {
		return (StringUtils.isBlank(value)) 
			? productoDAO.findAll() 
			: productoDAO.findLikeNombre(value);
	}

	@Override
	public boolean removeProducto(Long idProducto) {
		Producto producto = productoDAO.find(idProducto);
		if (producto != null) {
			return productoDAO.remove(producto);
		}
		return false;
	}

	@Override
	public Producto saveProducto(Producto producto) {
		return productoDAO.save(producto);
	}

}
