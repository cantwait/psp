package com.pdvsa.psp.mule.component;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.pdvsa.psp.model.OpcInfoRegister;
import com.pdvsa.psp.model.OpcInfoRegisterMongo;
import com.pdvsa.psp.service.impl.OpcControllerService;

public class OpcInfoPollingService implements Callable{
	
	private OpcControllerService opcControllerService;
	
	@Override
	public Object onCall(MuleEventContext arg0) throws Exception {
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
		
		arg0.getMessage().setPayload(opcs);
		return arg0;
	}

	public OpcControllerService getOpcControllerService() {
		return opcControllerService;
	}

	public void setOpcControllerService(OpcControllerService opcControllerService) {
		this.opcControllerService = opcControllerService;
	}

}
