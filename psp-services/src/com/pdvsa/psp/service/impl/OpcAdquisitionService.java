package com.pdvsa.psp.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.dao.IServidorGrupoDAO;
import com.pdvsa.psp.dao.IServidorOpcDAO;
import com.pdvsa.psp.dao.IItemDAO;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.Usuario;
import com.pdvsa.psp.service.IOpcAdquisitionService;

@Service("opcAdquisitionService") 
public class OpcAdquisitionService implements IOpcAdquisitionService {
	private IServidorOpcDAO servidorOpcDAO;
	private IServidorGrupoDAO servidorGrupoDAO;
	private IItemDAO itemDAO;

	public IServidorOpcDAO getRegionDAO() {
		return servidorOpcDAO;
	} 
	
	@Autowired
	public void setRegionDAO(IServidorOpcDAO servidorOpcDAO) {
		this.servidorOpcDAO = servidorOpcDAO;
	}
	
	public IItemDAO getItemDAO() {
		return itemDAO;
	}
	
	@Autowired
	public void setItemDAO(IItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public IServidorGrupoDAO getServidorGrupoDAO() {
		return servidorGrupoDAO;
	}	
	
	@Autowired
	public void setServidorGrupoDAO(IServidorGrupoDAO servidorGrupoDAO) {
		this.servidorGrupoDAO = servidorGrupoDAO;
	}

	
	@Override
	public List<ServidorOpc> getServidoresByRegion(Long idRegion) {
		return ((idRegion == null)
			? servidorOpcDAO.findAll(true)
			: servidorOpcDAO.findByRegion(idRegion, true));
	}

	@Override
	public List<Usuario> getUsuariosByServidor(Long idServidor) {
		return servidorOpcDAO.findUsuarios(idServidor, true);
	}

	@Override
	public List<ServidorGrupo> getGruposByServidor(Long idServidor) {
		return servidorGrupoDAO.findByServidor(idServidor, true);
	}

	@Override
	public List<Tanque> getTanquesByServidor(Long idServidor) {
		return servidorOpcDAO.findTanques(idServidor, true);
	}

	@Override
	public List<Item> getItemsByGrupo(Long idGrupo) {
		return itemDAO.findByGrupo(idGrupo, true);
	}

	@Override
	public ServidorOpc getServidor(Long idServidor) {
		return servidorOpcDAO.find(idServidor);
	}
	

}
