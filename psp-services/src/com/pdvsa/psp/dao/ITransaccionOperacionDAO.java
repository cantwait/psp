package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;

public interface ITransaccionOperacionDAO extends GenericDAO<TransaccionOperacionUsuario, Long>{

	List<TransaccionOperacionUsuario> getTransaccionOperacionByTransaccion(Transaccion transaccion);
	List<TransaccionOperacionUsuario> getTransaccionOperacionByOperacion(Operacion operacion);
}
