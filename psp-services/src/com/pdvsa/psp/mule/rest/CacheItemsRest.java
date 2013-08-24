package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.mule.component.StoreTankOpcDataCache;

@Path("/")
public class CacheItemsRest {
	
	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache;
	
	@GET
	@Path("/get-items-by-tank")
	@Produces("text/xml")
	public OpcItemsTransfer getItemsFromCacheByTankName(@QueryParam(value="nombreServidor")String nombreServidor, @QueryParam(value="nombreTanque")String tankName){
		OpcItemsTransfer items = new OpcItemsTransfer();
		List<OpcInfoRegisterMongo> variablesEncontradas = new ArrayList<OpcInfoRegisterMongo>();
		
		if(nombreServidor != null && tankName != null){
			
//			System.out.println("NOMBRE SERVIDOR: " + nombreServidor + " " + "Nombre Tanque: " + tankName);
			Map<String, List<OpcInfoRegisterMongo>> innerMap = getMapCache().get(nombreServidor);
			for(Entry<String, Map<String, List<OpcInfoRegisterMongo>>> server : getMapCache().entrySet()){				
				Map<String, List<OpcInfoRegisterMongo>> tanque = server.getValue();				
				for(Entry<String, List<OpcInfoRegisterMongo>> t : tanque.entrySet()){					
					List<OpcInfoRegisterMongo> variables = t.getValue();					
//					System.out.println("Servidor: "+ server.getKey() + " Tanque: " + t.getKey().toUpperCase() + " Cantidad de variables: " + variables.size());
				}
			}
			
			if(innerMap != null && innerMap.size() > 0){
//				System.out.println("no es nulo");
				for(Entry<String, List<OpcInfoRegisterMongo>> tanque : innerMap.entrySet()){
					
					if(tanque.getKey().equals(tankName)){
						variablesEncontradas.addAll(innerMap.get(tankName));
					}
				}
			}
			
			
			
		}
		
		if(variablesEncontradas.size() > 0){
			items.getOpcItems().addAll(variablesEncontradas);
		}
		
		return items;
	}

	public Map<String, Map<String, List<OpcInfoRegisterMongo>>> getMapCache() {
		return mapCache;
	}

	public void setMapCache(
			Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache) {
		this.mapCache = mapCache;
	}

	

}
