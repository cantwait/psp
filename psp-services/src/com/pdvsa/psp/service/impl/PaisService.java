package com.pdvsa.psp.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdvsa.psp.dao.IPaisDAO;
import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.model.Pais;
import com.pdvsa.psp.model.Region;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.service.IPaisService;

@WebService(name="managePaisService", endpointInterface="com.pdvsa.psp.service.IPaisService")
@Service("paisService")
public class PaisService implements IPaisService{

	private IPaisDAO paisDAO;
	
	@Override
	public List<Pais> getJerarquiaPais(String nombre) {
		List<Pais> paises = getPaisesLikeNombre(nombre);
		for (Pais pais : paises) {
			for (Region region : pais.getRegiones()) {
				for (Localidad localidad : region.getLocalidades()) {
					for (ServidorOpc servidor : localidad.getServidoresOpc()) {
						servidor.getTanques().size();
					}
				}
			}
		}
		return paises;
	}

	@Override
	public Pais getPaisById(Long idPais) {
		return paisDAO.find(idPais);
	}

	@Override
	public Pais getPaisByNombre(String nombre) {
		return paisDAO.findPaisByNombre(nombre);
	}

	@Override
	public List<Pais> getPaisesLikeNombre(String nombre) {
		return (StringUtils.isBlank(nombre)) ? paisDAO.findAll() : paisDAO.findPaisLikeNombre(nombre);
	}

	@Override
	public boolean removePais(Long id) {
		Pais pais = paisDAO.find(id);
		if(pais != null){
			return paisDAO.remove(pais);
		}
		return false;
	}

	@Override
	public Pais savePais(Pais pais) {
		return paisDAO.save(pais);
	}
	
	
	@Autowired
	public void setPaisDAO(IPaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

	public IPaisDAO getPaisDAO() {
		return paisDAO;
	}

	@Override
	public List<Pais> getTreePais(String nombre) {
		return paisDAO.findTreePais(nombre);
	}

}
