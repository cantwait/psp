package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

public interface ITransaccionOperacionUsuarioDAO extends GenericDAO<TransaccionOperacionUsuario, Long>{
	
	List<Operacion> getOperacionesByTransaccionAndUsuario(Integer transaccionId, Long usuarioId);

}
