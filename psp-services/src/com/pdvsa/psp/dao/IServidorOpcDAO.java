package com.pdvsa.psp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.Usuario;

public interface IServidorOpcDAO extends GenericDAO<ServidorOpc, Long> {
	
	public ServidorOpc getNewServidor();
	
	public ServidorOpc findByNombre(String nombre);

	public List<ServidorOpc> findLikeNombre(String value);
	
	public ServidorOpc findByHost(String host);
	
	public List<ServidorOpc> findAll(Boolean activo);
	
	public List<ServidorOpc> findByRegion(Long idRegion, Boolean activo);
	
	public List<Usuario> findUsuarios(Long idServidor, Boolean activo);
	
	public List<Tanque> findTanques(Long idServidor, Boolean activo);
	
	public List<ServidorOpc> findServidoresByLocalidad(Long localidad, Boolean activo);
	
	public HashMap<String, Object> findValuesByServerId(Long id);
}
