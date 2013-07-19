package com.pdvsa.psp.dao.impl;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ITransaccionOperacionDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacion;

public class TransaccionOperacionDAO extends BaseDAO<TransaccionOperacion, Long> implements ITransaccionOperacionDAO{

	@Override
	public List<TransaccionOperacion> getTransaccionOperacionByTransaccion(
			Transaccion transaccion) {
		Search s = new Search();
		s.addFilterEqual("transaccion", transaccion);
		return search(s);
	}

	@Override
	public List<TransaccionOperacion> getTransaccionOperacionByOperacion(
			Operacion operacion) {
		Search s = new Search();
		s.addFilterEqual("operacion", operacion);
		return search(s);
	}

}
