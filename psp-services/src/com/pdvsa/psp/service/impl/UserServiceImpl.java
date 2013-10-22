package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.dao.IRolDAO;
import com.pdvsa.psp.dao.IUserDAO;
import com.pdvsa.psp.dao.IUserRoleDAO;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.IUserService;

@Service("userService")
@WebService
public class UserServiceImpl implements IUserService {

	private IUserDAO userDAO;
	private IUserRoleDAO userRoleDAO;
	private IRolDAO rolDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Autowired
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	@Autowired
	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public IRolDAO getRoleDAO() {
		return rolDAO;
	}

	@Autowired
	public void setRoleDAO(IRolDAO roleDAO) {
		this.rolDAO = roleDAO;

	}

	public boolean delete(Usuario user) {
		return userDAO.remove(user);
	}

	public List<Rol> getAllRoles() {
		return rolDAO.findAll();
	}

	public List<Usuario> getAllUsers() {
		return userDAO.findAll();
	}

	public Usuario getNewUser() {
		return userDAO.getNewUser();
	}

	public List<Rol> getRolesByUser(Usuario user) {
		return rolDAO.findByUsuario(user);
	}

	public Usuario getUserByLoginName(String loginName) {
		return userDAO.findByLoginName(loginName);
	}

	public List<Usuario> getUsersLikeEmail(String value) {
		return userDAO.findLikeEmail(value);
	}

	public List<Usuario> getUsersLikeLoginName(String value) {
		return userDAO.findLikeLoginName(value);
	}

	public List<Usuario> getUsersLikeName(String value) {
		return userDAO.findLikeName(value);
	}

	public Usuario saveOrUpdate(Usuario user) {

		return userDAO.save(user);
	}

	@Override
	public List<Usuario> getUsuariosByRol(Long idRol) {
		return userDAO.findUsuariosByRol(idRol);
	}


}
