package com.pdvsa.psp.service;

import java.util.List;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.Usuario;

public interface IOpcAdquisitionService {

	public List<ServidorOpc> getServidoresByRegion(Long idRegion);
	
	public List<Usuario> getUsuariosByServidor(Long idServidor);
	
	public List<ServidorGrupo> getGruposByServidor(Long idServidor);
	
	public List<Tanque> getTanquesByServidor(Long idServidor);
	
	public List<Item> getItemsByGrupo(Long idGrupo);
	
	public ServidorOpc getServidor(Long idServidor);
	

}
