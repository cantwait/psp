package com.pdvsa.psp.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.dao.IUnidadMedidaDAO;
import com.pdvsa.psp.model.UnidadMedida;
import com.pdvsa.psp.service.IUnidadMedidaService;

@Service("unidadService")
public class UnidadMedidaService implements IUnidadMedidaService{
	
	private IUnidadMedidaDAO unidadDAO; 

	@Override
	public UnidadMedida getUnidadMedidaByNombre(String nombre) {
		return unidadDAO.findUnidadByNombre(nombre);
	}

	@Override
	public List<UnidadMedida> getUnidadesMedidaLikeNombre(String nombre) {
		return StringUtils.isBlank(nombre) ? unidadDAO.findAll() : unidadDAO.findUnidadesLikeNombre(nombre);
	}

	@Override
	public boolean removeUnidad(Long idUnidad) {
		UnidadMedida unidad = unidadDAO.find(idUnidad);
		if(unidad != null){
			return unidadDAO.remove(unidad);
		}
		return false;
	}

	@Override
	public UnidadMedida saveUnidad(UnidadMedida unidad) {
		return unidadDAO.save(unidad);
	}

	@Autowired
	public void setUnidadDAO(IUnidadMedidaDAO unidadDAO) {
		this.unidadDAO = unidadDAO;
	}

	public IUnidadMedidaDAO getUnidadDAO() {
		return unidadDAO;
	}

}
