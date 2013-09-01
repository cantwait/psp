package com.pdvsa.psp.mule.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;

import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.model.xml.OpcItemsTransfer;
import com.pdvsa.psp.mule.component.StoreTankOpcDataCache;

@Path("/")
public class CacheItemsRest {

	private Map<String, Map<String, List<OpcInfoRegisterMongo>>> mapCache;

	@GET
	@Path("/consultar")
	@Produces("text/xml")
	public OpcItemsTransfer getItemsFromCacheByTankName(
			@QueryParam(value = "nombreServidor") String nombreServidor,
			@QueryParam(value = "nombreTanque") String tankName) {
		OpcItemsTransfer items = new OpcItemsTransfer();
		List<OpcInfoRegisterMongo> variablesEncontradas = new ArrayList<OpcInfoRegisterMongo>();

		if (nombreServidor != null) {

			Map<String, List<OpcInfoRegisterMongo>> innerMap = getMapCache()
					.get(nombreServidor);

			// for(Entry<String, Map<String, List<OpcInfoRegisterMongo>>> server
			// : getMapCache().entrySet()){
			// Map<String, List<OpcInfoRegisterMongo>> tanques =
			// server.getValue();
			// for(Entry<String, List<OpcInfoRegisterMongo>> t :
			// tanques.entrySet()){
			// List<OpcInfoRegisterMongo> variables = t.getValue();
			// }
			// }

			if (innerMap != null && innerMap.size() > 0) {

				if (!StringUtils.isBlank(tankName)) {
					if (innerMap.containsKey(tankName)) {
						variablesEncontradas.addAll(innerMap.get(tankName));
					}
				} else {
					for (Entry<String, List<OpcInfoRegisterMongo>> tanque : innerMap
							.entrySet()) {
						variablesEncontradas.addAll(tanque.getValue());
					}
				}
			}

		}

		if (variablesEncontradas.size() > 0) {
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
