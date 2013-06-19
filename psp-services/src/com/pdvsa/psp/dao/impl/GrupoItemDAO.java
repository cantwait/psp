package com.pdvsa.psp.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IGrupoItemDAO;
import com.pdvsa.psp.model.GrupoItem;

@Repository
public class GrupoItemDAO extends BaseDAO<GrupoItem, Long> implements IGrupoItemDAO{

	@Override
	public List<GrupoItem> findGrupoItemById(Long id) {
		Search s = new Search();
		s.addFilterEqual("id", id);
		return search(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrupoItem> findGrupoItemByGrupo(Long id) {
		String qry = "Select g From GrupoItem g Where g.grupo.id = :idGrupo";		
		Query query = em().createQuery(qry);				
		return query.setParameter("idGrupo", id).getResultList();

	}
	

}
