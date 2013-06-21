package com.pdvsa.psp.mule.transformer;

import org.apache.commons.lang.StringUtils;
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
			
			servidorService.getValuesFromServerById(opc.getStationId());
			System.out.println("Nombre tagOpc: " + opc.getTagOpc());
			String nombreTanque = StringUtils.left(opc.getTagOpc(), StringUtils.indexOf(opc.getTagOpc(), '.'));
			System.out.println(nombreTanque);
			servidorService.getValuesFromTankByName(nombreTanque);
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
		String original = "TK-1002.DA[5].VU";
		
		String modificado = StringUtils.left(original, StringUtils.indexOf(original, '.'));
		
		System.out.println(modificado);
		
	}

}
