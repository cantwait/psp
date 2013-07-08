package com.pdvsa.psp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.pdvsa.psp.dao.IItemDAO;
import com.pdvsa.psp.dao.IServidorGrupoDAO;
import com.pdvsa.psp.dao.IServidorOpcDAO;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.ServidorGrupo;
import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.Item.ITEM_TYPE;
import com.pdvsa.psp.service.IItemService;

@WebService(serviceName="manageItemService", endpointInterface="com.pdvsa.psp.service.IItemService") 
@Service("itemService")
public class ItemService implements IItemService{
	private IItemDAO itemDAO;
	private IServidorOpcDAO servidorOpcDAO;
	private IServidorGrupoDAO servidorGrupoDAO;
	
	@Autowired
	public void setItemDAO(IItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public IItemDAO getItemDAO() {
		return itemDAO;
	}
	
	public IServidorOpcDAO getServidorOpcDAO() {
		return servidorOpcDAO;
	}
	
	@Autowired
	public void setServidorOpcDAO(IServidorOpcDAO servidorOpcDAO) {
		this.servidorOpcDAO = servidorOpcDAO;
	}

	public IServidorGrupoDAO getServidorGrupoDAO() {
		return servidorGrupoDAO;
	}
	
	@Autowired
	public void setServidorGrupoDAO(IServidorGrupoDAO servidorGrupoDAO) {
		this.servidorGrupoDAO = servidorGrupoDAO;
	}

	@Override
	public Item getItemById(Long idItem) {
		return itemDAO.find(idItem);
	}

	@Override
	public Item getItemByNombre(String nombre) {
		return itemDAO.findByNombre(nombre);
	}

	@Override
	public List<Item> getItemsLikeNombre(String value) {
		return (StringUtils.isBlank(value)) 
			? itemDAO.findAll() 
			: itemDAO.findLikeNombre(value);
	}
	
	@Override
	public List<Item> getItemsByGrupo(Long idGrupo) {
		return itemDAO.findByGrupo(idGrupo, null);
	}	

	@Override
	public boolean removeItem(Long idItem) {
		Item item = itemDAO.find(idItem);
		if (item != null) {
			return itemDAO.remove(item);
		}
		return false;
	}

	@Override	
	public Item saveItem(Item item) {		
		return itemDAO.save(item);
	}
	
	@Override
	public List<OpcInfoRegister> findMapItemsByRegion(Long idRegion) {
		List<OpcInfoRegister> result = new ArrayList<OpcInfoRegister>();
		int lengReg = 0;
		for(ServidorOpc srv : servidorOpcDAO.findByRegion(idRegion, true)) {
	    	List<Tanque> tanks = servidorOpcDAO.findTanques(srv.getId(), true);
			Integer ref = srv.getRefAddressBase() + 1;
	        for(ServidorGrupo grp : servidorGrupoDAO.findByServidor(srv.getId(), true)) {
				for(Item item: itemDAO.findByGrupo(grp.getGrupo().getId(), true)) {
					if ((lengReg = item.getTipoDato().getLengthForRegister()) == 0)
						continue;
					if (item.getTipoItem() == ITEM_TYPE.TANK) {
						for(Tanque tank: tanks) {
							String tagOpc = tank.getNombre() + "." + item.getItemOpc();
							result.add(new OpcInfoRegister(srv.getId(), 
									ref, tagOpc, item.getNombre(), item.getTipoDato(),
									srv.getHost_adquisicion(), srv.getPort_adquisicion()));
							ref += lengReg; 
						}
					}
					else {
						result.add(new OpcInfoRegister(srv.getId(), 
								ref, item.getItemOpc(), item.getNombre(), item.getTipoDato(),
								srv.getHost_adquisicion(), srv.getPort_adquisicion()));
						ref += lengReg; 
					}
				}
	        }
		}
		return result;
	}

	@Override
	public List<Item> saveItems(List<Item> items) {
		List<Item> lista = new ArrayList<Item>();
		if(items != null){
			if(items.size() != 0){
				for(Item i : items){
					Item o = new Item();
					o = saveItem(i);
					lista.add(o);
				}
			}
		}
		return lista;
	}

	@Override
	public List<Item> findUntransferredItems() {
		Search s = new Search();
		s.addFilterEqual("transferred", Boolean.FALSE);		
		return itemDAO.search(s);
	}		

}
