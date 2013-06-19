package com.pdvsa.psp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IServidorRolDAO;
import com.pdvsa.psp.model.ServidorRol;

@Repository
public class ServidorRolDAO extends BaseDAO<ServidorRol, Long> implements IServidorRolDAO{

	@Override
	public ServidorRol findServidorRolById(Long id) {
		Search s = new Search();
		s.addFilterEqual("id", id);
		return searchUnique(s);
	}

	@Override
	public List<ServidorRol> findServidorRolByServidor(Long idServidor) {
		Search s = new Search();
		s.addFilterEqual("servidorOpc.id", idServidor);
		return search(s);
	}

}
