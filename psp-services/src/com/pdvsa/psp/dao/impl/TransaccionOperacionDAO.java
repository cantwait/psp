package com.pdvsa.psp.dao.impl;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ITransaccionOperacionDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;

public class TransaccionOperacionDAO extends BaseDAO<TransaccionOperacionUsuario, Long> implements ITransaccionOperacionDAO{

	@Override
	public List<TransaccionOperacionUsuario> getTransaccionOperacionByTransaccion(
			Transaccion transaccion) {
		Search s = new Search();
		s.addFilterEqual("transaccion", transaccion);
		return search(s);
	}

	@Override
	public List<TransaccionOperacionUsuario> getTransaccionOperacionByOperacion(
			Operacion operacion) {
		Search s = new Search();
		s.addFilterEqual("operacion", operacion);
		return search(s);
	}

}
