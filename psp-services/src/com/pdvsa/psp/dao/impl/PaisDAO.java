package com.pdvsa.psp.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IPaisDAO;
import com.pdvsa.psp.model.Localidad;
import com.pdvsa.psp.model.Pais;
import com.pdvsa.psp.model.Region;
import com.pdvsa.psp.model.ServidorOpc;

@Repository
public class PaisDAO extends BaseDAO<Pais, Long> implements IPaisDAO{

	@Override
	public Pais findPaisByNombre(String nombre) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.addFilterEqual("nombre", nombre);
		return searchUnique(s);
	}

	@Override
	public List<Pais> findPaisLikeNombre(String nombre) {
		// TODO Auto-generated method stub
		Search s = new Search();
		s.addFilterILike("nombre", nombre);
		return search(s);
	}

	@Override
	public Pais getNewPais() {
		// TODO Auto-generated method stub
		return new Pais();
	}

	@Override
	public List<Pais> findTreePais(String nombre) {
		// TODO Auto-generated method stub
		List<Pais> paises = findAll();
		for (Pais pais : paises) {
			for (Region region : pais.getRegiones()) {
				for (Localidad localidad : region.getLocalidades()) {
					for(ServidorOpc servidor : localidad.getServidoresOpc()){
						servidor.getTanques().size();
					}
				}
			}
		}
		return paises;
	}


}
