package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.GrupoItem;

public interface IGrupoItemDAO extends GenericDAO<GrupoItem, Long>{
	
	public List<GrupoItem> findGrupoItemById(Long id);
	
	public List<GrupoItem> findGrupoItemByGrupo(Long id);

}
