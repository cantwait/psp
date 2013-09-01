package com.pdvsa.psp.mule.transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.pdvsa.psp.dao.IItemDAO;
import com.pdvsa.psp.model.Item;
import com.pdvsa.psp.model.xml.OpcInfoRegisterMongo;
import com.pdvsa.psp.service.IItemService;
import com.pdvsa.psp.service.IServidorService;

public class ProcessOpcInfoTransformer extends AbstractTransformer {
	
	private IServidorService servidorService;
	private IItemService itemService;
	
	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		
		if(src instanceof OpcInfoRegisterMongo){
			OpcInfoRegisterMongo opc = (OpcInfoRegisterMongo) src;
			
			HashMap<String, Object> valores = servidorService.getValuesFromServerById(opc.getIdServidor());			
			String nombreTanque = StringUtils.left(opc.getTagOpc(), StringUtils.indexOf(opc.getTagOpc(), '.'));
			if(valores != null && valores.values().size() > 0){
				opc.setTanqueNombre(nombreTanque);
				opc.setPaisNombre(valores.get("paisNombre").toString());
				opc.setRegionNombre(valores.get("regionNombre").toString());
				opc.setLocalidadNombre(valores.get("localidadNombre").toString());
				opc.setNombreServidor(valores.get("servidorNombre").toString());
			}
			
			Item item = itemService.getItemByNombre(opc.getTagName());
			if (item != null) {
				opc.setUnidadMedida(item.getUnidadMedida().getNombre());
			}
			
			return opc;
			
		}else if(src instanceof ArrayList){
			List<OpcInfoRegisterMongo> opcs = new ArrayList<OpcInfoRegisterMongo>();
			for (OpcInfoRegisterMongo opc : opcs) {
				HashMap<String, Object> valores = servidorService.getValuesFromServerById(opc.getIdServidor());			
				String nombreTanque = StringUtils.left(opc.getTagOpc(), StringUtils.indexOf(opc.getTagOpc(), '.'));
				if(valores != null && valores.values().size() > 0){
					opc.setTanqueNombre(nombreTanque);
					opc.setPaisNombre(valores.get("paisNombre").toString());
					opc.setRegionNombre(valores.get("regionNombre").toString());
					opc.setLocalidadNombre(valores.get("localidadNombre").toString());
					opc.setNombreServidor(valores.get("servidorNombre").toString());
				}
				Item item = itemService.getItemByNombre(opc.getTagName());
				if (item != null) {
					opc.setUnidadMedida(item.getUnidadMedida().getNombre());
				}
				
				return opcs;
			}
		}
		return src;
	}

	public IServidorService getServidorService() {
		return servidorService;
	}

	public void setServidorService(IServidorService servidorService) {
		this.servidorService = servidorService;
	}

	public IItemService getItemService() {
		return itemService;
	}

	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

}
