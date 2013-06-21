package com.pdvsa.psp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.Tanque;

public interface ITanqueDAO extends GenericDAO<Tanque, Long>{
	
	public Tanque getNewTanque();
	
	public Tanque findByNombre(String nombre);
	
	public List<Tanque> findLikeNombre(String value);
	
	public List<Tanque> findTanquesByServidor(Long servidor, Boolean activo);
	
	public HashMap<String, Object> findValuesFromTankName(String namePrefix);

}
