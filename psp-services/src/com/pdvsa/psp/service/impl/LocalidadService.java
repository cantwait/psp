package com.pdvsa.psp.service.impl;

import java.util.List;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.dao.ILocalidadDAO;
import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.service.ILocalidadService;

@WebService(serviceName="manageLocalidadService", endpointInterface="com.pdvsa.psp.service.ILocalidadService") 
@Service("localidadService")
public class LocalidadService implements ILocalidadService{
	private ILocalidadDAO localidadDAO;
	
	@Autowired
	public void setLocalidadDAO(ILocalidadDAO localidadDAO) {
		this.localidadDAO = localidadDAO;
	}

	public ILocalidadDAO getLocalidadDAO() {
		return localidadDAO;
	}
	
	@Override
	public Localidad getLocalidadById(Long idLocalidad) {
		return localidadDAO.find(idLocalidad);
	}

	@Override
	public Localidad getLocalidadByNombre(String nombre) {
		return localidadDAO.findByNombre(nombre);
	}

	@Override
	public List<Localidad> getLocalidadesLikeNombre(String value) {
		return (StringUtils.isBlank(value)) 
			? localidadDAO.findAll() 
			: localidadDAO.findLikeNombre(value);
	}

	@Override
	public boolean removeLocalidad(Long idLocalidad) {
		Localidad localidad = localidadDAO.find(idLocalidad);
		if (localidad != null) {
			return localidadDAO.remove(localidad);
		}
		return false;
	}

	@Override
	public Localidad saveLocalidad(Localidad localidad) {
		return localidadDAO.save(localidad);
	}

	@Override
	public List<Localidad> getLocalidadesByRegion(Long region, Boolean activo) {
		return localidadDAO.findLocalidadesByRegion(region, activo);
	}

}
