package com.pdvsa.psp.service.impl;

import java.util.List;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pdvsa.psp.dao.IGrupoDAO;
import com.pdvsa.psp.dao.IGrupoItemDAO;
import com.pdvsa.psp.dao.IServidorGrupoDAO;
import com.pdvsa.psp.model.Grupo;
import com.pdvsa.psp.model.GrupoItem;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.service.IGrupoService;

@WebService(serviceName = "manageGrupoService", endpointInterface = "com.pdvsa.psp.service.IGrupoService")
@Service("grupoService")
public class GrupoService implements IGrupoService {
	private IGrupoDAO grupoDAO;
	private IServidorGrupoDAO servidorgrupoDAO;
	private IGrupoItemDAO grupoitemDAO;

	@Autowired
	public void setGrupoDAO(IGrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	public IGrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	@Override
	public Grupo getGrupoById(Long idGrupo) {
		return grupoDAO.find(idGrupo);
	}

	@Override
	public Grupo getGrupoByNombre(String nombre) {
		return grupoDAO.findByNombre(nombre);
	}

	@Override
	public List<Grupo> getGruposLikeNombre(String value) {
		return (StringUtils.isBlank(value)) ? grupoDAO.findAll() : grupoDAO
				.findLikeNombre(value);
	}

	@Override
	public boolean removeGrupo(Long idGrupo) {
		Grupo grupo = grupoDAO.find(idGrupo);
		if (grupo != null) {
			return grupoDAO.remove(grupo);
		}
		return false;
	}

	@Override
	public Grupo saveGrupo(Grupo grupo) {
		return grupoDAO.save(grupo);
	}

	@Override
	public List<Grupo> getGruposByServidor(Long idServidor, Boolean activo) {
		return grupoDAO.findGruposByServidor(idServidor, activo);
	}

	@Override
	public ServidorGrupo saveServidorGrupo(ServidorGrupo servidorGrupo) {
		return servidorgrupoDAO.save(servidorGrupo);
	}

	@Autowired
	public void setServidorgrupoDAO(IServidorGrupoDAO servidorgrupoDAO) {
		this.servidorgrupoDAO = servidorgrupoDAO;
	}

	public IServidorGrupoDAO getServidorgrupoDAO() {
		return servidorgrupoDAO;
	}
	
	

	public IGrupoItemDAO getGrupoitemDAO() {
		return grupoitemDAO;
	}
	
	@Autowired
	public void setGrupoitemDAO(IGrupoItemDAO grupoitemDAO) {
		this.grupoitemDAO = grupoitemDAO;
	}

	@Override
	public Grupo saveGrupoItems(Grupo grupo, List<GrupoItem> items) {
		if (items != null) {
			if (items.size() > 0) {
				for (GrupoItem grp_item : items) {
					grp_item.setGrupo(grupo);
				}
				grupo.getGrupoItems().addAll(items);
			}
		}
		return this.saveGrupo(grupo);
	}

	@Override
	public List<GrupoItem> getGrupoItemById(Long id) {
		return (id == null) ? grupoitemDAO.findAll() : grupoitemDAO.findGrupoItemById(id);
	}

	@Override
	public List<GrupoItem> getGrupoItemByGrupo(Long idGrupo) {
		return (idGrupo == null) ? grupoitemDAO.findAll() : grupoitemDAO.findGrupoItemByGrupo(idGrupo);
	}
}
