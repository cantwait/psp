package com.pdvsa.psp.dao.impl;

import org.springframework.stereotype.Repository;

import com.pdvsa.psp.dao.IUserRoleDAO;
import com.pdvsa.psp.model.UsuarioRol;

@Repository
public class UserRoleDAO extends BaseDAO<UsuarioRol, Long> implements IUserRoleDAO {

}