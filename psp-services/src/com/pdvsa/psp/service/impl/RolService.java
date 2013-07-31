package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.dao.IRolDAO;
import com.pdvsa.psp.model.Rol;
import com.pdvsa.psp.model.UsuarioRol;
import com.pdvsa.psp.service.IRolService;

@WebService(name="manageRolService", endpointInterface="com.pdvsa.psp.service.IRolService")
@Service("rolService")
public class RolService implements IRolService{

	private IRolDAO rolDAO;
	
	@Override
	public Rol getRolByName(String nombre) {
		return rolDAO.findByNombre(nombre);
	}

	@Override
	public List<Rol> getRolesLikeNombre(String nombre) {
		return (StringUtils.isEmpty(nombre)) ? rolDAO.findAll() : rolDAO.findLikeNombre(nombre);
	}

	@Autowired
	public void setRolDAO(IRolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	public IRolDAO getRolDAO() {
		return rolDAO;
	}

	@Override
	public Rol saveRol(Rol rol) {
		return rolDAO.save(rol);
	}

	@Override
	public List<Rol> getRolesByServidor(Long idServidor, Boolean activo) {
		return rolDAO.findByServidor(idServidor, activo);
	}

		
	
	@Override
	public Rol saveRolUsuario(Rol rol, List<UsuarioRol> usuarios) {
		
		if(usuarios != null){
			if(usuarios.size() > 0){
				for(UsuarioRol usu_rol : usuarios){
					usu_rol.setRol(rol);
				}
				rol.getUsuarioRoles().addAll(usuarios);
			}
		}
		
		return saveRol(rol);
	}

	@Override
	public boolean removeRol(Long idRol) {
		Rol rol = rolDAO.find(idRol);
		if(rol != null){
			return rolDAO.remove(rol);
		}
		return false;
	}

}
