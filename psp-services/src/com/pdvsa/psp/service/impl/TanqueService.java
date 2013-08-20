package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.ITanqueDAO;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.service.ITanqueService;

@WebService(serviceName="manageTanqueService", endpointInterface="com.pdvsa.psp.service.ITanqueService") 
@Service("tanqueService")
public class TanqueService implements ITanqueService {
	private ITanqueDAO tanqueDAO;
	
	@Autowired
	public void setTanqueDAO(ITanqueDAO tanqueDAO) {
		this.tanqueDAO = tanqueDAO;
	}

	public ITanqueDAO getTanqueDAO() {
		return tanqueDAO;
	}
	
	@Override
	public Tanque getTanqueById(Long idTanque) {
		return tanqueDAO.find(idTanque);
	}

	@Override
	public Tanque getTanqueByNombre(String nombre) {
		return tanqueDAO.findByNombre(nombre);
	}

	@Override
	public List<Tanque> getTanquesLikeNombre(String value) {
		return (StringUtils.isBlank(value)) 
			? tanqueDAO.findAll() 
			: tanqueDAO.findLikeNombre(value);
	}

	@Override
	public boolean removeTanque(Long idTanque) {
		Tanque tanque = tanqueDAO.find(idTanque);
		if (tanque != null) {
			return tanqueDAO.remove(tanque);
		}
		return false;
	}

	@Override
	public Tanque saveTanque(Tanque tanque) {
		return tanqueDAO.save(tanque);
	}

	@Override
	public List<Tanque> getTanquesByServidor(Long servidor, Boolean activo) {
		return tanqueDAO.findTanquesByServidor(servidor, activo);
	}

	@Override	
	public List<Tanque> getTanquesByServidorActivo(Long servidor) {		
		return getTanquesByServidor(servidor, Boolean.TRUE);
	}

	@Override
	public List<Tanque> getAllTanques() {
		Search s = new Search();
		s.addFilterEqual("activo", Boolean.TRUE);
		return getTanqueDAO().search(s);
		
	}


}
