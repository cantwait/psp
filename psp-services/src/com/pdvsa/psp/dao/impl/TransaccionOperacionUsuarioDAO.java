package com.pdvsa.psp.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.ITransaccionOperacionUsuarioDAO;
import com.pdvsa.psp.model.Operacion;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Transaccion;
import com.pdvsa.psp.model.TransaccionOperacionUsuario;
import com.pdvsa.psp.model.Usuario;

@Repository
public class TransaccionOperacionUsuarioDAO extends BaseDAO<TransaccionOperacionUsuario, Long> implements ITransaccionOperacionUsuarioDAO{

	@Override
	public List<Operacion> getOperacionesByTransaccionAndUsuario(Integer transaccionId, Collection<Long> roles) {
		String qrtStr = "Select tou.operacion From TransaccionOperacionUsuario tou where tou.transaccion.codigo = :transaccion and tou.rol.id in :roles";		
		Query qry = em().createQuery(qrtStr);		
		qry.setParameter("roles", roles);
		qry.setParameter("transaccion", transaccionId);		
		return qry.getResultList();
	}

	@Override
	public List<TransaccionOperacionUsuario> getOperacionesUsuarioByTransaccion(
			Integer transaccionId) {
		String qrtStr = "Select tou From TransaccionOperacionUsuario tou where tou.transaccion.codigo = :transaccion";		
		Query qry = em().createQuery(qrtStr);
		qry.setParameter("transaccion", transaccionId);		
		
		return qry.getResultList();
	}

	@Override
	public List<Rol> getRolesByTransaccionAndOperacion(Integer transaccionId, String codigoId) {
		String qrtStr = "Select tou.rol From TransaccionOperacionUsuario tou where tou.transaccion.codigo = :transaccion and tou.operacion.codigo = :operacion";		
		Query qry = em().createQuery(qrtStr);
		qry.setParameter("transaccion", transaccionId);		
		qry.setParameter("operacion", codigoId);
		return qry.getResultList();
	}

	@Override
	public List<Operacion> getOperacionByTransaccion(Integer transaccionId) {
		String qrtStr = "Select distinct(tou.operacion) From TransaccionOperacionUsuario tou where tou.transaccion.codigo = :transaccion";		
		Query qry = em().createQuery(qrtStr);
		qry.setParameter("transaccion", transaccionId);		
		return qry.getResultList();
	}
	
	

}
