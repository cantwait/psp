package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mule.api.annotations.param.Payload;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.service.ITanqueService;

public class StoreTankOpcDataCache {
	
	private Map<String, List<OpcInfoRegisterMongo>> mapaItems = new HashMap<String, List<OpcInfoRegisterMongo>>();
	private ITanqueService tanqueService;

	public void storedLastItemsInMemory(@Payload OpcItemsTransfer items){
		
		List<Tanque> tanques = new ArrayList<Tanque>();
		
		tanques = getTanqueService().getAllTanques();
		
		if(tanques.size() > 0){
			initializeMap(tanques, items);
			for (Entry<String, List<OpcInfoRegisterMongo>> map : getMapaItems().entrySet()) {
				
				for (OpcInfoRegisterMongo item : items.getOpcItems()) {
					if(map.getKey().equals(item.getTanqueNombre())){
						List<OpcInfoRegisterMongo> variables = getMapaItems().get(item.getTanqueNombre());						
						variables.add(item);
					}
				}
			}
			
		}
		
	}
	
	private void initializeMap(List<Tanque> tanques, OpcItemsTransfer opcitems){
		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
		
		if(getMapaItems().size() > 0){
			for(Tanque ta : tanques){
				if(!getMapaItems().containsKey(ta.getNombre())){
					getMapaItems().put(ta.getNombre(), items);
				}
			}
		}else{
			for (Tanque t : tanques) {
				getMapaItems().put(t.getNombre(), items);
			}
		}
		
		for (OpcInfoRegisterMongo opcitem : opcitems.getOpcItems()) {
			if(getMapaItems().containsKey(opcitem.getTanqueNombre())){
				List<OpcInfoRegisterMongo> variables = getMapaItems().get(opcitem.getTanqueNombre());
				variables.clear();
			}
		}
		
	}

	public Map<String, List<OpcInfoRegisterMongo>> getMapaItems() {
		return mapaItems;
	}

	public void setMapaItems(Map<String, List<OpcInfoRegisterMongo>> mapaItems) {
		this.mapaItems = mapaItems;
	}

	public ITanqueService getTanqueService() {
		return tanqueService;
	}

	public void setTanqueService(ITanqueService tanqueService) {
		this.tanqueService = tanqueService;
	}
	
	
	
	

}
