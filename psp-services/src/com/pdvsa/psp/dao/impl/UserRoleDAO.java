package com.pdvsa.psp.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.IUserRoleDAO;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.model.UsuarioRol;

@Repository
public class UserRoleDAO extends BaseDAO<UsuarioRol, Long> implements IUserRoleDAO {

	@Override
	public List<Usuario> getUsuariosByRolId(Long rolId) {
		
		String qryStr = "select ur.usuario from UsuarioRol ur where ur.rol.id = :id";
		
		Query qry = em().createQuery(qryStr);
		
		qry.setParameter("id", rolId);
		
		return qry.getResultList();
	}

	@Override
	public List<Rol> getRolesByUser(Long idUser) {
		String qrystr = "select ur.rol from UsuarioRol ur where ur.usuario.id = :id";
		
		Query qry = em().createQuery(qrystr);
		
		qry.setParameter("id", idUser);
		
		return qry.getResultList();
	}

	@Override
	public List<Usuario> getUsuariosByRol(String rolNombre) {
		String qryStr = "select ur.usuario from UsuarioRol ur where ur.rol.nombre = :nombre";
		
		Query qry = em().createQuery(qryStr);
		
		qry.setParameter("nombre", rolNombre);
		
		return qry.getResultList();
	}

	
}