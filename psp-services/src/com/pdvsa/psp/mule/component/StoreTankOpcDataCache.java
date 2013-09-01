package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.mule.api.annotations.param.Payload;

import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.xml.MongoLogger;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.service.IServidorService;
import com.pdvsa.psp.service.ITanqueService;

public class StoreTankOpcDataCache {

	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache;

	public void storeLastItemsInMemory(@Payload OpcItemsTransfer items) {

		for (OpcInfoRegisterMongo item : items.getOpcItems()) {
			
			if (!getMapCache().containsKey(item.getNombreServidor())) {
				getMapCache().put(item.getNombreServidor(), new ConcurrentHashMap<String, List<OpcInfoRegisterMongo>>());

				Map<String, List<OpcInfoRegisterMongo>> innerMap = getMapCache().get(item.getNombreServidor());
				List<OpcInfoRegisterMongo> variables = new ArrayList<OpcInfoRegisterMongo>();
				getMapCache().get(item.getNombreServidor()).put(item.getTanqueNombre(), variables);
			}			
			
			Map<String, List<OpcInfoRegisterMongo>> servidor =  getMapCache().get(item.getNombreServidor());

			if (servidor.containsKey(item.getTanqueNombre())) {
				List<OpcInfoRegisterMongo> variables  = servidor.get(item.getTanqueNombre());
				for (Iterator i = variables.iterator(); i.hasNext();) {
					OpcInfoRegisterMongo register = (OpcInfoRegisterMongo) i.next();
					if (register.equals(item)) {
						i.remove();
					}
				}
				variables.add(item);
			} else {
				List<OpcInfoRegisterMongo> variables = new ArrayList<OpcInfoRegisterMongo>();
				getMapCache().get(item.getNombreServidor()).put(item.getTanqueNombre(), variables);
				variables.add(item);
			}
				
		}

	}

	public Map<String, Map<String, List<OpcInfoRegisterMongo>>> getMapCache() {
		return mapCache;
	}

	public void setMapCache(
			Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache) {
		this.mapCache = mapCache;
	}

}
