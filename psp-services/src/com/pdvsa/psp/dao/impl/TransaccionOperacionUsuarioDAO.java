package com.pdvsa.psp.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.pdvsa.psp.dao.ITransaccionOperacionUsuarioDAO;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

public class TransaccionOperacionUsuarioDAO extends BaseDAO<TransaccionOperacionUsuario, Long> implements ITransaccionOperacionUsuarioDAO{

	@Override
	public List<TransaccionOperacionUsuario> getOperacionesByTransaccionAndUsuario(
			Transaccion transaccion, Usuario usuario) {
		String qrtStr = "Select tou From TransaccionOperacionUsuario tou where tou.transaccion = :transaccion and tou.usuario = :usuario";		
		Query qry = em().createQuery(qrtStr);		
		qry.setParameter("usuario", usuario);
		qry.setParameter("transaccion", transaccion);		
		return qry.getResultList();
	}

}
