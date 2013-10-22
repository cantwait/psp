package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

public class StoreDataInCache {

	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> cache;

	private Map<String, String> notificacionesEnviadas = null;
	private Map<String, Date> controlTransmision = null;

	public void store(@Payload OpcItemsTransfer items) {

		for (OpcInfoRegisterMongo item : items.getOpcItems()) {

			if (notificacionesEnviadas != null && controlTransmision != null) {
				if (notificacionesEnviadas.containsKey(item.getLocalidad())) {
					notificacionesEnviadas.remove(item.getLocalidad());
				}
				controlTransmision.put(item.getLocalidad(),new Date());
			}

			if (!getCache().containsKey(item.getNombreServidor())) {
				getCache()
						.put(item.getNombreServidor(),
								new ConcurrentHashMap<String, List<OpcInfoRegisterMongo>>());

				Map<String, List<OpcInfoRegisterMongo>> innerMap = getCache()
						.get(item.getNombreServidor());
				List<OpcInfoRegisterMongo> variables = new ArrayList<OpcInfoRegisterMongo>();
				getCache().get(item.getNombreServidor()).put(item.getTanque(),
						variables);
			}

			Map<String, List<OpcInfoRegisterMongo>> servidor = getCache().get(
					item.getNombreServidor());

			if (servidor.containsKey(item.getTanque())) {
				List<OpcInfoRegisterMongo> variables = servidor.get(item
						.getTanque());
				for (Iterator i = variables.iterator(); i.hasNext();) {
					OpcInfoRegisterMongo register = (OpcInfoRegisterMongo) i
							.next();
					if (register.equals(item)) {
						i.remove();
					}
				}
				variables.add(item);
			} else {
				List<OpcInfoRegisterMongo> variables = new ArrayList<OpcInfoRegisterMongo>();
				getCache().get(item.getNombreServidor()).put(item.getTanque(),
						variables);
				variables.add(item);
			}

		}

	}

	public Map<String, Map<String, List<OpcInfoRegisterMongo>>> getCache() {
		return cache;
	}

	public void setCache(
			Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache) {
		this.cache = mapCache;
	}

	public Map<String, String> getNotificacionesEnviadas() {
		return notificacionesEnviadas;
	}

	public void setNotificacionesEnviadas(
			Map<String, String> notificacionesEnviadas) {
		this.notificacionesEnviadas = notificacionesEnviadas;
	}

	public Map<String, Date> getControlTransmision() {
		return controlTransmision;
	}

	public void setControlTransmision(Map<String, Date> controlTransmision) {
		this.controlTransmision = controlTransmision;
	}

}
