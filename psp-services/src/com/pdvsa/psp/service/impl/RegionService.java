package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IRegionDAO;
import com.pdvsa.psp.model.Region;
import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.service.IRegionService;


@WebService(serviceName="manageRegionService", endpointInterface="com.pdvsa.psp.service.IRegionService") 
@Service("regionService")
public class RegionService implements IRegionService{

	private IRegionDAO regionDAO;

	@Autowired
	public void setRegionDAO(IRegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}

	public IRegionDAO getRegionDAO() {
		return regionDAO;
	}
	
	@Override
	public Region getRegionById(Long idRegion) {
		return regionDAO.find(idRegion);
	}

	@Override
	public Region getRegionByNombre(String nombre) {
		return regionDAO.findByNombre(nombre);
	}

	@Override
	public List<Region> getRegionesLikeNombre(String value) {
		return (StringUtils.isBlank(value)) 
			? regionDAO.findAll() 
			: regionDAO.findLikeNombre(value);
	}
	
	@Override
	public List<Region> getJerarquiaRegiones(String nombre) {
		List<Region> regiones = getRegionesLikeNombre(nombre);
	    for(Region region : regiones) {
	    	for(Localidad localidad : region.getLocalidades()) {
	    		for(ServidorOpc servidor : localidad.getServidoresOpc()) {
	    			servidor.getTanques().size();
	    		}
	    	}
	    }
	    return regiones;
	}	

	@Override
	public boolean removeRegion(Long idRegion) {
		Region region = regionDAO.find(idRegion);
		if (region != null) {
			return regionDAO.remove(region);
		}
		return false;
	}

	@Override
	public Region saveRegion(Region region) {
		return regionDAO.save(region);
	}

	@Override
	public List<Region> getRegionByPais(Long pais, boolean activo) {
		return regionDAO.findRegionesByPais(pais, activo);
	}

	@Override
	public List<Region> getAllRegiones() {
		Search s = new Search();
		s.addFilterEqual("activo", Boolean.TRUE);
		return getRegionDAO().search(s);
	}
	
}
