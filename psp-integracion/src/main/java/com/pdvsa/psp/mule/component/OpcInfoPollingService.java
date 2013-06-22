package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.OpcInfoRegisterMongo;
import com.pdvsa.psp.service.impl.OpcControllerService;

public class OpcInfoPollingService extends AbstractTransformer{
	
	private OpcControllerService opcControllerService;

	public OpcControllerService getOpcControllerService() {
		return opcControllerService;
	}

	public void setOpcControllerService(OpcControllerService opcControllerService) {
		this.opcControllerService = opcControllerService;
	}

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
List<OpcInfoRegisterMongo> opcs = new ArrayList<OpcInfoRegisterMongo>();
		
		List<OpcInfoRegister> info = opcControllerService.getAllRegisters();		
		if(info.size() > 0){
			for (OpcInfoRegister op : info) {
				OpcInfoRegisterMongo o = new OpcInfoRegisterMongo();
				o.setStationId(op.getStationId());
				o.setHostModbusSlave(op.getHostModbusSlave());
				o.setPortModbusSlave(op.getPortModbusSlave());
				o.setQuality(op.getQuality());
				o.setRegValue(op.getRegValue());
				o.setTagName(op.getTagName());
				o.setTagOpc(op.getTagOpc());
				o.setReference(op.getReference());
				o.setTimestamp(op.getTimestamp());				
				opcs.add(o);
			}
		}
		
		return opcs;
	}

}
