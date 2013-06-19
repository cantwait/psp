package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.ServidorRol;

public interface IServidorRolDAO extends GenericDAO<ServidorRol, Long>{
	
	public ServidorRol findServidorRolById(Long id);
	public List<ServidorRol> findServidorRolByServidor(Long idServidor);

}
