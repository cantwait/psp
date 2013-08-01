package com.pdvsa.psp.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.IUserRoleDAO;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.model.UsuarioRol;

@Repository
public class UserRoleDAO extends BaseDAO<UsuarioRol, Long> implements IUserRoleDAO {

	@Override
	public List<Usuario> getUsuariosByRol(String roleName) {
		
		String qryStr = "select ur.usuario from UsuarioRol ur where ur.rol.nombre = :nombre";
		
		Query qry = em().createQuery(qryStr);
		
		qry.setParameter("nombre", roleName);
		
		return qry.getResultList();
	}

}