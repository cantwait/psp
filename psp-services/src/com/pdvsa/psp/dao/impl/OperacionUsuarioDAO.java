package com.pdvsa.psp.dao.impl;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IOperacionUsuarioDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.OperacionUsuario;
import com.pdvsa.psp.model.Usuario;

public class OperacionUsuarioDAO extends BaseDAO<OperacionUsuario, Long> implements IOperacionUsuarioDAO{

	

	@Override
	public List<OperacionUsuario> getOperacionUsuarioByUsuario(Usuario usuario) {
		Search s = new Search();
		s.addFilterEqual("usuario", usuario);		
		return search(s);
	}

	@Override
	public List<OperacionUsuario> getOperacionUsuarioByOperacion(
			Operacion operacion) {
		Search s = new Search();
		s.addFilterEqual("operacion", operacion);
		return search(s);
	}
	
	

}
