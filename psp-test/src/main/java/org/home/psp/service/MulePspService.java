package org.home.psp.service;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class MulePspService implements Callable{
	
	private PspService pspService;

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		pspService.saveOpcData(null);
		return eventContext;
	}

	public PspService getPspService() {
		return pspService;
	}

	public void setPspService(PspService pspService) {
		this.pspService = pspService;
	}

	
}
