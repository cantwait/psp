package com.pdvsa.psp.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.ITransaccionOperacionUsuarioDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

@Repository
public class TransaccionOperacionUsuarioDAO extends BaseDAO<TransaccionOperacionUsuario, Long> implements ITransaccionOperacionUsuarioDAO{

	@Override
	public List<Operacion> getOperacionesByTransaccionAndUsuario(Integer transaccionId, Long usuarioId) {
		String qrtStr = "Select tou.operacion From TransaccionOperacionUsuario tou where tou.transaccion.codigo = :transaccion and tou.usuario.id = :usuario";		
		Query qry = em().createQuery(qrtStr);		
		qry.setParameter("usuario", usuarioId);
		qry.setParameter("transaccion", transaccionId);		
		return qry.getResultList();
	}

}
