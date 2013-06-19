package com.pdvsa.psp.service.impl;

import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.dao.ITipoUnidadMedidaDAO;
import com.pdvsa.psp.model.TipoUnidadMedida;
import com.pdvsa.psp.service.ITipoUnidadMedidaService;

@Service("tipoUnidadService")
public class TipoUnidadService implements ITipoUnidadMedidaService{
	
	private ITipoUnidadMedidaDAO tipounidadDAO;

	@Override
	public TipoUnidadMedida getTipoMedidaByNombre(String nombre) {
		return tipounidadDAO.findTipoMedidaByNombre(nombre);
	}

	@Override
	public List<TipoUnidadMedida> getTiposMedidaLikeNombre(String nombre) {
		return StringUtils.isBlank(nombre) ? tipounidadDAO.findAll() : tipounidadDAO.findTiposMedidaLikeNombre(nombre);
	}

	@Override
	public boolean removeTipounidad(Long idTipounidad) {
		TipoUnidadMedida tipounidad = tipounidadDAO.find(idTipounidad);
		if(tipounidad != null){
			return tipounidadDAO.remove(tipounidad);
		}
		return false;
	}

	@Override
	public TipoUnidadMedida saveTipounidad(TipoUnidadMedida tipounidad) {
		return tipounidadDAO.save(tipounidad);
	}

	@Autowired
	public void setTipounidadDAO(ITipoUnidadMedidaDAO tipounidadDAO) {
		this.tipounidadDAO = tipounidadDAO;
	}

	public ITipoUnidadMedidaDAO getTipounidadDAO() {
		return tipounidadDAO;
	}

}
