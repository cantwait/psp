package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mule.api.annotations.param.Payload;

import com.pdvsa.psp.model.ServidorOpc;
import com.pdvsa.psp.model.Tanque;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.service.IServidorService;
import com.pdvsa.psp.service.ITanqueService;

public class StoreTankOpcDataCache {

	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache;

	public void storeLastItemsInMemory(@Payload OpcItemsTransfer items) {

		initializeMap(items);
		for (OpcInfoRegisterMongo item : items.getOpcItems()) {
			for (Entry<String, Map<String, List<OpcInfoRegisterMongo>>> servidor : getMapCache()
					.entrySet()) {

				if (servidor.getKey().equals(item.getNombreServidor())) {
					Map<String, List<OpcInfoRegisterMongo>> innerMap = servidor.getValue();
					for (Entry<String, List<OpcInfoRegisterMongo>> tanque : innerMap.entrySet()) {
						if (item.getTanqueNombre().equals(tanque.getKey())) {
							List<OpcInfoRegisterMongo> variables = tanque.getValue();
							variables.add(item);
						}

					}

				}
			}
		}

	}

	private void initializeMap(OpcItemsTransfer opcItems) {

		List<OpcInfoRegisterMongo> items = new ArrayList<OpcInfoRegisterMongo>();
		Map<String, List<OpcInfoRegisterMongo>> emptyInnerMap = new HashMap<String, List<OpcInfoRegisterMongo>>();

		for (OpcInfoRegisterMongo opc : opcItems.getOpcItems()) {
			if (!getMapCache().containsKey(opc.getNombreServidor())) {
				getMapCache().put(opc.getNombreServidor(), emptyInnerMap);

				Map<String, List<OpcInfoRegisterMongo>> innerMap = getMapCache().get(opc.getNombreServidor());
				if (!innerMap.containsKey(opc.getTanqueNombre())) {
					getMapCache().get(opc.getNombreServidor()).put(opc.getTanqueNombre(), items);
				}
			}else{
				Map<String, List<OpcInfoRegisterMongo>> innerMap = getMapCache().get(opc.getNombreServidor());				
				for (Entry<String, List<OpcInfoRegisterMongo>> m : innerMap.entrySet()) {
					List<OpcInfoRegisterMongo> variables = m.getValue();
					variables.clear();
				}
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
