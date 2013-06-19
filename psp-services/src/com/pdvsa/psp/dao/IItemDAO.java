package com.pdvsa.psp.dao;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Item;

public interface IItemDAO extends GenericDAO<Item, Long> {

	public Item getNewItem();
	
	public Item findByNombre(String nombre);
	
	public List<Item> findLikeNombre(String value);
	
	public List<Item> findByGrupo(Long idGrupo, Boolean activo);
	
}
