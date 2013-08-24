package com.pdvsa.psp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IServidorGrupoDAO;
import com.pdvsa.psp.dao.IServidorOpcDAO;
import com.pdvsa.psp.dao.ITanqueDAO;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.service.IOpcControllerService;
import com.pdvsa.psp.service.IServidorService;

@WebService(serviceName = "manageServidorService", endpointInterface = "com.pdvsa.psp.service.IServidorService")
@Service("servidorService")
public class ServidorService implements IServidorService{
	
	@Autowired(required=false)
	@Qualifier("opcControllerService")
	private IOpcControllerService controllerService;
	
	@Autowired
	private IServidorOpcDAO servidorDAO;

	@Autowired
	private ITanqueDAO tanqueDAO;

	@Autowired
	private IServidorGrupoDAO servidorGrupoDAO;

	public IServidorGrupoDAO getServidorGrupoDAO() {
		return servidorGrupoDAO;
	}

	public void setServidorGrupoDAO(IServidorGrupoDAO servidorGrupoDAO) {
		this.servidorGrupoDAO = servidorGrupoDAO;
	}

	public void setServidorDAO(IServidorOpcDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}

	public IServidorOpcDAO getServidorDAO() {
		return servidorDAO;
	}

	@Override
	public ServidorOpc getServidorById(Long idServidor) {
		return servidorDAO.find(idServidor);
	}

	@Override
	public ServidorOpc getServidorByNombre(String nombre) {
		return servidorDAO.findByNombre(nombre);
	}

	@Override
	public List<ServidorOpc> getServidoresLikeNombre(String value) {
		return (StringUtils.isBlank(value)) ? servidorDAO.findAll()
				: servidorDAO.findLikeNombre(value);
	}

	@Override
	public boolean removeServidor(Long idServidor) {
		ServidorOpc servidor = servidorDAO.find(idServidor);
		boolean remover = false;
		if (servidor != null) {
			remover = servidorDAO.remove(servidor);
			if(remover){
				if (controllerService != null) {
					controllerService.removeServer(servidor.getLocalidad().getRegion().getId(), servidor.getId());
				}
			}
		}
		return remover;
	}

	@Override
	public ServidorOpc saveServidor(ServidorOpc servidor) {
		servidor.setTransferred(Boolean.FALSE);
		return servidorDAO.save(servidor);
	}

	@Override
	public List<ServidorOpc> getServidoresByLocalidad(Long localidad,
			Boolean activo) {
		return servidorDAO.findServidoresByLocalidad(localidad, activo);
	}

	@Override
	public ServidorOpc saveServidorOpc(ServidorOpc servidor,
			List<Tanque> tanques, List<ServidorGrupo> grupos) {

		

		if (tanques != null) {
			if (tanques.size() > 0) {
				for (Tanque tanque : tanques) {
					tanque.setServidorOpc(servidor);
				}
				servidor.getTanques().addAll(tanques);
			}
		}

		if (grupos != null) {
			if (grupos.size() > 0) {
				for (ServidorGrupo grupo : grupos) {
					grupo.setServidorOpc(servidor);
				}
				servidor.getServidorGrupos().addAll(grupos);
			}
		}

		
		boolean addOpcServer = false;
		if(servidor.getId() == null){
			addOpcServer = true;
		}
		
		
		servidor = servidorDAO.save(servidor);
		if(addOpcServer == true){
			if (controllerService != null) {
				controllerService.addServer(servidor.getLocalidad().getRegion().getId(), servidor.getId());
			}			
		}


		return servidor;

	}

	public void setTanqueDAO(ITanqueDAO tanqueDAO) {
		this.tanqueDAO = tanqueDAO;
	}

	public ITanqueDAO getTanqueDAO() {
		return tanqueDAO;
	}

	
	@Override
	public List<ServidorGrupo> getServidorGrupoByServidor(Long idServidorGrupo) {
		return servidorGrupoDAO.findServidorGrupoByServidor(idServidorGrupo);
	}

	public IOpcControllerService getControllerService() {
		return controllerService;
	}

	public void setControllerService(IOpcControllerService controllerService) {
		this.controllerService = controllerService;
	}

	@Override
	public HashMap<String, Object> getValuesFromServerById(Long id) {
		
		return servidorDAO.findValuesByServerId(id);
	}

	@Override
	public HashMap<String, Object> getValuesFromTankByName(String nombre) {
		
		return tanqueDAO.findValuesFromTankName(nombre);
	}

	@Override
	public List<ServidorOpc> findUntransferredServers() {
		Search s = new Search();
		s.addFilterEqual("transferred", Boolean.FALSE);
		return servidorDAO.search(s);
	}

	@Override	
	public ServidorOpc updateServidorStatus(ServidorOpc servidor) {
		servidor.setTransferred(Boolean.TRUE);
		return servidorDAO.save(servidor);
	}

	@Override	
	public ServidorOpc deleteLogically(ServidorOpc servidor) {
		servidor.setActivo(Boolean.FALSE);
		return saveServidor(servidor);
	}

	@Override
	public List<ServidorOpc> getAllServers() {
		Search s = new Search();
		
		s.addFilterEqual("activo", Boolean.TRUE);
		
		
		return servidorDAO.search(s);
	}
	
}
