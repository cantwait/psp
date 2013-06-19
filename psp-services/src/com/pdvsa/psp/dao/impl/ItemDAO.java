package com.pdvsa.psp.dao.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IItemDAO;
import com.pdvsa.psp.model.Item;

@Repository
public class ItemDAO extends BaseDAO<Item, Long> implements IItemDAO {

	@Override
	public Item getNewItem() {
		return new Item();
	}
	
	@Override
	public Item findByNombre(String nombre) {
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Item> findLikeNombre(String nombre) {
		Search s = new Search();
		s.addFilterILike("nombre", nombre);
		return search(s);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findByGrupo(Long idGrupo, Boolean activo) {
		String sql = "SELECT a FROM Item a JOIN a.grupoItems b WHERE b.grupo.id=?";
		if (activo != null) {
			sql = sql.concat(" AND a.activo=?");
		}
		sql = sql.concat(" ORDER BY a.id");
		Query query = em().createQuery(sql);
		query.setParameter(1, idGrupo);
		if (activo != null) {
			query.setParameter(2, activo);
		}
		return query.getResultList();
	}

}
