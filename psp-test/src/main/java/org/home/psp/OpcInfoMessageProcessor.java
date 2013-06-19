package org.home.psp;

import org.apache.commons.httpclient.ContentLengthInputStream;
import org.home.psp.data.OpcInfoRegister;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleException;
import org.mule.api.lifecycle.Callable;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.mongo.processors.AbstractMessageProcessor;

public class OpcInfoMessageProcessor implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		
		DefaultMuleMessage muleMessage = (DefaultMuleMessage) eventContext.getMessage();
		ContentLengthInputStream clis = (ContentLengthInputStream) muleMessage.getOriginalPayload();
		
		
				
		System.out.println("Mensaje: " + muleMessage.getOriginalPayload().getClass().getName());
		System.out.println("Contenido: " + muleMessage.getOriginalPayload().toString());
		
		
		return eventContext.getMessage();
	}

	

}
