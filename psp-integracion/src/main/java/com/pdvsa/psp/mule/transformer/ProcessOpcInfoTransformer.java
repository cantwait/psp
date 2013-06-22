package com.pdvsa.psp.mule.transformer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.pdvsa.psp.model.OpcInfoRegisterMongo;
import com.pdvsa.psp.service.IServidorService;

public class ProcessOpcInfoTransformer extends AbstractTransformer {
	
	private IServidorService servidorService;
	
	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		OpcInfoRegisterMongo opc = null;
		if(src instanceof OpcInfoRegisterMongo){
			opc = (OpcInfoRegisterMongo) src;
			
			HashMap<String, Object> valores = servidorService.getValuesFromServerById(opc.getStationId());			
			String nombreTanque = StringUtils.left(opc.getTagOpc(), StringUtils.indexOf(opc.getTagOpc(), '.'));
			if(valores != null && valores.values().size() > 0){
				opc.setTanqueNombre(nombreTanque);
				opc.setPaisNombre(valores.get("paisNombre").toString());
				opc.setRegionNombre(valores.get("regionNombre").toString());
				opc.setLocalidadNombre(valores.get("localidadNombre").toString());
			}
			return opc;
			
		}
		return src;
	}

	public IServidorService getServidorService() {
		return servidorService;
	}

	public void setServidorService(IServidorService servidorService) {
		this.servidorService = servidorService;
	}
	
	public static void main(String... args){
//		String original = "TK-1002.DA[5].VU";
//		
//		String modificado = StringUtils.left(original, StringUtils.indexOf(original, '.'));
//		
//		System.out.println(modificado);		
		
		
	    Calendar newDate = Calendar.getInstance();
	    
	    newDate.setTime(new Date(Long.valueOf(1371920235676l)));
	    
	    System.out.println(newDate.getTime());
		
	}

}
