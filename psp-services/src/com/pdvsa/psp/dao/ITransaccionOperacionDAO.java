package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacion;

public interface ITransaccionOperacionDAO extends GenericDAO<TransaccionOperacion, Long>{

	List<TransaccionOperacion> getTransaccionOperacionByTransaccion(Transaccion transaccion);
	List<TransaccionOperacion> getTransaccionOperacionByOperacion(Operacion operacion);
}
