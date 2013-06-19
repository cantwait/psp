package org.home.psp.service;

import org.home.psp.data.OpcInfoRegister;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class MuleUpdateOpcInfoService implements Callable{
	
	private PspService pspService;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		pspService.updateOpcData(Long.valueOf(eventContext.getMessage().getPayloadAsString()));
		
		return eventContext;
	}

	public PspService getPspService() {
		return pspService;
	}

	public void setPspService(PspService pspService) {
		this.pspService = pspService;
	}
	
	

}
