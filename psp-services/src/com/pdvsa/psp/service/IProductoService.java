package com.pdvsa.psp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.model.Producto;

@WebService
@Service
public interface IProductoService{
	
	@WebMethod 
	@WebResult(name="producto") 
	public Producto getProductoById(@WebParam(name="idProducto") Long idProducto);	
	
	@WebMethod 
	@WebResult(name="producto") 
	public Producto getProductoByNombre(@WebParam(name="nombre") String nombre);
	
	@WebMethod 
	@WebResult(name="productos")
	public List<Producto> getProductosLikeNombre(@WebParam(name="value") String value);
	
	@WebMethod 
	public boolean removeProducto(@WebParam(name="idProducto") Long idProducto);
	
	@WebMethod 
	@WebResult(name="producto") 
	public Producto saveProducto(@WebParam(name="producto") Producto producto);

}
