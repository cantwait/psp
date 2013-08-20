package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.mule.component.StoreTankOpcDataCache;

@Path("/")
public class CacheItemsRest {
	
	private StoreTankOpcDataCache cacheItem;
	
	@GET
	@Path("/get-items-by-tank")
	@Produces("text/xml")
	public OpcItemsTransfer getItemsFromCacheByTankName(@QueryParam(value="nombre")String tankName){
		OpcItemsTransfer items = new OpcItemsTransfer();
		List<OpcInfoRegisterMongo> variablesEncontradas = new ArrayList<OpcInfoRegisterMongo>();
		
		if(tankName != null){
			variablesEncontradas = getCacheItem().getMapaItems().get(tankName);
		}
		
		if(variablesEncontradas.size() > 0){
			items.getOpcItems().addAll(variablesEncontradas);
		}
		
		return items;
	}

	public StoreTankOpcDataCache getCacheItem() {
		return cacheItem;
	}

	public void setCacheItem(StoreTankOpcDataCache cacheItem) {
		this.cacheItem = cacheItem;
	}

}
